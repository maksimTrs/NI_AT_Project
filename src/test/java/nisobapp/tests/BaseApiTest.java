package nisobapp.tests;

import nisobapp.api.RequestManager;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.util.HashMap;
import java.util.Map;

import static nisobapp.api.AccessTokenManager.getToken;
import static nisobapp.api.AccessTokenManager.renewToken;

public class BaseApiTest {

    protected RequestManager manager;

    protected static final String BASE_URL1 = "https://network-international--uat.sandbox.my.salesforce.com/services/apexrest/selfonboarding/application/";
    private static  String TOKEN;


    @BeforeSuite(alwaysRun = true)
    public static void executeApiPreConditions() {
        TOKEN =  getToken();
    }

    @BeforeTest
    public void setUp() {
        TOKEN = renewToken();

        manager = new RequestManager();
        manager.createPlaywright();

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + TOKEN);
        manager.setApiRequestContext(BASE_URL1, headers);
    }

    @AfterTest
    public void tearDown() {
        manager.disposeAPIRequestContext();
        manager.closePlaywright();
    }
}
