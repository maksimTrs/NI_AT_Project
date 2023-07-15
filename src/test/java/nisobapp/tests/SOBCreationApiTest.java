package nisobapp.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import nisobapp.pojo.SobAppApiMain;
import nisobapp.utils.SobAppDataBuilder;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static nisobapp.utils.ConfigLoader.getSingletonInstance;
import static org.assertj.core.api.Assertions.assertThat;

public class SOBCreationApiTest extends BaseApiTest {


    @Test
    public void createBookingTest() {

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

        System.out.println("^^^^^^^^^^^^^^^ newApplication JSON = " + jsonConvertAppResult);


        APIResponse response = manager.postRequest(BASE_URL1, RequestOptions.create()
                .setData(jsonConvertAppResult)
                .setTimeout(80000));

        System.out.println("***********" + response.text());
        System.out.println("***********" + response.statusText());

        assertThat(response.status()).isEqualTo(201);

        JSONObject responseObject = new JSONObject(response.text());
        String applicationNumber = responseObject.getString("applicationNumber");
        System.out.println("************** applicationNumber = " + applicationNumber);

        assertThat(applicationNumber)
                .as("applicationNumber value is: " + applicationNumber)
                .isNotEmpty()
                .matches("A-\\d{9,}");

        getSingletonInstance().setApplicationNumber(applicationNumber);
    }
}
