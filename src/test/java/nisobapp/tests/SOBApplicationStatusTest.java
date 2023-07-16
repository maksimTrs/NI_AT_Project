package nisobapp.tests;

import com.microsoft.playwright.APIResponse;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static nisobapp.api.StatusCode.CODE_200;
import static nisobapp.utils.ConfigLoader.getSingletonInstance;
import static nisobapp.utils.TestHelper.SOB_CREATION_URL;
import static org.assertj.core.api.Assertions.assertThat;

public class SOBApplicationStatusTest extends BaseApiTest {

    @Test(priority = 2)
    public void getSFApplicationStatus() {

        APIResponse apiResponse = manager.getRequest(SOB_CREATION_URL + "/" + getSingletonInstance().getSalesforceApplicationId());

        assertThat(apiResponse.status())
                .isEqualTo(CODE_200.CODE);

        loggerAPI.debug(">>>>>>>>>>>> Application Response = " + apiResponse.text());

        JSONObject responseObject = new JSONObject(apiResponse.text());
        String applicationStage = responseObject.getString("stage");
        String salesforceApplicationId = responseObject.getString("salesforceApplicationId");

        assertThat(applicationStage)
                .as("applicationStage value should be Draft OR Approval")
                //.isEqualTo("Draft")
                .containsAnyOf("Draft", "Approval");

        assertThat(salesforceApplicationId)
                .as("salesforceApplicationId value should be equal value form conf file")
                .isEqualTo(getSingletonInstance().getSalesforceApplicationId());

        loggerAPI.info(">>>>>>>>>>>> Application Response salesforceApplicationId = " + salesforceApplicationId);
    }
}
