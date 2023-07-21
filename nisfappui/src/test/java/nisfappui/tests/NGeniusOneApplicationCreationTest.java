package nisfappui.tests;


import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import io.qameta.allure.testng.Tags;
import nisfappui.utils.TestBuilderForPosAndEcom;
import nisfappui.utils.TestBuilderMain;
import org.testng.annotations.Test;

import static io.qameta.allure.Allure.step;
import static nisfappui.constants.TestHelper.*;
import static nisfappui.pages.NavigationMenuPartitions.APPLICATIONS;
import static nisfappui.services.BankTypes.ENBD;
import static nisfappui.services.CityTypes.ABU_DHABI;
import static nisfappui.services.CountryTypes.UAE;
import static nisfappui.services.LegalTypeTypes.LLC;
import static nisfappui.services.NationalityTypes.NATIONALITY_UAE;
import static nisfappui.services.NgOnlineIntegrationMethodTypes.NHR;
import static nisfappui.services.NgOnlinePaymentTypes.MASTERCARD;
import static nisfappui.services.NgOnlinePaymentTypes.VISA;
import static nisfappui.services.PaymentModeTypes.FN;
import static nisfappui.services.PaymentModeTypes.MC_777;
import static nisfappui.services.RefundCategoryTypes.C;
import static nisfappui.services.RentalModeTypes.BANK_TRANSFER;
import static nisfappui.services.SettlementFrequencyEcomTypes.DAILY;
import static nisfappui.utils.AllureStepsTemplates.*;
import static nisfappui.utils.AppDataFaker.*;
import static nisfappui.utils.MethodAssertionsForPO.*;

public class NGeniusOneApplicationCreationTest extends BaseTest {

    private TestBuilderForPosAndEcom testBuilderForPosAndEcom = new TestBuilderForPosAndEcom();
    private TestBuilderMain testBuilderMain = new TestBuilderMain();


    @Severity(SeverityLevel.CRITICAL)
    @Owner("Maksim T")
    @Tags({@Tag("UI_TEST"), @Tag("SMOKE_TEST")})
    @Description("Create NGeniusOne application in the 'Draft' stage. Fill App Contact PEP info and upload Document file")
    @Epic("SF Application Testing")
    @Feature("Create NGeniusOne (PBQ, TTP, PBL) Application in SF UI")
    @Story("NGeniusOne Application Creation Test")
    @Test(groups = {"SmokeTest"})
    public void createNGeniusOneApplicationTest() {


        doSFLogIn(SF_URL, SALES_OFFICER_USER);


        step(APP_TAB_STEP, () -> {

            testBuilderMain.openNewMerchantPopUp(mainSFAppPage, APPLICATIONS);
        });

        step(NEW_APP_TAB_NG1_STEP, () -> {

            testBuilderForPosAndEcom.fillMerchantInitialCreationNG1WithBase24(merchantInitialCreationPage, "Car Rental");
        });


        step(NEW_APP_TAB_MERCH_INFO_STEP, () -> {

            testBuilderForPosAndEcom.fillMerchantInformationPartitionECOM(newApplicationMerchantInformationPartitionPage,
                    LLC, WEBSITE_ECOM, ABU_DHABI, UAE);
        });


        step(NEW_APP_TAB_AUTH_SIGN_STEP, () -> {

            testBuilderMain.fillApplicationAuthorizedSignatoryPartition(newApplicationAuthorizedSignatoryPartitionPage, NATIONALITY_UAE);
        });


        step(NEW_APP_TAB_SALES_OFFICER_STEP, () -> {

            testBuilderMain.fillApplicationSalesOfficerInspectionPartition(newApplicationSalesOfficerInspectionPartitionPage, true);
        });


        step(NEW_APP_TAB_PAYMENT_SETTL_STEP, () -> {

            testBuilderMain.fillApplicationPaymentSettlementDetailsPartition(newApplicationPaymentSettlementDetailsPartitionPage,
                    ENBD, FN, BANK_TRANSFER);
        });


/*        step(NEW_APP_TAB_FEES_POS_STEP, () -> {

            newApplicationFeesChargesPartitionPage
                    .fillMisMonthReportFee(getRandomDoubleValue(10, 1001));
        });*/


        step(NEW_APP_TAB_FEES_ECOM_STEP, () -> {

            testBuilderForPosAndEcom.fillApplicationFeesChargesEcomPartition(newApplicationFeesChargesEcomPartitionPage,
                    DAILY, C);
        });


        step(NEW_APP_TAB_NG_ONLINE_STEP, () -> {

            testBuilderForPosAndEcom.fillNGeniusOnlinePartitionPage(nGeniusOnlinePartitionPage,
                    true, true, NHR, VISA, MASTERCARD);
        });


        step(NEW_APP_TAB_BUSINESS_DETAIL_STEP, () -> {

            testBuilderMain.fillApplicationBusinessDetailsPartition(newApplicationBusinessDetailsPartitionPage,
                    "Truck Rental", BUSINESS_OPERATION_DESC);
        });


        step(APP_TAB_IBAN_STEP, () -> {

            testBuilderMain.fillApplicationIBAN(applicationPage, IBAN_VALUE, ACCOUNT_NUMBER_VALUE);
        });


        step(APP_TAB_ASSERT_APP_STEP, () -> {

            assertElementHasMatches(applicationPage.getApplicationPrimaryId(), "A-\\d{9,}");
            assertElementContainsText(applicationPage.getApplicationTradeName(), "AT TEST");
            assertElementHasText(applicationPage.getDraftStageIsChosenState().get(0), "true");
            assertElementHasText(applicationPage.getDraftStageIsChosenState().get(1), "true");
        });


        step(CONTACT_PAGE_PEP_STEP, () -> {

            testBuilderMain.openAccountPageAndFillPepContactInfo(applicationPage, false);
        });


        step(CONTACT_PAGE_ASSERT_APP_ID_STEP, () -> {

            assertElementHasText(applicationPage.getAppIdFromContactPageReturning(), applicationPage.getAppSFID());
        });


        step(DOC_TAB_UPLOAD_DOC_FILE_STEP, () -> {

            testBuilderMain.openDocPageAndUploadMultiplyFiles(applicationPage, documentPage,
                    DOC_FILE_UPLOAD_PATH, DOC_FILE_UPLOAD_PATH2);
        });


        step(DOC_TAB_ASSERT_APP_ID_STEP, () -> {

            assertElementHasText(applicationPage.getAppIdFromDocumentPageReturning(), applicationPage.getAppSFID());
        });

        step(APP_TAB_ASSERT_IBAN_STEP, () -> {

            assertElementHasText(applicationPage.getFilledAppPageIban(), IBAN_VALUE);
            assertElementHasText(applicationPage.getFilledAppPageIbanAccountNumber(), ACCOUNT_NUMBER_VALUE);
        });
    }
}
