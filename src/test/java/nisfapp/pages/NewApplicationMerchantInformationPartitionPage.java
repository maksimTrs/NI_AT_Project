package nisfapp.pages;

import com.microsoft.playwright.Page;
import nisfapp.utils.MethodActionForPO;

public class NewApplicationMerchantInformationPartitionPage extends MethodActionForPO {


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
        //waitForPageLoadState(page);
        // waitForLocatorLoadTimeout(page, PHONE, 7000);
        page.locator(PHONE).fill(phoneNumber);
        return this;
    }

    public NewApplicationMerchantInformationPartitionPage fillLegalType(String legalType) {
        page.locator(LEGAL_TYPE).click();
        page.locator(String.format(LEGAL_TYPE_OPTION, legalType)).click();
        return this;
    }

    public NewApplicationMerchantInformationPartitionPage fillPOBox(String PoBox) {
        page.locator(PO_BOX).fill(PoBox);
        return this;
    }

    public NewApplicationMerchantInformationPartitionPage fillCity(String city) {
        // page.waitForSelector(CITY_DUBAI, new Page.WaitForSelectorOptions().setState(VISIBLE));
        page.locator(CITY).press("Enter");
        page.locator(String.format(CITY_OPTION, city)).click();
        return this;
    }

    public NewApplicationMerchantInformationPartitionPage fillAddress(String address) {
        page.locator(ADDRESS).fill(address);
        return this;
    }


    public NewApplicationMerchantInformationPartitionPage fillWebsiteEcomOrPos(String locator, String webUrl) {
        page.locator(String.format(WEBSITE, locator)).fill(webUrl);
        return this;
    }


    public NewApplicationMerchantInformationPartitionPage fillTradeLicenceNumber(long tln) {
        page.locator(TRADE_LICENCE_NUMBER).fill(String.valueOf(tln));
        return this;
    }


    public NewApplicationMerchantInformationPartitionPage fillCountry(String country) {
        page.locator(COUNTRY).click();
        page.locator(String.format(COUNTRY_OPTION, country)).click();
        return this;
    }

    public NewApplicationMerchantInformationPartitionPage fillDateEstablishment(String date) {
        page.locator(DATE_ESTABLISHMENT).fill(date);
        return this;
    }


    public NewApplicationMerchantInformationPartitionPage fillDateLicenceExpiration(String date) {
        page.locator(DATE_LICENCE_EXP).fill(date);
        return this;
    }
}
