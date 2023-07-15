package nisobapp.utils;

import nisobapp.pojo.*;

import java.util.ArrayList;
import java.util.List;

import static nisobapp.utils.ApiAppDataFaker.*;
import static nisobapp.utils.TestHelper.*;

public class SobAppDataBuilder {

    public static Application getApplicationBuilderData() {
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
                //.shareHolders(null)
                .tenantId("ni")
                .contacts(getContactsItems())
                .sanctionCountryInformation(SanctionCountryInformation
                        .builder()
                        .relatedpersonentityinSC(false)
                        .involveddealingsinSC(false)
                        .investmentsinSC(false)
                        .build())
                .merchants(getMerchantsItems())
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
                .passportNumber("V4554")
                .passportExpirationDate(getRandomLicenceExpirationDate())
                .visaExpirationDate(getRandomLicenceExpirationDate())
                .emiratesExpirationDate(getRandomLicenceExpirationDate())
                .email(getRandomEmail())
                .PEPInformation(PEPInformation
                        .builder()
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
                .passportNumber("V34355")
                .passportExpirationDate(getRandomLicenceExpirationDate())
                .email(getRandomEmail())
                .PEPInformation(PEPInformation
                        .builder()
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
                .tradeLicenseNumber("433553")
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
                .configuration(Configuration
                        .builder()
                        .settlementFrequency("Monthly")
                        .settlementFirstDay("")
                        .settlementSecondDay("")
                        .refundCategory("N")
                        .netSpread(0.58)
                        .build())
                .services(Services
                        .builder()
                        .build())
                .terminals(getTerminalsItems("PBQ"))
                .address(Address
                        .builder()
                        .addressLine(getRandomAddress())
                        .city("Umm Al Quwain")
                        .pobox(getRandomPOBox())
                        .phone(getRandomPhone())
                        .build())
                .build();


        MerchantsItem NG1_TapToPay = MerchantsItem.builder()
                .merchantType("NG1-TapToPay")
                .merchantName(getRandomTradeName())
                .tradeName(getRandomTradeName())
                .bankCode("3707")
                .IBAN(IBAN)
                .accountNumber(IBAN_ACC_NUM)
                .documents(getDocumentsItems2())
                .taxRegistrationNumber(String.valueOf(getRandomTLN()))
                .tradeLicenseNumber("557754")
                .tradeLicenseExpirationDate(getRandomLicenceExpirationDate())
                .merchantEmail(getRandomEmail())
                .MSFRates(MSFRates
                        .builder()
                        .standard("1.50")
                        .premium("2.25")
                        .manual("3.00")
                        .debit("2.25")
                        .international("3.00")
                        .build())
                .fees(Fees.builder()
                        .perTransactionAuthorizationFee(1.0)
                        .MISMonthlyFee(0.0)
                        .refundFee(5.0)
                        .chargebackFee(20.0)
                        .annualFee(2.0)
                        .build())
                .configuration(Configuration
                        .builder()
                        .settlementFrequency("Monthly")
                        .settlementFirstDay("")
                        .settlementSecondDay("")
                        .refundCategory("N")
                        .netSpread(0.58)
                        .SoftPOSAuthSystem("MPGS")
                        .build())
                .services(Services
                        .builder()
                        .build())
                .terminals(getTerminalsItems("TTP"))
                .address(Address
                        .builder()
                        .addressLine(getRandomAddress())
                        .city("Umm Al Quwain")
                        .pobox(getRandomPOBox())
                        .phone(getRandomPhone())
                        .build())
                .build();


        MerchantsItem NG1_PayByLink = MerchantsItem.builder()
                .merchantType("NG1-PayByLink")
                .merchantName(getRandomTradeName())
                .tradeName(getRandomTradeName())
                .bankCode("3707")
                .IBAN(IBAN)
                .accountNumber(IBAN_ACC_NUM)
                .documents(getDocumentsItems2())
                .taxRegistrationNumber(String.valueOf(getRandomTLN()))
                .tradeLicenseNumber("141414")
                .tradeLicenseExpirationDate(getRandomLicenceExpirationDate())
                .merchantEmail(getRandomEmail())
                .website(getRandomWebURL())
                .MSFRates(MSFRates
                        .builder()
                        .standard("2.99")
                        .CUP("2.99")
                        .JCB("1.99")
                        .premium("1.99")
                        .manual("1.99")
                        .debit("2.25")
                        .international("2.99")
                        .diners("1.99")
                        .build())
                .fees(Fees.builder()
                        .perTransactionAuthorizationFee(0.4)
                        .MISMonthlyFee(99.0)
                        .refundFee(5.0)
                        .chargebackFee(0.0)
                        .annualFee(100)
                        .setupFee(500)
                        .build())
                .configuration(Configuration
                        .builder()
                        .settlementFrequency("Daily")
                        .settlementFirstDay("")
                        .settlementSecondDay("")
                        .refundCategory("C")
                        .netSpread(1)
                        .transactionsExpectedAnnually("")
                        .ecommerceVolumeExpectedAnnually("")
                        .paymentTypeAccepted("MasterCard")
                        .amountCap("1000")
                        .flexiCutofDays("1")
                        .ECOMAuthSystem("MPGS")
                        .build())
                .services(Services
                        .builder()
                        .enable3DS(true)
                        .build())
                .terminals(getTerminalsItems("PBL"))
                .address(Address
                        .builder()
                        .addressLine(getRandomAddress())
                        .city("Umm Al Quwain")
                        .pobox(getRandomPOBox())
                        .phone(getRandomPhone())
                        .build())
                .build();

        merchantsItemList.add(NG1_PayByQR);
        merchantsItemList.add(NG1_TapToPay);
        merchantsItemList.add(NG1_PayByLink);

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


    private static List<DocumentsItem> getDocumentsItems2() {
        List<DocumentsItem> documentsItemList = new ArrayList<>();


        DocumentsItem DOC_1 = DocumentsItem.builder()
                .documentId(DOC_ID_1_1)
                .documentURL(DOC_URL_1_1)
                .documentType("bankStatement")
                .build();

        DocumentsItem DOC_2 = DocumentsItem.builder()
                .documentId(DOC_ID_2_2)
                .documentURL(DOC_URL_2_2)
                .documentType("tradeLicense")
                .build();

        DocumentsItem DOC_3 = DocumentsItem.builder()
                .documentId(DOC_ID_3_3)
                .documentURL(DOC_URL_3_3)
                .documentType("passport")
                .build();

        DocumentsItem DOC_4 = DocumentsItem.builder()
                .documentId(DOC_ID_4_4)
                .documentURL(DOC_URL_4_4)
                .documentType("visa")
                .build();


        DocumentsItem DOC_5 = DocumentsItem.builder()
                .documentId(DOC_ID_5_5)
                .documentURL(DOC_URL_5_5)
                .documentType("emiratesIdFile")
                .build();

        DocumentsItem DOC_6 = DocumentsItem.builder()
                .documentId(DOC_ID_6_6)
                .documentURL(DOC_URL_6_6)
                .documentType("emiratesIdFile")
                .build();


        DocumentsItem DOC_7 = DocumentsItem.builder()
                .documentId(DOC_ID_7_7)
                .documentURL(DOC_URL_7_7)
                .documentType("passport")
                .build();

        DocumentsItem DOC_8 = DocumentsItem.builder()
                .documentId(DOC_ID_8_8)
                .documentURL(DOC_URL_8_8)
                .documentType("visa")
                .build();

        DocumentsItem DOC_9 = DocumentsItem.builder()
                .documentId(DOC_ID_9_9)
                .documentURL(DOC_URL_9_9)
                .documentType("emiratesIdFile")
                .build();

        DocumentsItem DOC_10 = DocumentsItem.builder()
                .documentId(DOC_ID_10_10)
                .documentURL(DOC_URL_10_10)
                .documentType("emiratesIdFile")
                .build();

        DocumentsItem DOC_11 = DocumentsItem.builder()
                .documentId(DOC_ID_11_11)
                .documentURL(DOC_URL_11_11)
                .documentType("powerOfAttorney")
                .build();


        DocumentsItem DOC_12 = DocumentsItem.builder()
                .documentId(DOC_ID_12_12)
                .documentURL(DOC_URL_12_12)
                .documentType("taxRegistrationCertificate")
                .build();

        DocumentsItem DOC_13 = DocumentsItem.builder()
                .documentId(DOC_ID_13_13)
                .documentURL(DOC_URL_13_13)
                .documentType("memorandum")
                .build();


        DocumentsItem DOC_14 = DocumentsItem.builder()
                .documentId(DOC_ID_14_14)
                .documentURL(DOC_URL_14_14)
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
        documentsItemList.add(DOC_11);
        documentsItemList.add(DOC_12);
        documentsItemList.add(DOC_13);
        documentsItemList.add(DOC_14);

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
        } else if (merchantType.equalsIgnoreCase("TTP")) {
            TerminalsItem terminal_1 = TerminalsItem.builder()
                    .terminalModel("CPOCPIN")
                    .terminalFee(0.0)
                    .terminalCategory("")
                    .terminalBrand("MOSAMBEE")
                    .build();

            TerminalsItem terminal_2 = TerminalsItem.builder()
                    .terminalModel("CPOCPIN")
                    .terminalFee(0.0)
                    .terminalCategory("")
                    .terminalBrand("MOSAMBEE")
                    .build();

            TerminalsItem terminal_3 = TerminalsItem.builder()
                    .terminalModel("CPOCPIN")
                    .terminalFee(0.0)
                    .terminalCategory("")
                    .terminalBrand("MOSAMBEE")
                    .build();

            terminalsItemList.add(terminal_1);
            terminalsItemList.add(terminal_2);
            terminalsItemList.add(terminal_3);
        } else if (merchantType.equalsIgnoreCase("PBL")) {
            TerminalsItem terminal_1 = TerminalsItem.builder()
                    .terminalModel("")
                    .terminalFee(600)
                    .terminalCategory("")
                    .terminalBrand("")
                    .build();

            terminalsItemList.add(terminal_1);
        }

        return terminalsItemList;
    }
}
