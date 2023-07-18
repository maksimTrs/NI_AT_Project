package nisobapp.tests;

import com.microsoft.playwright.APIResponse;
import io.qameta.allure.Allure;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static nisobapp.api.StatusCode.CODE_200;
import static nisobapp.utils.ConfigLoader.getSingletonInstance;
import static nisobapp.utils.MethodAssertionsForAPI.*;
import static nisobapp.utils.TestHelper.SOB_CREATION_URL_ENDPOINT;
import static nisobapp.utils.TestHelper.SOB_CREATION_URL_GETAPP_SF_ID;

public class SOBApplicationStatusTest extends BaseApiTest {

    @Test(priority = 2)
    public void getSFApplicationStatus() {

        APIResponse apiResponse = manager.getRequest(SOB_CREATION_URL_ENDPOINT + SOB_CREATION_URL_GETAPP_SF_ID);

        loggerAPI.debug(">>>>>>>>>>>> API url:" + apiResponse.url());
        Allure.attachment(">>>>>>>>>>>> API url:", apiResponse.url());


        assertApiResponseStatusCode(apiResponse.status(), CODE_200.CODE);

        loggerAPI.debug(">>>>>>>>>>>> Application Response: " + apiResponse.text());
        Allure.attachment(">>>>>>>>>>>> Application Response: ", apiResponse.text());

        JSONObject responseObject = new JSONObject(apiResponse.text());
        String applicationStage = responseObject.getString("stage");
        String salesforceApplicationId = responseObject.getString("salesforceApplicationId");

        assertApiElementContainsText(applicationStage, "Draft", "Approval");

        assertApiResponseElementHasValue(salesforceApplicationId, getSingletonInstance().getSalesforceApplicationId());
    }
}
