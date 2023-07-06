package nisfapp.tests;


import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import io.qameta.allure.testng.Tags;
import org.testng.annotations.Test;

import static nisfapp.pages.NavigationMenuPartitions.APPLICATIONS;
import static nisfapp.services.ApplicationProductTypes.POS;
import static nisfapp.services.BankTypes.ENBD;
import static nisfapp.services.CityTypes.ABU_DHABI;
import static nisfapp.services.CountryTypes.UAE;
import static nisfapp.services.LegalTypeTypes.LLC;
import static nisfapp.services.NationalityTypes.NATIONALITY_UAE;
import static nisfapp.services.NgOnlineIntegrationMethodTypes.NHR;
import static nisfapp.services.NgOnlinePaymentTypes.MASTERCARD;
import static nisfapp.services.NgOnlinePaymentTypes.VISA;
import static nisfapp.services.PaymentModeTypes.MC_777;
import static nisfapp.services.PosTypeAndGatewayTypes.ECON_TYPE;
import static nisfapp.services.PosTypeAndGatewayTypes.POS_TYPE_NG;
import static nisfapp.services.RefundCategoryTypes.C;
import static nisfapp.services.RentalModeTypes.BANK_TRANSFER;
import static nisfapp.services.SettlementFrequencyEcomTypes.DAILY;
import static nisfapp.services.TestHelper.*;
import static nisfapp.services.TestHelper.ACCOUNT_NUMBER_VALUE;
import static nisfapp.utils.AppDataFaker.*;

public class Type3ApplicationCreationTest extends  BaseTest {

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

        mainSFAppPage
                .clickOnNavigationMenuType()
                .chooseOnNavigationMenuType(APPLICATIONS)
                .clickOnNewAppBtn();

        merchantInitialCreationPage
                .fillTradeName(getRandomTradeName())
                .fillMerchantEmail(getRandomEmail())
                .chooseBusinessNatureType("Mail Order")
                .selectPosType(POS_TYPE_NG.getDisplayName())
                .selectEcomType(ECON_TYPE.getDisplayName())
                .moveToTheSecondApplicationScreen();


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


        newApplicationAuthorizedSignatoryPartitionPage
                .fillFirstAndLastName(getRandomFirstName(), getRandomLastName())
                .fillMobilPhone(getRandomPhone())
                .fillPassportNum(getRandomPassport())
                .fillNationality(NATIONALITY_UAE.getDisplayName())
                .fillContactBirthday(getRandomDateOfBirth())
                .fillPassportExpDate(getRandomLicenceExpirationDate());

        newApplicationSalesOfficerInspectionPartitionPage
                .clickOnOriginalDocumentImageVerifiedCheckBox(true);


        newApplicationPaymentSettlementDetailsPartitionPage
                .fillBankName(ENBD.getDisplayName())
                .fillPaymentMode(MC_777.getDisplayName())
                .fillRentalMode(BANK_TRANSFER.getDisplayName())
                .fillTaxRegNum(getRandomTLN());


        newApplicationFeesChargesPartitionPage
                .fillMisMonthReportFee(getRandomDoubleValue(10, 1001));


        newApplicationFeesChargesEcomPartitionPage
                .fillSettlementFreqEcomOption(DAILY.getDisplayName())
                .fillRefundCategory(C.getDisplayName());

        nGeniusOnlinePartitionPage
                .clickOnWebIntegrationCheckbox(true)
                .clickOnPayByLinkCheckbox(true)
                .fillNumberTransactionsAnnual(getRandomIntValue(1000, 100000))
                .fillNumberEcomAnnualValue(getRandomIntValue(1000, 100000))
                .fillIntegrationMethod(NHR.getDisplayName())
                .selectCardPaymentType(VISA.getDisplayName())
                .selectCardPaymentType(MASTERCARD.getDisplayName());

        newApplicationBusinessDetailsPartitionPage
                .fillBusinessLine("Door-to-Door Sales")
                .fillDescOfBusinessOperation(BUSINESS_OPERATION_DESC)
                .fillYearsInBusiness(getRandomIntValue(1, 15))
                .fillVolumePerYear(getRandomIntValue(1000, 500000))
                .fillCardPerYear(getRandomIntValue(100, 10000))
                .clickOnNewAppSaveBtn();

        applicationPage
                .openCurrentSFAppTab()
                .assertApplicationPrimaryId()
                .assertApplicationTradeName()
                .assertDraftStageIsChosen();

        applicationPage
                .fillType3BusinessSensitivePartition(IBAN_VALUE, ACCOUNT_NUMBER_VALUE);

        applicationPage
                .openAppContactPage()
                .editContact()
                .editPepField(false)
                .saveContact()
                .moveToAppPage();

        applicationPage
                .assertAppIdFromContactPageReturning();

        applicationPage
                .openAppGenericDocument();

        documentPage
                .clickOnUploadDocFilesBtn()
                .uploadDocFileViaPopUp(DOC_FILE_UPLOAD_PATH)
                .closeDocPartitionAndMoveToAppPage();

        applicationPage
                .assertAppIdFromDocumentPageReturning();

    }

}