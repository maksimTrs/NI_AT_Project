package nisfappui.pages;

import com.microsoft.playwright.Page;
import nisfappui.utils.MethodActionsForPO;

public class NewApplicationMerchantInformationPartitionPage extends MethodActionsForPO {


    private static final String PHONE = "//label[text()='* Phone']//ancestor::span//child::input";
    private static final String LEGAL_TYPE = "//button[contains(@aria-label,'Legal type')]";
    private static final String LEGAL_TYPE_OPTION = "//span[@title='%s']";
    private static final String PO_BOX = "//label[text()='* P.O. Box']//ancestor::span//child::input";
    private static final String CITY = "//button[contains(@aria-label, 'City')]";
    private static final String CITY_OPTION = "//span[@title='%s']";
    private static final String ADDRESS = "//label[text()='* Address']//ancestor::span//child::input";
    private static final String WEBSITE = "//label[text()='%s']//ancestor::span//child::input";
    private static final String TRADE_LICENCE_NUMBER = "//label[text()='* Trade license number']//ancestor::span//child::input";
    private static final String COUNTRY = "//button[contains(@aria-label, 'Entity country of establishment')]";
    private static final String COUNTRY_OPTION = "//span[@title='%s']";
    private static final String DATE_ESTABLISHMENT = "//label[text()='* Entity date of establishment']//ancestor::span//child::input";
    private static final String DATE_LICENCE_EXP = "//label[text()='* Trade license expiration date']//ancestor::span//child::input";


    private final Page page;

    public NewApplicationMerchantInformationPartitionPage(Page page) {
        this.page = page;
    }

    public NewApplicationMerchantInformationPartitionPage fillPhone(String phoneNumber) {
        page.waitForTimeout(3000);
        fillElementField(page.locator(PHONE), phoneNumber);
        return this;
    }

    public NewApplicationMerchantInformationPartitionPage fillLegalType(String legalType) {
        doClickOnElement(page.locator(LEGAL_TYPE));

        String legaTypeOpt = String.format(LEGAL_TYPE_OPTION, legalType);
        doClickOnElement(page.locator(legaTypeOpt));
        return this;
    }

    public NewApplicationMerchantInformationPartitionPage fillPOBox(String PoBox) {
        fillElementField(page.locator(PO_BOX), PoBox);
        return this;
    }

    public NewApplicationMerchantInformationPartitionPage fillCity(String city) {
        pressKeyBtn(page.locator(CITY), "Enter");

        String cityOpt = String.format(CITY_OPTION, city);
        doClickOnElement(page.locator(cityOpt));
        return this;
    }

    public NewApplicationMerchantInformationPartitionPage fillAddress(String address) {
        fillElementField(page.locator(ADDRESS), address);
        return this;
    }


    public NewApplicationMerchantInformationPartitionPage fillWebsiteEcomOrPos(String locator, String webUrl) {
        String res = String.format(WEBSITE, locator);
        fillElementField(page.locator(res), webUrl);
        return this;
    }


    public NewApplicationMerchantInformationPartitionPage fillTradeLicenceNumber(long tln) {
        fillElementField(page.locator(TRADE_LICENCE_NUMBER), String.valueOf(tln));
        return this;
    }


    public NewApplicationMerchantInformationPartitionPage fillCountry(String country) {
        doClickOnElement(page.locator(COUNTRY));

        String countryOpt = String.format(COUNTRY_OPTION, country);
        doClickOnElement(page.locator(countryOpt));
        return this;
    }

    public NewApplicationMerchantInformationPartitionPage fillDateEstablishment(String date) {
        fillElementField(page.locator(DATE_ESTABLISHMENT), date);
        return this;
    }


    public NewApplicationMerchantInformationPartitionPage fillDateLicenceExpiration(String date) {
        fillElementField(page.locator(DATE_LICENCE_EXP), date);
        return this;
    }
}
