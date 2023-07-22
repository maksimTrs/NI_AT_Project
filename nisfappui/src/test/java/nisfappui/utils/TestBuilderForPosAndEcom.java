package nisfappui.utils;

import nisfappui.pages.*;
import nisfappui.services.*;

import static nisfappui.services.ApplicationProductTypes.*;
import static nisfappui.services.Ng1AuthSystemTypes.BASE24;
import static nisfappui.services.Ng1AuthSystemTypes.MPGS;
import static nisfappui.services.PosTypeAndGatewayTypes.*;
import static nisfappui.utils.AppDataFaker.*;

public class TestBuilderForPosAndEcom {

    public void fillMerchantInitialCreationECOM(MerchantInitialCreationPage merchantInitialCreationPage,
                                                String bnt) {
        merchantInitialCreationPage
                .fillTradeName(getRandomTradeName())
                .fillMerchantEmail(getRandomEmail())
                .chooseBusinessNatureType(bnt)
                .unselectSelectedProduct(POS.getDisplayName())
                .selectEcomType(ECON_TYPE.getDisplayName())
                .moveToTheSecondApplicationScreen();
    }


    public void fillMerchantInitialCreationType3(MerchantInitialCreationPage merchantInitialCreationPage,
                                                 String bnt) {
        merchantInitialCreationPage
                .fillTradeName(getRandomTradeName())
                .fillMerchantEmail(getRandomEmail())
                .chooseBusinessNatureType(bnt)
                .selectPosType(POS_TYPE_NG.getDisplayName())
                .selectEcomType(ECON_TYPE.getDisplayName())
                .moveToTheSecondApplicationScreen();
    }


    public void fillMerchantInitialCreationPosNG(MerchantInitialCreationPage merchantInitialCreationPage,
                                                 String bnt) {
        merchantInitialCreationPage
                .fillTradeName(getRandomTradeName())
                .fillMerchantEmail(getRandomEmail())
                .chooseBusinessNatureType(bnt)
                .unselectSelectedProduct(ECOM.getDisplayName())
                .selectPosType(POS_TYPE_NG.getDisplayName())
                .setNumberOfPos(getRandomIntValue(1, 13))
                .moveToTheSecondApplicationScreen();
    }

    public void fillMerchantInitialCreationPosSOFTPOS(MerchantInitialCreationPage merchantInitialCreationPage,
                                                      String bnt) {
        merchantInitialCreationPage
                .fillTradeName(getRandomTradeName())
                .fillMerchantEmail(getRandomEmail())
                .chooseBusinessNatureType(bnt)
                .unselectSelectedProduct(ECOM.getDisplayName())
                .selectPosType(POS_TYPE_SOFT.getDisplayName())
                .moveToTheSecondApplicationScreen();
    }


    public void fillMerchantInitialCreationNG1WithBase24(MerchantInitialCreationPage merchantInitialCreationPage,
                                                         String bnt) {
        merchantInitialCreationPage
                .fillTradeName(getRandomTradeName())
                .fillMerchantEmail(getRandomEmail())
                .chooseBusinessNatureType(bnt)
                .unselectSelectedProduct(POS.getDisplayName())
                .unselectSelectedProduct(ECOM.getDisplayName())
                .selectAvailableProduct(NG1.getDisplayName())
                .selectNg1ECOMAuthType(BASE24.getDisplayName())
                .selectNg1SoftPOSAuthType(BASE24.getDisplayName())
                .moveToTheSecondApplicationScreen();
    }

    public void fillMerchantInitialCreationNG1WithMPGS(MerchantInitialCreationPage merchantInitialCreationPage,
                                                       String bnt) {
        merchantInitialCreationPage
                .fillTradeName(getRandomTradeName())
                .fillMerchantEmail(getRandomEmail())
                .chooseBusinessNatureType(bnt)
                .unselectSelectedProduct(POS.getDisplayName())
                .unselectSelectedProduct(ECOM.getDisplayName())
                .selectAvailableProduct(NG1.getDisplayName())
                .selectNg1ECOMAuthType(MPGS.getDisplayName())
                .selectNg1SoftPOSAuthType(MPGS.getDisplayName())
                .moveToTheSecondApplicationScreen();
    }


    public void fillMerchantInformationPartitionECOM(NewApplicationMerchantInformationPartitionPage newApplicationMerchantInformationPartitionPage,
                                                     LegalTypeTypes legalTypeTypes,
                                                     String webSite,
                                                     CityTypes cityTypes,
                                                     CountryTypes countryTypes) {
        newApplicationMerchantInformationPartitionPage
                .fillPhone(getRandomPhone())
                .fillLegalType(legalTypeTypes.getDisplayName())
                .fillWebsiteEcomOrPos(webSite, getRandomWebURL())
                .fillPOBox(getRandomPOBox())
                .fillAddress(getRandomAddress())
                .fillCity(cityTypes.getDisplayName())
                .fillCountry(countryTypes.getDisplayName())
                .fillTradeLicenceNumber(getRandomTLN())
                .fillDateEstablishment(getRandomDateOfEstablishment())
                .fillDateLicenceExpiration(getRandomLicenceExpirationDate());
    }


    public void fillApplicationFeesChargesEcomPartition(NewApplicationFeesChargesEcomPartitionPage newApplicationFeesChargesEcomPartitionPage,
                                                        SettlementFrequencyEcomTypes settlementFrequencyEcomTypes,
                                                        RefundCategoryTypes refundCategoryTypes) {
        newApplicationFeesChargesEcomPartitionPage
                .fillSettlementFreqEcomOption(settlementFrequencyEcomTypes.getDisplayName())
                .fillRefundCategory(refundCategoryTypes.getDisplayName());
    }

    public void fillApplicationFeesChargesPosPartition(NewApplicationFeesChargesPartitionPage newApplicationFeesChargesPartitionPage) {
        newApplicationFeesChargesPartitionPage
                .fillMisMonthReportFee(getRandomDoubleValue(10, 99));
    }


    public void fillNGeniusOnlinePartitionPage(NGeniusOnlinePartitionPage nGeniusOnlinePartitionPage,
                                               boolean clickOnWebIntegrationCheckbox,
                                               boolean clickOnPayByLinkCheckbox,
                                               NgOnlineIntegrationMethodTypes ngOnlineIntegrationMethodTypes,
                                               NgOnlinePaymentTypes ngOnlinePaymentTypes,
                                               NgOnlinePaymentTypes ngOnlinePaymentTypes2) {
        nGeniusOnlinePartitionPage
                .clickOnWebIntegrationCheckbox(clickOnWebIntegrationCheckbox)
                .clickOnPayByLinkCheckbox(clickOnPayByLinkCheckbox)
                .fillNumberTransactionsAnnual(getRandomIntValue(10000, 100000))
                .fillNumberEcomAnnualValue(getRandomIntValue(100, 10000))
                .fillIntegrationMethod(ngOnlineIntegrationMethodTypes.getDisplayName())
                .selectCardPaymentType(ngOnlinePaymentTypes.getDisplayName())
                .selectCardPaymentType(ngOnlinePaymentTypes2.getDisplayName());
    }
}
