package nisfapp.tests;


import org.testng.annotations.Test;

import static nisfapp.pages.NavigationMenuPartitions.APPLICATIONS;
import static nisfapp.services.ApplicationProductTypes.ECOM;
import static nisfapp.services.BankTypes.ENBD;
import static nisfapp.services.CityTypes.DUBAI;
import static nisfapp.services.CountryTypes.UAE;
import static nisfapp.services.LegalTypeTypes.LLC;
import static nisfapp.services.NationalityTypes.NATIONALITY_UAE;
import static nisfapp.services.PaymentModeTypes.MC_777;
import static nisfapp.services.PosTypeAndGatewayTypes.POS_TYPE_NG;
import static nisfapp.services.RentalModeTypes.BANK_TRANSFER;
import static nisfapp.services.TestHelper.*;
import static nisfapp.utils.AppDataFaker.*;

public class PosNgeniusApplicationCreationTest extends BaseTest {


    @Test
    public void createNgeniusPosApplicationTest() {

        doSFLogIn(SF_URL, SALES_OFFICER_USER);
        mainSFAppPage
                .clickOnNavigationMenuType()
                .chooseOnNavigationMenuType(APPLICATIONS)
                .clickOnNewAppBtn();

        merchantInitialCreationPage
                .fillTradeName(getRandomTradeName())
                .fillMerchantEmail(getRandomEmail())
                .chooseBusinessNatureType("Legal Services")
                // .chooseRandomBusinessNatureType()
                .unselectSelectedProduct(ECOM.getDisplayName())
                .selectPosType(POS_TYPE_NG.getDisplayName())
                .setNumberOfPos(getRandomIntValue(1, 13))
                .moveToTheSecondApplicationScreen();

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

        newApplicationBusinessDetailsPartitionPage
                .fillBusinessLine("Fines")
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


        //applicationPage.submitToNextStage();
    }
}
