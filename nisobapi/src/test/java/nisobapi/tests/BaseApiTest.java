package nisobapi.tests;

import nisobapi.api.RequestManager;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


import static nisobapi.api.AccessTokenManager.getToken;
import static nisobapi.api.AccessTokenManager.renewToken;
import static nisobapi.constants.TestHelper.SOB_CREATION_URL;

public class BaseApiTest {

    protected static boolean isClearMode = Boolean.getBoolean("CLEAR_MODE");

    public static Logger loggerAPI = Logger.getLogger(BaseApiTest.class);
    private static String TOKEN;
    protected RequestManager manager;

    @BeforeSuite(alwaysRun = true)
    public static void executeApiPreConditions() {
        TOKEN = getToken();

        try {
            if (isClearMode) {
                FileUtils.deleteDirectory(new File("target/allure-results"));
                loggerAPI.debug("Folders [allure-results] were deleted successfully");
            }
        } catch (IOException e) {
            loggerAPI.debug("Failed to delete folder: " + e.getMessage());
        }
    }

    @BeforeClass
    public void setUp() {
        TOKEN = renewToken();

        manager = new RequestManager();
        manager.createPlaywright();

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + TOKEN);
        manager.setApiRequestContext(SOB_CREATION_URL, headers);
    }

    @AfterClass
    public void tearDown() {
        manager.disposeAPIRequestContext();
        manager.closePlaywright();
    }


    @BeforeMethod(alwaysRun = true)
    public void collectTestData(Method method) {

        loggerAPI.info("********************************************************************************");
        loggerAPI.info("<<< Test method: " + method.getName() + " was started >>>");
        loggerAPI.info("********************************************************************************");
    }

    @AfterMethod(alwaysRun = true)
    public void attachFilesToFailedTest(Method method) {
        loggerAPI.info("********************************************************************************");
        loggerAPI.info("<<< Test method: " + method.getName() + " was finished >>>");
        loggerAPI.info("********************************************************************************");
    }

}
