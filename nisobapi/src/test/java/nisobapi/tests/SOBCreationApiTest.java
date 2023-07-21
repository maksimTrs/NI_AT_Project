package nisobapi.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import io.qameta.allure.Allure;
import nisobapi.pojo.Application;
import nisobapi.pojo.SanctionCountryInformation;
import nisobapi.pojo.SobAppApiMain;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static nisobapi.api.StatusCode.CODE_201;
import static nisobapi.constants.TestHelper.APP_ID_REGEX;
import static nisobapi.constants.TestHelper.SOB_CREATION_URL_ENDPOINT;
import static nisobapi.utils.ApiAppDataFaker.getRandomDateOfEstablishment;
import static nisobapi.utils.ApiAppDataFaker.getRandomIntValue;
import static nisobapi.utils.MethodAssertionsForAPI.*;
import static nisobapi.utils.SobAppDataBuilder.*;


public class SOBCreationApiTest extends BaseApiTest {

    private static Application buildApplication() {
        return Application.builder()
                .merchantType("SME")
                .keyMerchant("")
                .creator("SOB")
                .legalType("Sole Proprietor")
                .countryOfEstablishment("United Arab Emirates")
                .dateOfEstablishment(getRandomDateOfEstablishment())
                .businessNature("Books/Paper/Office Supplies")
                .businessLine("Stationery, Office Supplies, Printing and Writing Paper Sundries")
                .businessDescription("AT SOB TEST")
                .yearsInBusiness(String.valueOf(getRandomIntValue(1, 15)))
                .expectedVolumePerYear(String.valueOf(getRandomIntValue(1000, 50000)))
                .expectedCardVolume(String.valueOf(getRandomIntValue(100, 1000)))
                .paymentMode("FN")
                .rentalMode("DD")
                .screeningResult(SCREENING_RESULT_FORMAT)
                //.shareHolders(null)
                .tenantId("ni")
                .contacts(getContactsItems())
                .sanctionCountryInformation(SanctionCountryInformation
                        .builder()
                        .relatedpersonentityinSC(false)
                        .involveddealingsinSC(false)
                        .investmentsinSC(false)
                        .build())
                .merchants(getMerchantsItems())
                .build();
    }


    @Test()
    public void createSobAppTest() {

        SobAppApiMain newApplicationFromBuilder = SobAppApiMain
                .builder()
                .application(buildApplication())
                .build();


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
