package nisfappui.utils;

import nisfappui.pages.*;
import nisfappui.services.BankTypes;
import nisfappui.services.NationalityTypes;
import nisfappui.services.PaymentModeTypes;
import nisfappui.services.RentalModeTypes;

import static nisfappui.constants.TestHelper.*;
import static nisfappui.services.BankTypes.ENBD;
import static nisfappui.services.NationalityTypes.NATIONALITY_UAE;
import static nisfappui.services.PaymentModeTypes.MC_777;
import static nisfappui.services.RentalModeTypes.BANK_TRANSFER;
import static nisfappui.utils.AppDataFaker.*;
import static nisfappui.utils.AppDataFaker.getRandomLicenceExpirationDate;

public class TestBuilderMain {

    public void openNewMerchantPopUp(MainSFAppPage mainSFAppPage, NavigationMenuPartitions navigationMenuPartitions) {
        mainSFAppPage
                .clickOnNavigationMenuType()
                .chooseOnNavigationMenuType(navigationMenuPartitions)
                .clickOnNewAppBtn();
    }


    public void fillApplicationAuthorizedSignatoryPartition(NewApplicationAuthorizedSignatoryPartitionPage newApplicationAuthorizedSignatoryPartitionPage,
                                                             NationalityTypes nationalityTypes) {
        newApplicationAuthorizedSignatoryPartitionPage
                .fillFirstAndLastName(getRandomFirstName(), getRandomLastName())
                .fillMobilPhone(getRandomPhone())
                .fillPassportNum(getRandomPassport())
                .fillNationality(nationalityTypes.getDisplayName())
                .fillContactBirthday(getRandomDateOfBirth())
                .fillPassportExpDate(getRandomLicenceExpirationDate());
    }


    public void fillApplicationSalesOfficerInspectionPartition(NewApplicationSalesOfficerInspectionPartitionPage
                                                                       newApplicationSalesOfficerInspectionPartitionPage,
                                                               boolean checkCheckBox) {
        newApplicationSalesOfficerInspectionPartitionPage
                .clickOnOriginalDocumentImageVerifiedCheckBox(checkCheckBox);
    }

    public void fillApplicationPaymentSettlementDetailsPartition(NewApplicationPaymentSettlementDetailsPartitionPage
                                                                         newApplicationPaymentSettlementDetailsPartitionPage,
                                                                 BankTypes bankTypes,
                                                                 PaymentModeTypes paymentModeTypes,
                                                                 RentalModeTypes rentalModeTypes) {
        newApplicationPaymentSettlementDetailsPartitionPage
                .fillBankName(bankTypes.getDisplayName())
                .fillPaymentMode(paymentModeTypes.getDisplayName())
                .fillRentalMode(rentalModeTypes.getDisplayName())
                .fillTaxRegNum(getRandomTLN());
    }


    public void fillApplicationBusinessDetailsPartition(NewApplicationBusinessDetailsPartitionPage newApplicationBusinessDetailsPartitionPage,
                                                        String businessLine,
                                                        String businessDesc) {
        newApplicationBusinessDetailsPartitionPage
                .fillBusinessLine(businessLine)
                .fillDescOfBusinessOperation(businessDesc)
                .fillYearsInBusiness(getRandomIntValue(1, 15))
                .fillVolumePerYear(getRandomIntValue(10000, 50000))
                .fillCardPerYear(getRandomIntValue(100, 10000))
                .clickOnNewAppSaveBtn();
    }


    public void fillApplicationIBAN(ApplicationPage applicationPage, String iBan, String accNum) {

        applicationPage
                .openCurrentSFAppTab()
                .fillBusinessSensitivePartition(iBan, accNum);
    }

    public void fillApplicationType3IBAN(ApplicationPage applicationPage, String iBan, String accNum) {

        applicationPage
                .openCurrentSFAppTab()
                .fillType3BusinessSensitivePartition(iBan, accNum);
    }

    public void openAccountPageAndFillPepContactInfo(ApplicationPage applicationPage,boolean isPep) {
        applicationPage
                .openAppContactPage()
                .editContact()
                .editPepField(isPep)
                .saveContact()
                .moveToAppPage();
    }


    public void openDocPageAndUploadMultiplyFiles(ApplicationPage applicationPage,
                                                  DocumentPage documentPage,
                                                  String file1,
                                                  String file2) {
        applicationPage
                .openAppGenericDocument();

        documentPage
                .clickOnUploadDocFilesBtn()
                .uploadDocFilesViaPopUp(file1, file2)
                .closeDocPartitionAndMoveToAppPage();
    }

    public void openDocPageAndUploadFile(ApplicationPage applicationPage,
                                                  DocumentPage documentPage,
                                                  String file1) {
        applicationPage
                .openAppGenericDocument();

        documentPage
                .clickOnUploadDocFilesBtn()
                .uploadDocFileViaPopUp(file1)
                .closeDocPartitionAndMoveToAppPage();
    }
}
