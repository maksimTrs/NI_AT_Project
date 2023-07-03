package nisfapp.tests;

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
import static nisfapp.services.PaymentModeTypes.MC_871;
import static nisfapp.services.PosTypeAndGatewayTypes.ECON_TYPE;
import static nisfapp.services.RefundCategoryTypes.C;
import static nisfapp.services.RentalModeTypes.PAY_BY_LINK;
import static nisfapp.services.SettlementFrequencyEcomTypes.DAILY;
import static nisfapp.services.TestHelper.*;
import static nisfapp.utils.AppDataFaker.*;

public class EcomApplicationCreationTest extends BaseTest {


    @Test
    public void createECOMApplicationTest() {

        doSFLogIn();
        mainSFAppPage
                .clickOnNavigationMenuType()
                .chooseOnNavigationMenuType(APPLICATIONS)
                .clickOnNewAppBtn();

        merchantInitialCreationPage
                .fillTradeName(getRandomTradeName())
                .fillMerchantEmail(getRandomEmail())
                .chooseBusinessNatureType("Car Rental")
                // .chooseRandomBusinessNatureType()
                .unselectSelectedProduct(POS.getDisplayName())
                .selectEcomType(ECON_TYPE.getDisplayName())
                //.setNumberOfPos(getRandomIntValue(1, 13))
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
                .fillPaymentMode(MC_871.getDisplayName())
                .fillRentalMode(PAY_BY_LINK.getDisplayName())
                .fillTaxRegNum(getRandomTLN());


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
                .fillBusinessLine("Truck Rental")
                .fillDescOfBusinessOperation(BUSINESS_OPERATION_DESC)
                .fillYearsInBusiness(getRandomIntValue(1, 15))
                .fillVolumePerYear(getRandomIntValue(1000, 500000))
                .fillCardPerYear(getRandomIntValue(100, 10000))
                .clickOnNewAppSaveBtn();


        applicationPage
                .openCurrentSFAppTab()
                .fillBusinessSensitivePartition(IBAN_VALUE, ACCOUNT_NUMBER_VALUE);

        applicationPage
                .assertApplicationPrimaryId()
                .assertApplicationTradeName()
                .assertDraftStageIsChosen();

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


        // applicationPage.submitToNextStage();

    }
}
