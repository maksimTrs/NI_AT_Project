package nisobapi.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import io.qameta.allure.Allure;
import nisobapi.pojo.SobAppApiMain;
import nisobapi.utils.SobAppDataBuilder;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static nisobapi.api.StatusCode.CODE_200;
import static nisobapi.api.StatusCode.CODE_201;
import static nisobapi.constants.TestHelper.SOB_CREATION_URL_ENDPOINT;
import static nisobapi.utils.MethodAssertionsForAPI.*;

public class SOBApplicationStatusTest extends BaseApiTest {

    private String createSoBApp() {

        SobAppApiMain newApplicationFromBuilder = SobAppApiMain.builder()
                .application(SobAppDataBuilder.getApplicationBuilderData())
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonConvertAppResult;
        try {
            jsonConvertAppResult = objectMapper.writeValueAsString(newApplicationFromBuilder);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        APIResponse response = manager.postRequest(SOB_CREATION_URL_ENDPOINT, RequestOptions.create()
                .setData(jsonConvertAppResult));

        assertApiResponseStatusCode(response.status(), CODE_201.CODE);

        JSONObject responseObject = new JSONObject(response.text());
        String salesforceApplicationId = responseObject.getString("salesforceApplicationId");

        assertApiResponseElementIsNotEmptyOrNull(salesforceApplicationId);

        return salesforceApplicationId;
    }

    @Test()
    public void getSFApplicationStatus() {

        String salesforceApplicationId = createSoBApp();
        loggerAPI.debug(">>>>>>>>>>>> API salesforceApplicationId:" + salesforceApplicationId);
        Allure.attachment(">>>>>>>>>>>> API salesforceApplicationId:", salesforceApplicationId);

        APIResponse apiResponse = manager.getRequest(SOB_CREATION_URL_ENDPOINT + "/" + salesforceApplicationId);

        loggerAPI.debug(">>>>>>>>>>>> API url:" + apiResponse.url());
        Allure.attachment(">>>>>>>>>>>> API url:", apiResponse.url());


        assertApiResponseStatusCode(apiResponse.status(), CODE_200.CODE);

        loggerAPI.debug(">>>>>>>>>>>> Application Response: " + apiResponse.text());
        Allure.attachment(">>>>>>>>>>>> Application Response: ", apiResponse.text());

        JSONObject responseObject = new JSONObject(apiResponse.text());
        String applicationStage = responseObject.getString("stage");
        String salesforceApplicationId_new = responseObject.getString("salesforceApplicationId");

        assertApiElementContainsText(applicationStage, "Draft", "Approval");
        assertApiResponseElementHasValue(salesforceApplicationId_new, salesforceApplicationId);
    }
}
