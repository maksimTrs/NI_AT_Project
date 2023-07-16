package nisobapp.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import nisobapp.pojo.SobAppApiMain;
import nisobapp.utils.SobAppDataBuilder;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static nisobapp.api.StatusCode.CODE_201;
import static nisobapp.utils.ConfigLoader.getSingletonInstance;
import static nisobapp.utils.TestHelper.APP_ID_REGEX;
import static nisobapp.utils.TestHelper.SOB_CREATION_URL;
import static org.assertj.core.api.Assertions.assertThat;

public class SOBCreationApiTest extends BaseApiTest {


    @Test(priority = 1)
    public void createSobAppTest() {

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

        loggerAPI.debug(">>>>>>>>>>>> New Application JSON Body = " + jsonConvertAppResult);


        APIResponse response = manager.postRequest(SOB_CREATION_URL, RequestOptions.create()
                .setData(jsonConvertAppResult)
                .setTimeout(80000));

        loggerAPI.debug(">>>>>>>>>>>> New Application Response = " + response.text());

        assertThat(response.status())
                .isEqualTo(CODE_201.CODE);

        JSONObject responseObject = new JSONObject(response.text());
        String applicationNumber = responseObject.getString("applicationNumber");
        String salesforceApplicationId = responseObject.getString("salesforceApplicationId");

        assertThat(applicationNumber)
                .as("applicationNumber value is: " + applicationNumber)
                .isNotEmpty()
                .matches(APP_ID_REGEX);

        loggerAPI.info(">>>>>>>>>>>> New Application Response Application Number = " + applicationNumber);
        loggerAPI.info(">>>>>>>>>>>> New Application Response salesforceApplicationId = " + salesforceApplicationId);

        getSingletonInstance().setApplicationNumber(applicationNumber);
        getSingletonInstance().setSalesforceApplicationId(salesforceApplicationId);
    }
}
