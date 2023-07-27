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
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static nisfappui.services.UserCreator.*;
import static nisfappui.utils.BrowserContextFactory.setupBrowserContext;
import static nisfappui.utils.BrowserFactory.launchBrowser;
import static nisfappui.utils.PropertyReader.getTestDataFromBundle;


@Listeners({TestListener.class})
public abstract class BaseTest {


    public static final String SF_URL = getTestDataFromBundle("BASE_URL");
    public static Logger logger = Logger.getLogger(BaseTest.class);
    public static boolean isClearMode = Boolean.getBoolean("CLEAR_MODE");
    public static boolean isTraceEnabled = Boolean.getBoolean("TRACE_FLAG");
    public static boolean isHeadlessMode = Boolean.getBoolean("HEADLESS_MODE");
    public static String browserType = System.getProperty("BROWSER_TYPE");
    private static ThreadLocal<Playwright> playwrightThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<Browser> browserThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<Page> pageThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> browserContextThreadLocal = new ThreadLocal<>();
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
    //private static volatile List<Path> listOfVideoRecords;
  //  private  static ThreadLocal<List<Path>> listOfVideoRecords = ThreadLocal.withInitial(ArrayList::new);
    private static ConcurrentLinkedQueue<Path> listOfVideoRecords = new ConcurrentLinkedQueue<>();
   // private  static ThreadLocal<List<Path>> fullListOfVideoRecords =  ThreadLocal.withInitial(ArrayList::new);
    private static ConcurrentLinkedQueue<Path> fullListOfVideoRecords = new ConcurrentLinkedQueue<>();


    @BeforeSuite(alwaysRun = true)
    public static void executePreConditions() {
        try {
            FileUtils.deleteDirectory(new File("traces"));
            FileUtils.deleteDirectory(new File("videos"));
            if (isClearMode) {
                FileUtils.deleteDirectory(new File("target/allure-results"));
                logger.debug("Folders [allure-results] were deleted successfully");
            }
            logger.debug("Folders [videos, traces] were deleted successfully");
        } catch (IOException e) {
            logger.debug("Failed to delete folder: " + e.getMessage());
        }
    }

    private static Playwright getTLPlaywright() {

        return playwrightThreadLocal.get();
    }

    private static Browser getTLBrowser() {

        return browserThreadLocal.get();
    }

    private static Page getTLPage() {

        return pageThreadLocal.get();
    }

    private static BrowserContext getBrowserContextTLPage() {

        return browserContextThreadLocal.get();
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
        playwrightThreadLocal.set(playwright);

        browser = launchBrowser(getTLPlaywright(), browserType, isHeadlessMode);
        browserThreadLocal.set(browser);

        browserContext = setupBrowserContext(getTLBrowser(), isTraceEnabled);
        //browserContext.setDefaultTimeout(40000);
        browserContextThreadLocal.set(browserContext);


        //page.setDefaultTimeout(40000);
        page = getBrowserContextTLPage().newPage();
        pageThreadLocal.set(page);

        initPages(this, getTLPage());


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


        getTLPage().close();
        getBrowserContextTLPage().close();
        getTLBrowser().close();
        getTLPlaywright().close();



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
        if (!result.isSuccess() || result.getStatus() == 3) {
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

    private  synchronized void collectPlayWrightVideos() {
        try (Stream<Path> pathStream = Files.walk(Paths.get("videos/")).parallel()) {
            pathStream
                    .filter(Files::isRegularFile)
                    .forEach(listOfVideoRecords::add);
            // Process the list of video records
        } catch (IOException e) {
            logger.error("Failed to walk through the directory: " + e.getMessage());
        }
    }

    private  synchronized   void deleteSuccessfulTestPlayWrightVideos(Method method) {
        try (Stream<Path> pathStream = Files.walk(Paths.get("videos/")).parallel()) {
            pathStream
                    .filter(Files::isRegularFile)
                    .filter(s1 -> !listOfVideoRecords.contains(s1))
                    .forEach(fullListOfVideoRecords::add);

            for (Path path : fullListOfVideoRecords) {
                Files.deleteIfExists(path);
            }
            logger.debug("<<<<< For test [" + method.getName() + "] was deleted a video: " + page.video().path() + " >>>>>");
        } catch (IOException e) {
            logger.error("Failed to walk through the directory2: " + e.getMessage());
        }
    }
}
