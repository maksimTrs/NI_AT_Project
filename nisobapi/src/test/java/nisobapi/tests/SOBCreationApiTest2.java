package nisobapi.tests;

import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import io.qameta.allure.Allure;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static nisobapi.api.StatusCode.CODE_201;
import static nisobapi.constants.TestHelper.APP_ID_REGEX;
import static nisobapi.constants.TestHelper.SOB_CREATION_URL_ENDPOINT;
import static nisobapi.utils.MethodAssertionsForAPI.*;
import static nisobapi.utils.SobAppApiSerialization.deserializePostSonJsonAndSerializeToUniqueJson;


public class SOBCreationApiTest2 extends BaseApiTest {

    @Test()
    public void createSobAppTest2() {

        String jsonConvertAppResult = deserializePostSonJsonAndSerializeToUniqueJson
                ("src/test/resources/testData/SOB_Application_API_UAT_Creation2.json");

        loggerAPI.debug(">>>>>>>>>>>> New Application JSON Body: " + jsonConvertAppResult);
        Allure.attachment(">>>>>>>>>>>> New Application JSON Body: ", jsonConvertAppResult);


        APIResponse response = manager.postRequest(SOB_CREATION_URL_ENDPOINT, RequestOptions.create()
                .setData(jsonConvertAppResult));

        Allure.attachment(">>>>>>>>>>>> New Application URL: ", response.url());

        loggerAPI.debug(">>>>>>>>>>>> New Application Response: " + response.text());
        Allure.attachment(">>>>>>>>>>>> New Application Response: ", response.text());

        assertApiResponseStatusCode(response.status(), CODE_201.CODE);

        JSONObject responseObject = new JSONObject(response.text());
        String applicationNumber = responseObject.getString("applicationNumber");
        String salesforceApplicationId = responseObject.getString("salesforceApplicationId");

        assertApiElementHasMatches(applicationNumber, APP_ID_REGEX);
        assertApiResponseElementIsNotEmptyOrNull(salesforceApplicationId);

        loggerAPI.info(">>>>>>>>>>>> New Application Response Application Number: " + applicationNumber);
        Allure.attachment(">>>>>>>>>>>> New Application Response Application Number: ", applicationNumber);
        loggerAPI.info(">>>>>>>>>>>> New Application Response salesforceApplicationId: " + salesforceApplicationId);
        Allure.attachment(">>>>>>>>>>>> New Application Response salesforceApplicationId: ", salesforceApplicationId);
    }
}
