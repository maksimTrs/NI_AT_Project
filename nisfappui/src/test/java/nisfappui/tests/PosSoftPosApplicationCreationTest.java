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
import static nisfappui.services.CityTypes.DUBAI;
import static nisfappui.services.CountryTypes.UAE;
import static nisfappui.services.LegalTypeTypes.LLC;
import static nisfappui.services.NationalityTypes.NATIONALITY_UAE;
import static nisfappui.services.PaymentModeTypes.MC_871;
import static nisfappui.services.RentalModeTypes.BANK_TRANSFER;
import static nisfappui.utils.AllureStepsTemplates.*;
import static nisfappui.utils.MethodAssertionsForPO.*;


/**
 * need to add steps to update softpos product page: POS BRAND and POS MODEL
 * Error within the Product please select POS Brand that is suitable for Soft POS
 **/

public class PosSoftPosApplicationCreationTest extends BaseTest {

    private TestBuilderForPosAndEcom testBuilderForPosAndEcom = new TestBuilderForPosAndEcom();
    private TestBuilderMain testBuilderMain = new TestBuilderMain();


    @Severity(SeverityLevel.CRITICAL)
    @Owner("Maksim T")
    @Tags({@Tag("UI_TEST"), @Tag("SMOKE_TEST")})
    @Description("Create POS application with type 'SoftPos' in the 'Draft' stage. Fill App Contact PEP info and upload Document file")
    @Epic("SF Application Testing")
    @Feature("Create POS Application in SF UI")
    @Story("POS 'SoftPos' Application Creation Test")
    @Test(groups = {"SmokeTest"})
    public void createSoftPosApplicationTest() {

        doSFLogIn(SF_URL, SALES_OFFICER_USER);


        step(APP_TAB_STEP, () -> {

            testBuilderMain.openNewMerchantPopUp(mainSFAppPage, APPLICATIONS);
        });

        step(NEW_APP_TAB_POS_SOFTPOS_STEP, () -> {

            testBuilderForPosAndEcom.fillMerchantInitialCreationPosSOFTPOS(merchantInitialCreationPage, "Accounting Services");
        });


        step(NEW_APP_TAB_MERCH_INFO_STEP, () -> {

            testBuilderForPosAndEcom.fillMerchantInformationPartitionECOM(newApplicationMerchantInformationPartitionPage,
                    LLC, WEBSITE, DUBAI, UAE);
        });

        step(NEW_APP_TAB_AUTH_SIGN_STEP, () -> {

            testBuilderMain.fillApplicationAuthorizedSignatoryPartition(newApplicationAuthorizedSignatoryPartitionPage, NATIONALITY_UAE);
        });


        step(NEW_APP_TAB_SALES_OFFICER_STEP, () -> {

            testBuilderMain.fillApplicationSalesOfficerInspectionPartition(newApplicationSalesOfficerInspectionPartitionPage, true);
        });


        step(NEW_APP_TAB_PAYMENT_SETTL_STEP, () -> {

            testBuilderMain.fillApplicationPaymentSettlementDetailsPartition(newApplicationPaymentSettlementDetailsPartitionPage,
                    ENBD, MC_871, BANK_TRANSFER);
        });


        step(NEW_APP_TAB_FEES_POS_STEP, () -> {

            testBuilderForPosAndEcom.fillApplicationFeesChargesPosPartition(newApplicationFeesChargesPartitionPage);
        });


        step(NEW_APP_TAB_BUSINESS_DETAIL_STEP, () -> {

            testBuilderMain.fillApplicationBusinessDetailsPartition(newApplicationBusinessDetailsPartitionPage,
                    "Insurance", BUSINESS_OPERATION_DESC);
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

            testBuilderMain.openDocPageAndUploadFile(applicationPage, documentPage, DOC_FILE_UPLOAD_PATH);
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
