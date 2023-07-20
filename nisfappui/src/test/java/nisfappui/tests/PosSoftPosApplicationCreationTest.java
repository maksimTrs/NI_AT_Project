package nisfappui.tests;

import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import io.qameta.allure.testng.Tags;
import org.testng.annotations.Test;

import static io.qameta.allure.Allure.step;
import static nisfappui.pages.NavigationMenuPartitions.APPLICATIONS;
import static nisfappui.services.ApplicationProductTypes.ECOM;
import static nisfappui.services.BankTypes.ENBD;
import static nisfappui.services.CityTypes.DUBAI;
import static nisfappui.services.CountryTypes.UAE;
import static nisfappui.services.LegalTypeTypes.LLC;
import static nisfappui.services.NationalityTypes.NATIONALITY_UAE;
import static nisfappui.services.PaymentModeTypes.MC_777;
import static nisfappui.services.PosTypeAndGatewayTypes.POS_TYPE_SOFT;
import static nisfappui.services.RentalModeTypes.BANK_TRANSFER;
import static nisfappui.services.TestHelper.*;
import static nisfappui.utils.AppDataFaker.*;


/**
 * need to add steps to update softpos product page: POS BRAND and POS MODEL
 * Error within the Product please select POS Brand that is suitable for Soft POS
 **/

public class PosSoftPosApplicationCreationTest extends BaseTest {


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


        step("Open Application tab and click on 'New' btn", () -> {
            mainSFAppPage
                    .clickOnNavigationMenuType()
                    .chooseOnNavigationMenuType(APPLICATIONS)
                    .clickOnNewAppBtn();
        });

        step("Open 'NEW MERCHANT:NEW APPLICATION' window and fill mandatory fields with POS type = SOFTPOS", () -> {
            merchantInitialCreationPage
                    .fillTradeName(getRandomTradeName())
                    .fillMerchantEmail(getRandomEmail())
                    .chooseBusinessNatureType("Accounting Services")
                    // .chooseRandomBusinessNatureType()
                    .unselectSelectedProduct(ECOM.getDisplayName())
                    .selectPosType(POS_TYPE_SOFT.getDisplayName())
                    //.setNumberOfPos(getRandomIntValue(1, 13))
                    .moveToTheSecondApplicationScreen();
        });


        step("Open 'New Application: New' window and fill 'Merchant Information' partition", () -> {
            newApplicationMerchantInformationPartitionPage
                    .fillPhone(getRandomPhone())
                    .fillLegalType(LLC.getDisplayName())
                    .fillPOBox(getRandomPOBox())
                    .fillAddress(getRandomAddress())
                    .fillCity(DUBAI.getDisplayName())
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

        step("Open 'New Application: New' window and fill 'Fees and Charges' partition", () -> {
            newApplicationFeesChargesPartitionPage
                    .fillMisMonthReportFee(getRandomDoubleValue(9, 999));
        });


        step("Open 'New Application: New' window and fill 'Business Details - KYC Profile Form", () -> {
            newApplicationBusinessDetailsPartitionPage
                    .fillBusinessLine("Insurance")
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
                    .uploadDocFileViaPopUp(DOC_FILE_UPLOAD_PATH)
                    .closeDocPartitionAndMoveToAppPage();
        });

        step("Assert APP ID, check the latest created test Application after Document Page redirection", () -> {
            applicationPage
                    .assertAppIdFromDocumentPageReturning();
        });

        step("Assert filled Application IBAN partition", () -> {
            applicationPage
                    .assertFilledAppPageIban();
        });
    }
}
