package nisfapp.tests;


import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import io.qameta.allure.testng.Tags;
import org.testng.annotations.Test;

import static io.qameta.allure.Allure.step;
import static nisfapp.pages.NavigationMenuPartitions.APPLICATIONS;
import static nisfapp.services.ApplicationProductTypes.*;
import static nisfapp.services.BankTypes.ENBD;
import static nisfapp.services.CityTypes.ABU_DHABI;
import static nisfapp.services.CountryTypes.UAE;
import static nisfapp.services.LegalTypeTypes.LLC;
import static nisfapp.services.NationalityTypes.NATIONALITY_UAE;
import static nisfapp.services.NgOnlineIntegrationMethodTypes.NHR;
import static nisfapp.services.NgOnlinePaymentTypes.MASTERCARD;
import static nisfapp.services.NgOnlinePaymentTypes.VISA;
import static nisfapp.services.PaymentModeTypes.MC_777;
import static nisfapp.services.RefundCategoryTypes.C;
import static nisfapp.services.RentalModeTypes.BANK_TRANSFER;
import static nisfapp.services.SettlementFrequencyEcomTypes.DAILY;
import static nisfapp.services.TestHelper.*;
import static nisfapp.utils.AppDataFaker.*;

public class NGeniusOneApplicationCreationTest extends BaseTest {


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


        step("Open Application tab and click on 'New' btn", () -> {
            mainSFAppPage
                    .clickOnNavigationMenuType()
                    .chooseOnNavigationMenuType(APPLICATIONS)
                    .clickOnNewAppBtn();
        });

        step("Open 'NEW MERCHANT:NEW APPLICATION' window and fill mandatory fields for NG1 product", () -> {
            merchantInitialCreationPage
                    .fillTradeName(getRandomTradeName())
                    .fillMerchantEmail(getRandomEmail())
                    .chooseBusinessNatureType("Car Rental")
                    .unselectSelectedProduct(POS.getDisplayName())
                    .unselectSelectedProduct(ECOM.getDisplayName())
                    .selectAvailableProduct(NG1.getDisplayName())
                    .moveToTheSecondApplicationScreen();
        });


        step("Open 'New Application: New' window and fill 'Merchant Information' partition", () -> {
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


        step("Open 'New Application: New' window and fill 'Authorized Signatory details' partition", () -> {
            newApplicationAuthorizedSignatoryPartitionPage
                    .fillFirstAndLastName(getRandomFirstName(), getRandomLastName())
                    .fillMobilPhone(getRandomPhone())
                    .fillPassportNum(getRandomPassport())
                    .fillNationality(NATIONALITY_UAE.getDisplayName())
                    .fillContactBirthday(getRandomDateOfBirth())
                    .fillPassportExpDate(getRandomLicenceExpirationDate());
        });


        step("Open 'New Application: New' window and fill 'Sales Officer Inspection Report' partition", () -> {
            newApplicationSalesOfficerInspectionPartitionPage
                    .clickOnOriginalDocumentImageVerifiedCheckBox(true);
        });


        step("Open 'New Application: New' window and fill 'Payment and Settlement Details' partition", () -> {
            newApplicationPaymentSettlementDetailsPartitionPage
                    .fillBankName(ENBD.getDisplayName())
                    .fillPaymentMode(MC_777.getDisplayName())
                    .fillRentalMode(BANK_TRANSFER.getDisplayName())
                    .fillTaxRegNum(getRandomTLN());
        });


/*        step("Open 'New Application: New' window and fill 'Fees and Charges' partition", () -> {
            newApplicationFeesChargesPartitionPage
                    .fillMisMonthReportFee(getRandomDoubleValue(10, 1001));
        });*/


        step("Open 'New Application: New' window and fill 'Fees and Charges (Ecom)' partition", () -> {
            newApplicationFeesChargesEcomPartitionPage
                    .fillSettlementFreqEcomOption(DAILY.getDisplayName())
                    .fillRefundCategory(C.getDisplayName());
        });


        step("Open 'New Application: New' window and fill 'N-Genius Online' partition", () -> {
            nGeniusOnlinePartitionPage
                    .clickOnWebIntegrationCheckbox(true)
                    .clickOnPayByLinkCheckbox(true)
                    .fillNumberTransactionsAnnual(getRandomIntValue(10000, 100000))
                    .fillNumberEcomAnnualValue(getRandomIntValue(100, 10000))
                    .fillIntegrationMethod(NHR.getDisplayName())
                    .selectCardPaymentType(VISA.getDisplayName())
                    .selectCardPaymentType(MASTERCARD.getDisplayName());
        });


        step("Open 'New Application: New' window and fill 'Business Details - KYC Profile Form", () -> {
            newApplicationBusinessDetailsPartitionPage
                    .fillBusinessLine("Truck Rental")
                    .fillDescOfBusinessOperation(BUSINESS_OPERATION_DESC)
                    .fillYearsInBusiness(getRandomIntValue(1, 15))
                    .fillVolumePerYear(getRandomIntValue(10000, 50000))
                    .fillCardPerYear(getRandomIntValue(100, 10000))
                    .clickOnNewAppSaveBtn();
        });


        step("Open new create Application tab and fill IBAN value", () -> {
            applicationPage
                    .openCurrentSFAppTab()
                    .fillBusinessSensitivePartition(IBAN_VALUE, ACCOUNT_NUMBER_VALUE);
        });


        step("Assert new application: validate APP ID, Trade Name, Draft Stage", () -> {
            applicationPage
                    .assertApplicationPrimaryId()
                    .assertApplicationTradeName()
                    .assertDraftStageIsChosen();
        });


        step("Open Application Contact Page and fill field PEP=No. Move to the App page", () -> {
            applicationPage
                    .openAppContactPage()
                    .editContact()
                    .editPepField(false)
                    .saveContact()
                    .moveToAppPage();
        });


        step("Assert APP ID, check the latest created test Application after Contact Page redirection", () -> {
            applicationPage
                    .assertAppIdFromContactPageReturning();
        });


        step("Open initial Application Document and upload document file. Go back to the initial application.", () -> {
            applicationPage
                    .openAppGenericDocument();

            documentPage
                    .clickOnUploadDocFilesBtn()
                    //.uploadDocFileViaPopUp(DOC_FILE_UPLOAD_PATH)
                    .uploadDocFilesViaPopUp(DOC_FILE_UPLOAD_PATH, DOC_FILE_UPLOAD_PATH2)
                    .closeDocPartitionAndMoveToAppPage();
        });


        step("Assert APP ID, check the latest created test Application after Document Page redirection", () -> {
            applicationPage
                    .assertAppIdFromDocumentPageReturning();
        });

    }
}
