package nisfappui.tests;

import com.microsoft.playwright.*;
import io.qameta.allure.Allure;
import nisfappui.annotations.PlayWrightPage;
import nisfappui.model.User;
import nisfappui.pages.*;
import nisfappui.utils.TestListener;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static nisfappui.services.UserCreator.*;
import static nisfappui.utils.BrowserContextFactory.setupBrowserContext;
import static nisfappui.utils.BrowserFactory.launchBrowser;
import static nisfappui.utils.PropertyReader.getTestDataFromBundle;


@Listeners({TestListener.class})
public abstract class BaseTest {

    protected static final String SF_URL = getTestDataFromBundle("BASE_URL");
    public static Logger logger = Logger.getLogger(BaseTest.class);
    protected static boolean isClearMode = Boolean.getBoolean("CLEAR_MODE");
    protected static boolean isTraceEnabled = Boolean.getBoolean("TRACE_FLAG");
    protected static boolean isHeadlessMode = Boolean.getBoolean("HEADLESS_MODE");
    protected static String browserType = System.getProperty("BROWSER_TYPE");
    public User SALES_OFFICER_USER;
    public User INCORRECT_SF_USER;
    protected Playwright playwright;
    protected Page page;
    protected BrowserContext browserContext;
    protected Browser browser;
    @PlayWrightPage
    protected LogInPage logInPage;
    @PlayWrightPage
    protected MainSFAppPage mainSFAppPage;
    @PlayWrightPage
    protected MerchantInitialCreationPage merchantInitialCreationPage;
    @PlayWrightPage
    protected NewApplicationMerchantInformationPartitionPage newApplicationMerchantInformationPartitionPage;
    @PlayWrightPage
    NewApplicationAuthorizedSignatoryPartitionPage newApplicationAuthorizedSignatoryPartitionPage;
    @PlayWrightPage
    NewApplicationSalesOfficerInspectionPartitionPage newApplicationSalesOfficerInspectionPartitionPage;
    @PlayWrightPage
    NewApplicationPaymentSettlementDetailsPartitionPage newApplicationPaymentSettlementDetailsPartitionPage;
    @PlayWrightPage
    NewApplicationBusinessDetailsPartitionPage newApplicationBusinessDetailsPartitionPage;
    @PlayWrightPage
    NewApplicationFeesChargesPartitionPage newApplicationFeesChargesPartitionPage;
    @PlayWrightPage
    ApplicationPage applicationPage;
    @PlayWrightPage
    ContactPage contactPage;
    @PlayWrightPage
    DocumentPage documentPage;
    @PlayWrightPage
    NewApplicationFeesChargesEcomPartitionPage newApplicationFeesChargesEcomPartitionPage;
    @PlayWrightPage
    NGeniusOnlinePartitionPage nGeniusOnlinePartitionPage;
    private List<Path> listOfVideoRecords;

    @BeforeSuite(alwaysRun = true)
    public static void executePreConditions() {
        try {
            if (isClearMode) {
                FileUtils.deleteDirectory(new File("traces"));
                FileUtils.deleteDirectory(new File("videos"));
                FileUtils.deleteDirectory(new File("target/allure-results"));
                logger.debug("Folders [videos, traces, allure-results] were deleted successfully");
            }
        } catch (IOException e) {
            logger.debug("Failed to delete folder: " + e.getMessage());
        }
    }


    @BeforeClass()
    public void setUp() {
        SALES_OFFICER_USER = withCredentialsFromPropertyFile(getTestDataFromBundle(USER_NAME),
                        getTestDataFromBundle(USER_PASSWORD));
        INCORRECT_SF_USER = withWrongCredentialsFromPropertyFile(getTestDataFromBundle(USER_NAME),
                        getTestDataFromBundle(USER_PASSWORD_WRONG));
    }

    @BeforeMethod(alwaysRun = true)
    public void collectTestData(Method method) {

        logger.info("********************************************************************************");
        logger.info("<<< Test method: " + method.getName() + " was started >>>");
        logger.info("********************************************************************************");


        playwright = Playwright.create();
        browser = launchBrowser(playwright, browserType, isHeadlessMode);

        browserContext = setupBrowserContext(browser, isTraceEnabled);
        browserContext.setDefaultTimeout(40000);


        //page.setDefaultTimeout(40000);
        page = browserContext.newPage();
        initPages(this, page);

        if (isTraceEnabled) {
            collectPlayWrightVideos();
        }
    }


    @AfterMethod(alwaysRun = true)
    public void attachFilesToFailedTest(ITestResult result, Method method) throws IOException {

        doLoggingFailedTest(result, method);

        logger.info("********************************************************************************");
        logger.info("<<< Test method: " + method.getName() + " was finished >>>");
        logger.info("********************************************************************************");


        browserContext.close();
        browser.close();
        page.close();
        playwright.close();

        if (isTraceEnabled && result.isSuccess()) {
            deleteSuccessfulTestPlayWrightVideos(method);
        }
    }


    protected void doSFLogIn(String SFurl, User logInUser) {
        logInPage
                .openUrl(SFurl)
                .fillUserNameAndPasswordFields(logInUser)
                .doLogIn();
    }

    private void initPages(Object obj, Page page) {
        Class<?> superclass = obj.getClass().getSuperclass();
        for (Field field : superclass.getDeclaredFields()) {
            if (field.isAnnotationPresent(PlayWrightPage.class)) {
                Class<?>[] pageClass = {Page.class};
                try {
                    field.set(this, field.getType().getConstructor(pageClass).newInstance(page));
                } catch (IllegalAccessException | InstantiationException | InvocationTargetException |
                         NoSuchMethodException e) {
                    throw new RuntimeException("!!! +++ Constructor for PO objects wasn't created for field: " + field.getName() + " +++ !!!\n" + e);
                }
            }
        }
    }

    private void doLoggingFailedTest(ITestResult result, Method method) throws IOException {
        if (!result.isSuccess()) {
            String uuid = UUID.randomUUID().toString();
            byte[] screenshot = page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get("build/allure-results/screenshot_" + uuid + "screenshot.png"))
                    .setFullPage(true));

            Allure.addAttachment(uuid, new ByteArrayInputStream(screenshot));

            if (isTraceEnabled) {
                logger.debug("<<<<< For test [" + method.getName() + "] was created a video: " + page.video().path() + " >>>>>");
                Path tracePath = Paths.get("traces/" + result.getMethod().getMethodName().replace("()", "") + ".zip");
                browserContext.tracing()
                        .stop(new Tracing.StopOptions()
                                .setPath(tracePath));
                Allure.addAttachment(tracePath.getFileName().toString(), new ByteArrayInputStream(Files.readAllBytes(tracePath)));
            }
        }
    }

    private void collectPlayWrightVideos() {
        try (Stream<Path> pathStream = Files.walk(Paths.get("videos/"))) {
            listOfVideoRecords = pathStream
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
            // Process the list of video records
        } catch (IOException e) {
            logger.error("Failed to walk through the directory: " + e.getMessage());
        }
    }

    private void deleteSuccessfulTestPlayWrightVideos(Method method) {
        List<Path> fullListOfVideoRecords = new ArrayList<>();
        try (Stream<Path> pathStream = Files.walk(Paths.get("videos/"))) {
            fullListOfVideoRecords = pathStream
                    .filter(Files::isRegularFile)
                    .filter(s1 -> !listOfVideoRecords.contains(s1))
                    .collect(Collectors.toList());

            for (Path path : fullListOfVideoRecords) {
                Files.deleteIfExists(path);
            }
            logger.debug("<<<<< For test [" + method.getName() + "] was deleted a video: " + page.video().path() + " >>>>>");
        } catch (IOException e) {
            logger.error("Failed to walk through the directory2: " + e.getMessage());
        }
    }
}
