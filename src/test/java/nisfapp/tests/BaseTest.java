package nisfapp.tests;

import com.microsoft.playwright.*;
import io.qameta.allure.Allure;
import nisfapp.annotations.PlayWrightPage;
import nisfapp.model.User;
import nisfapp.pages.*;
import nisfapp.services.UserCreator;
import nisfapp.utils.BrowserFactory;
import nisfapp.utils.TestListener;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.awt.*;
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

import static nisfapp.utils.PropertyReader.getTestDataFromBundle;


@Listeners({TestListener.class})
public abstract class BaseTest {
    public static final String SF_URL = getTestDataFromBundle("BASE_URL");
    public static Logger logger = Logger.getLogger(BaseTest.class);
    private static boolean isTraceEnabled = Boolean.getBoolean("TRACE_FLAG");
    private static boolean isHeadlessMode = Boolean.getBoolean("HEADLESS_MODE");
    private static String browserType = System.getProperty("BROWSER_TYPE");
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


    private List<Path> listOfVideoRecords = new ArrayList<>();

    @BeforeSuite(alwaysRun = true)
    private static void executePreConditions() {

        try {
            FileUtils.deleteDirectory(new File("videos"));
            FileUtils.deleteDirectory(new File("traces"));
            FileUtils.deleteDirectory(new File("build/allure-results"));
            logger.debug("Folders [videos, traces, allure-results] were deleted successfully");
        } catch (IOException e) {
            System.err.println("Failed to delete folder: " + e.getMessage());
        }
    }


    @BeforeClass()
    public void setUp() {
        //.launch(new BrowserType.LaunchOptions().setHeadless(Boolean.getBoolean("HEADLESS_MODE")).setChannel("chrome"));
        SALES_OFFICER_USER = UserCreator.withCredentialsFromPropertyFile();
        INCORRECT_SF_USER = UserCreator.withWrongCredentialsFromPropertyFile();
    }

    @BeforeMethod(alwaysRun = true)
    public void collectTestData(Method method) {

        logger.info("********************************************************************************");
        logger.info("<<< Test method: " + method.getName() + " was started >>>");
        logger.info("********************************************************************************");

        playwright = Playwright.create();
        browser = BrowserFactory.launchBrowser(playwright, browserType, isHeadlessMode);


        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();


        // browserContext.setDefaultTimeout(40000);
        //  page.setDefaultTimeout(40000);

        if (isTraceEnabled) {
            browserContext = browser.newContext(new Browser.NewContextOptions()
                    .setViewportSize(width, height)
                    //  .setGeolocation(new Geolocation(-33.8571197, 151.2138464))
                    .setPermissions(List.of("geolocation"))
                    .setRecordVideoDir(Paths.get("videos/"))
                    .setRecordVideoSize(1280, 720));

            browserContext.tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true)
                    .setSources(false));

            try (Stream<Path> pathStream = Files.walk(Paths.get("videos/"))) {
                listOfVideoRecords = pathStream
                        .filter(Files::isRegularFile)
                        .collect(Collectors.toList());
                // Process the list of video records
            } catch (Exception e) {
                System.err.println("Failed to walk through the directory: " + e.getMessage());
            }

        } else {
            browserContext = browser.newContext(new Browser.NewContextOptions()
                    .setViewportSize(width, height)
                    .setPermissions(List.of("geolocation")));
        }

        page = browserContext.newPage();

        initPages(this, page);
    }


/*    @AfterClass()
    public void tearDown() {
        *//*browserContext.close();
        browser.close();*//*
    }*/


    @AfterMethod(alwaysRun = true)
    public void attachFilesToFailedTest(ITestContext testInfo, ITestResult result, Method method) throws IOException {
        if (!result.isSuccess()) {
            String uuid = UUID.randomUUID().toString();
            byte[] screenshot = page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get("build/allure-results/screenshot_" + uuid + "screenshot.png"))
                    .setFullPage(true));

            Allure.addAttachment(uuid, new ByteArrayInputStream(screenshot));
            Allure.addAttachment("source.html", "text/html", page.content());

            if (isTraceEnabled) {
                /*String traceFileName = String.format("build/%s_trace.zip", uuid);
                Path tracePath = Paths.get(traceFileName);*/
                Path tracePath = Paths.get("traces/" + result.getMethod().getMethodName().replace("()", "") + ".zip");
                browserContext.tracing()
                        .stop(new Tracing.StopOptions()
                                .setPath(tracePath));
                // Allure.addAttachment("trace.zip", new ByteArrayInputStream(Files.readAllBytes(tracePath)));
                Allure.addAttachment(tracePath.getFileName().toString(), new ByteArrayInputStream(Files.readAllBytes(tracePath)));


                List<Path> fullListOfVideoRecords;
                try (Stream<Path> pathStream = Files.walk(Paths.get("videos/"))) {
                    fullListOfVideoRecords = pathStream
                            .filter(Files::isRegularFile)
                            .filter(s1 -> !listOfVideoRecords.contains(s1))
                            .collect(Collectors.toList());

                    for (Path path : fullListOfVideoRecords) {
                        Files.deleteIfExists(path);
                    }
                } catch (IOException e) {
                    System.err.println("Failed to walk through the directory2: " + e.getMessage());
                }
            }
        }

        logger.info("********************************************************************************");
        logger.info("<<< Test method: " + method.getName() + " was finished >>>");
        logger.info("********************************************************************************");

        browserContext.close();
        browser.close();
        page.close();
    }


    protected void doSFLogIn() {
        logInPage
                .openUrl(SF_URL)
                .fillUserNameAndPasswordFields(SALES_OFFICER_USER)
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
}
