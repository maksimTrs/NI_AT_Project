package nisobapp.utils;

import nisobapp.pojo.*;

import java.util.ArrayList;
import java.util.List;

import static nisobapp.utils.ApiAppDataFaker.*;
import static nisobapp.utils.TestHelper.*;

public class SobAppDataBuilder {

    public static Application getApplicationBuilderData () {
        return Application.builder()
                .merchantType("SME")
                .keyMerchant("")
                .creator("SOB")
                .legalType("Partnership")
                .countryOfEstablishment("United Arab Emirates")
                .dateOfEstablishment(getRandomDateOfEstablishment())
                .businessNature("Billing Services")
                .businessLine("Accounting, Auditing, and Bookkeeping Services")
                .businessDescription("AT SOB TEST")
                .yearsInBusiness(String.valueOf(getRandomIntValue(1, 15)))
                .expectedVolumePerYear(String.valueOf(getRandomIntValue(1000, 50000)))
                .expectedCardVolume(String.valueOf(getRandomIntValue(100, 1000)))
                .paymentMode("EQ")
                .rentalMode("DD")
                .screeningResult(SCREENING_RESULT)
                .shareHolders(null)
                .tenantId("ni")
                .contacts(getContactsItems())
                .sanctionCountryInformation(SanctionCountryInformation.builder()
                        .relatedpersonentityinSC(true)
                        .involveddealingsinSC(true)
                        .investmentsinSC(true).build())
                .build();

    }

















    private static List<ContactsItem> getContactsItems() {
        List<ContactsItem> contactsItemList = new ArrayList<>();

        ContactsItem indian = ContactsItem.builder()
                .authorizedSignatory(true)
                .UAEPASSVerifiedContact(true)
                .firstName(getRandomFirstName())
                .lastName(getRandomLastName())
                .mobile(getRandomPhone())
                .birthDate(getRandomDateOfBirth())
                .emiratesId("784-1990-6338466-5")
                .nationality("Indian")
                .passportNumber(getRandomPassport())
                .passportExpirationDate(getRandomLicenceExpirationDate())
                .visaExpirationDate(getRandomLicenceExpirationDate())
                .emiratesExpirationDate(getRandomLicenceExpirationDate())
                .email(getRandomEmail())
                .PEPInformation(PEPInformation.builder()
                        .isPEP(true)
                        .build())
                .percentageofOwnership(String.valueOf(getRandomIntValue(1, 50)))
                .build();

        ContactsItem UAE = ContactsItem.builder()
                .authorizedSignatory(true)
                .UAEPASSVerifiedContact(true)
                .firstName(getRandomFirstName())
                .lastName(getRandomLastName())
                .mobile(getRandomPhone())
                .birthDate(getRandomDateOfBirth())
                .designation("tet")
                .nationality("UAE")
                .passportNumber(getRandomPassport())
                .passportExpirationDate(getRandomLicenceExpirationDate())
                .email(getRandomEmail())
                .PEPInformation(PEPInformation.builder()
                        .isPEP(true)
                        .build())
                .percentageofOwnership(String.valueOf(getRandomIntValue(10, 50)))
                .build();

        contactsItemList.add(indian);
        contactsItemList.add(UAE);

        return contactsItemList;
    }




    private static List<MerchantsItem> getMerchantsItems() {
        List<MerchantsItem> merchantsItemList = new ArrayList<>();


        MerchantsItem NG1_PayByQR = MerchantsItem.builder()
                .merchantType("NG1-PayByQR")
                .merchantName(getRandomTradeName())
                .tradeName(getRandomTradeName())
                .bankCode("3707")
                .IBAN(IBAN)
                .accountNumber(IBAN_ACC_NUM)
                .documents(getDocumentsItems())
                .taxRegistrationNumber(String.valueOf(getRandomTLN()))
                .tradeLicenseNumber(String.valueOf(getRandomTLN()))
                .tradeLicenseExpirationDate(getRandomLicenceExpirationDate())
                .merchantEmail(getRandomEmail())
                .MSFRates(MSFRates.builder()
                        .standard("1.50")
                        .premium("2.25")
                        .manual("3.00")
                        .debit("2.25")
                        .international("3.00")
                        .build())
                .fees(Fees.builder()
                        .perTransactionAuthorizationFee(1.0)
                        .MISMonthlyFee(129.0)
                        .refundFee(5.0)
                        .chargebackFee(20.0)
                        .build())
                .configuration(Configuration.builder()
                        .settlementFrequency("Monthly")
                        .settlementFirstDay("")
                        .settlementSecondDay("")
                        .refundCategory("N")
                        .netSpread(0.58)
                        .build())
                .services(null)
                .terminals(getTerminalsItems("PBQ"))
                .address(Address.builder()
                        .addressLine(getRandomAddress())
                        .city("Umm Al Quwain")
                        .pobox(getRandomPOBox())
                        .phone(getRandomPhone())
                        .build())
                .build();




        return merchantsItemList;
    }





    private static List<DocumentsItem> getDocumentsItems() {
        List<DocumentsItem> documentsItemList = new ArrayList<>();


        DocumentsItem DOC_1 = DocumentsItem.builder()
                .documentId(DOC_ID_1)
                .documentURL(DOC_URL_1)
                .documentType("bankStatement")
                .build();

        DocumentsItem DOC_2 = DocumentsItem.builder()
                .documentId(DOC_ID_2)
                .documentURL(DOC_URL_2)
                .documentType("tradeLicense")
                .build();

        DocumentsItem DOC_3 = DocumentsItem.builder()
                .documentId(DOC_ID_3)
                .documentURL(DOC_URL_3)
                .documentType("passport")
                .build();

        DocumentsItem DOC_4 = DocumentsItem.builder()
                .documentId(DOC_ID_4)
                .documentURL(DOC_URL_4)
                .documentType("visa")
                .UAEPASSVerifiedFile(true)
                .build();


        DocumentsItem DOC_5 = DocumentsItem.builder()
                .documentId(DOC_ID_5)
                .documentURL(DOC_URL_5)
                .documentType("emiratesIdFile")
                .UAEPASSVerifiedFile(true)
                .build();

        DocumentsItem DOC_6 = DocumentsItem.builder()
                .documentId(DOC_ID_6)
                .documentURL(DOC_URL_6)
                .documentType("powerOfAttorney")
                .UAEPASSVerifiedFile(true)
                .build();


        DocumentsItem DOC_7 = DocumentsItem.builder()
                .documentId(DOC_ID_7)
                .documentURL(DOC_URL_7)
                .documentType("passport")
                .build();

        DocumentsItem DOC_8 = DocumentsItem.builder()
                .documentId(DOC_ID_8)
                .documentURL(DOC_URL_8)
                .documentType("memorandum")
                .build();

        DocumentsItem DOC_9 = DocumentsItem.builder()
                .documentId(DOC_ID_9)
                .documentURL(DOC_URL_9)
                .documentType("selfOnboardingAgreement")
                .build();

        DocumentsItem DOC_10 = DocumentsItem.builder()
                .documentId(DOC_ID_9)
                .documentURL(DOC_URL_9)
                .documentType("selfOnboardingAgreement")
                .build();

        documentsItemList.add(DOC_1);
        documentsItemList.add(DOC_2);
        documentsItemList.add(DOC_3);
        documentsItemList.add(DOC_4);
        documentsItemList.add(DOC_5);
        documentsItemList.add(DOC_6);
        documentsItemList.add(DOC_7);
        documentsItemList.add(DOC_8);
        documentsItemList.add(DOC_9);
        documentsItemList.add(DOC_10);

        return documentsItemList;
    }



    private static List<TerminalsItem> getTerminalsItems(String merchantType) {
        List<TerminalsItem> terminalsItemList = new ArrayList<>();

        if (merchantType.equalsIgnoreCase("PBQ")) {
            TerminalsItem terminal_1 = TerminalsItem.builder()
                    .terminalModel("")
                    .terminalFee(0.0)
                    .terminalCategory("")
                    .terminalBrand("")
                    .build();

            terminalsItemList.add(terminal_1);
        }


        return terminalsItemList;
    }
}
