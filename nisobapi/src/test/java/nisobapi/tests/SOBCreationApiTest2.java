package nisobapi.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import io.qameta.allure.Allure;
import nisobapi.pojo.*;
import org.apache.commons.io.*;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import static nisobapi.api.StatusCode.CODE_201;
import static nisobapi.constants.TestHelper.APP_ID_REGEX;
import static nisobapi.constants.TestHelper.SOB_CREATION_URL_ENDPOINT;
import static nisobapi.utils.ApiAppDataFaker.getRandomDateOfEstablishment;
import static nisobapi.utils.ApiAppDataFaker.getRandomIntValue;
import static nisobapi.utils.MethodAssertionsForAPI.*;
import static nisobapi.utils.SobAppDataBuilder.*;


public class SOBCreationApiTest2 extends BaseApiTest {


    @Test()
    public void createSobAppTest2()   {

        String fileContent = null;
        try {
            File file = new File("src/test/resources/testData/SOB_Application_API_UAT_Creation2.json");
            fileContent = FileUtils.readFileToString(file, "UTF-8");
            //System.out.println(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }


        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(String.class, new PassportNumberDeserializer());
        module.addDeserializer(String.class, new MobileNumberDeserializer());
        module.addDeserializer(String.class, new TradeLicenseNumberDeserializer());
        module.addDeserializer(String.class, new ScreeningResultDeserializer());
        module.addDeserializer(String.class, new EmailrDeserializer());
        mapper.registerModule(module);


        SobAppApiMain newApplicationFromBuilder;
        try {
            newApplicationFromBuilder = mapper.readValue(fileContent, SobAppApiMain.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


        ObjectMapper objectMapper = new ObjectMapper();
        String jsonConvertAppResult;
        try {
            jsonConvertAppResult = objectMapper.writeValueAsString(newApplicationFromBuilder);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

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
