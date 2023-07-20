package nisfappui.tests;


import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import io.qameta.allure.testng.Tags;
import org.testng.annotations.Test;

import static io.qameta.allure.Allure.step;
import static nisfappui.pages.NavigationMenuPartitions.APPLICATIONS;
import static nisfappui.services.BankTypes.ENBD;
import static nisfappui.services.CityTypes.ABU_DHABI;
import static nisfappui.services.CountryTypes.UAE;
import static nisfappui.services.LegalTypeTypes.LLC;
import static nisfappui.services.NationalityTypes.NATIONALITY_UAE;
import static nisfappui.services.NgOnlineIntegrationMethodTypes.NHR;
import static nisfappui.services.NgOnlinePaymentTypes.MASTERCARD;
import static nisfappui.services.NgOnlinePaymentTypes.VISA;
import static nisfappui.services.PaymentModeTypes.MC_777;
import static nisfappui.services.PosTypeAndGatewayTypes.ECON_TYPE;
import static nisfappui.services.PosTypeAndGatewayTypes.POS_TYPE_NG;
import static nisfappui.services.RefundCategoryTypes.C;
import static nisfappui.services.RentalModeTypes.BANK_TRANSFER;
import static nisfappui.services.SettlementFrequencyEcomTypes.DAILY;
import static nisfappui.constants.TestHelper.*;
import static nisfappui.utils.AllureStepsTemplates.*;
import static nisfappui.utils.AppDataFaker.*;
import static nisfappui.utils.MethodAssertionsForPO.*;

public class Type3ApplicationCreationTest extends BaseTest {

    @Severity(SeverityLevel.NORMAL)
    @Owner("Maksim T")
    @Tags({@Tag("UI_TEST"), @Tag("SMOKE_TEST")})
    @Description("Create TYPE3 application in the 'Draft' stage. Fill App Contact PEP info and upload Document file")
    @Epic("SF Application Testing")
    @Feature("Create TYPE3 (ECOM + POS) Application in SF UI")
    @Story("Type3 Application Creation Test")
    @Test(groups = {"SmokeTest"})
    public void createType3ApplicationTest() {

        doSFLogIn(SF_URL, SALES_OFFICER_USER);

        step(APP_TAB_STEP, () -> {

            mainSFAppPage
                    .clickOnNavigationMenuType()
                    .chooseOnNavigationMenuType(APPLICATIONS)
                    .clickOnNewAppBtn();
        });


        step(NEW_APP_TAB_TYPE3_STEP, () -> {

            merchantInitialCreationPage
                    .fillTradeName(getRandomTradeName())
                    .fillMerchantEmail(getRandomEmail())
                    .chooseBusinessNatureType("Mail Order")
                    .selectPosType(POS_TYPE_NG.getDisplayName())
                    .selectEcomType(ECON_TYPE.getDisplayName())
                    .moveToTheSecondApplicationScreen();
        });


        step(NEW_APP_TAB_MERCH_INFO_STEP, () -> {

            newApplicationMerchantInformationPartitionPage
                    .fillPhone(getRandomPhone())
                    .fillLegalType(LLC.getDisplayName())
                    .fillWebsiteEcomOrPos(WEBSITE_ECOM, getRandomWebURL())
                    .fillPOBox(getRandomPOBox())
                    .fillAddress(getRandomAddress())
                    .fillCity(ABU_DHABI.getDisplayName())
                    .fillCountry(UAE.getDisplayName())
                    .fillTradeLicenceNumber(getRandomTLN())
                    .fillDateEstablishment(getRandomDateOfEstablishment())
                    .fillDateLicenceExpiration(getRandomLicenceExpirationDate());
        });

        step(NEW_APP_TAB_AUTH_SIGN_STEP, () -> {

            newApplicationAuthorizedSignatoryPartitionPage
                    .fillFirstAndLastName(getRandomFirstName(), getRandomLastName())
                    .fillMobilPhone(getRandomPhone())
                    .fillPassportNum(getRandomPassport())
                    .fillNationality(NATIONALITY_UAE.getDisplayName())
                    .fillContactBirthday(getRandomDateOfBirth())
                    .fillPassportExpDate(getRandomLicenceExpirationDate());
        });


        step(NEW_APP_TAB_SALES_OFFICER_STEP, () -> {

            newApplicationSalesOfficerInspectionPartitionPage
                    .clickOnOriginalDocumentImageVerifiedCheckBox(true);
        });


        step(NEW_APP_TAB_PAYMENT_SETTL_STEP, () -> {

            newApplicationPaymentSettlementDetailsPartitionPage
                    .fillBankName(ENBD.getDisplayName())
                    .fillPaymentMode(MC_777.getDisplayName())
                    .fillRentalMode(BANK_TRANSFER.getDisplayName())
                    .fillTaxRegNum(getRandomTLN());
        });


        step(NEW_APP_TAB_FEES_POS_STEP, () -> {

            newApplicationFeesChargesPartitionPage
                    .fillMisMonthReportFee(getRandomDoubleValue(10, 1001));
        });


        step(NEW_APP_TAB_FEES_ECOM_STEP, () -> {

            newApplicationFeesChargesEcomPartitionPage
                    .fillSettlementFreqEcomOption(DAILY.getDisplayName())
                    .fillRefundCategory(C.getDisplayName());
        });


        step(NEW_APP_TAB_NG_ONLINE_STEP, () -> {

            nGeniusOnlinePartitionPage
                    .clickOnWebIntegrationCheckbox(true)
                    .clickOnPayByLinkCheckbox(true)
                    .fillNumberTransactionsAnnual(getRandomIntValue(1000, 10000))
                    .fillNumberEcomAnnualValue(getRandomIntValue(100, 1000))
                    .fillIntegrationMethod(NHR.getDisplayName())
                    .selectCardPaymentType(VISA.getDisplayName())
                    .selectCardPaymentType(MASTERCARD.getDisplayName());
        });


        step(NEW_APP_TAB_BUSINESS_DETAIL_STEP, () -> {

            newApplicationBusinessDetailsPartitionPage
                    .fillBusinessLine("Door-to-Door Sales")
                    .fillDescOfBusinessOperation(BUSINESS_OPERATION_DESC)
                    .fillYearsInBusiness(getRandomIntValue(1, 15))
                    .fillVolumePerYear(getRandomIntValue(1000, 5000))
                    .fillCardPerYear(getRandomIntValue(100, 1000))
                    .clickOnNewAppSaveBtn();
        });


        step(APP_TAB_IBAN_STEP, () -> {

            applicationPage
                    .openCurrentSFAppTab()
                    .fillType3BusinessSensitivePartition(IBAN_VALUE, ACCOUNT_NUMBER_VALUE);
        });


        step(APP_TAB_ASSERT_APP_STEP, () -> {

            assertElementHasMatches(applicationPage.getApplicationPrimaryId(), "A-\\d{9,}");
            assertElementContainsText(applicationPage.getApplicationTradeName(), "AT TEST");
            assertElementHasText(applicationPage.getDraftStageIsChosenState().get(0), "true");
            assertElementHasText(applicationPage.getDraftStageIsChosenState().get(1), "true");
        });


        step(CONTACT_PAGE_PEP_STEP, () -> {

            applicationPage
                    .openAppContactPage()
                    .editContact()
                    .editPepField(false)
                    .saveContact()
                    .moveToAppPage();
        });


        step(CONTACT_PAGE_ASSERT_APP_ID_STEP, () -> {

            assertElementHasText(applicationPage.getAppIdFromContactPageReturning(), applicationPage.getAppSFID());
        });


        step(DOC_TAB_UPLOAD_DOC_FILE_STEP, () -> {

            applicationPage
                    .openAppGenericDocument();

            documentPage
                    .clickOnUploadDocFilesBtn()
                    .uploadDocFileViaPopUp(DOC_FILE_UPLOAD_PATH)
                    .closeDocPartitionAndMoveToAppPage();
        });


        step(DOC_TAB_ASSERT_APP_ID_STEP, () -> {

            assertElementHasText(applicationPage.getAppIdFromDocumentPageReturning(), applicationPage.getAppSFID());
        });

        step(APP_TAB_ASSERT_IBAN_STEP, () -> {

            assertElementHasText(applicationPage.getFilledType3AppPageIban(), IBAN_VALUE);
            assertElementHasText(applicationPage.getFilledType3AppPageIbanAccNumber(), ACCOUNT_NUMBER_VALUE);
        });
    }
}
