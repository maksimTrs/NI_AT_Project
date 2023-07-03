package nisfapp.tests;

import org.testng.annotations.Test;

import static nisfapp.pages.NavigationMenuPartitions.APPLICATIONS;
import static nisfapp.services.ApplicationProductTypes.ECOM;
import static nisfapp.services.BankTypes.ENBD;
import static nisfapp.services.CityTypes.DUBAI;
import static nisfapp.services.CountryTypes.UAE;
import static nisfapp.services.LegalTypeTypes.SOLE_PROPRIETOR;
import static nisfapp.services.NationalityTypes.NATIONALITY_UAE;
import static nisfapp.services.PaymentModeTypes.MC_777;
import static nisfapp.services.PosTypeAndGatewayTypes.POS_TYPE_SOFT;
import static nisfapp.services.RentalModeTypes.BANK_TRANSFER;
import static nisfapp.services.TestHelper.*;
import static nisfapp.utils.AppDataFaker.*;


/**
 * need to add steps to update softpos product page: POS BRAND and POS MODEL
 * Error within the Product please select POS Brand that is suitable for Soft POS
 **/

public class PosSoftPosApplicationCreationTest extends BaseTest {

    @Test
    public void createSoftPosApplicationTest() {

        doSFLogIn();
        mainSFAppPage
                .clickOnNavigationMenuType()
                .chooseOnNavigationMenuType(APPLICATIONS)
                .clickOnNewAppBtn();

        merchantInitialCreationPage
                .fillTradeName(getRandomTradeName())
                .fillMerchantEmail(getRandomEmail())
                .chooseBusinessNatureType("Accounting Services")
                // .chooseRandomBusinessNatureType()
                .unselectSelectedProduct(ECOM.getDisplayName())
                .selectPosType(POS_TYPE_SOFT.getDisplayName())
                //.setNumberOfPos(getRandomIntValue(1, 13))
                .moveToTheSecondApplicationScreen();

        newApplicationMerchantInformationPartitionPage
                .fillPhone(getRandomPhone())
                .fillLegalType(SOLE_PROPRIETOR.getDisplayName())
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
                .fillBusinessLine("Insurance")
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
