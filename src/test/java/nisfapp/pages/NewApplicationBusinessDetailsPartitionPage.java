package nisfapp.pages;

import com.microsoft.playwright.Page;
import nisfapp.utils.MethodActionsForPO;

public class NewApplicationBusinessDetailsPartitionPage extends MethodActionsForPO {

    private static final String DESC_BUSINESS_OPERATION_TEXT = "PLAYWRIGHT AT TEST";

    private static final String BUSINESS_LINE = "//label[text()='Business Line']//..//button[contains(@aria-label, 'Business Line')]";
    private static final String BUSINESS_LINE_OPTION = "//span[text()='%s']";
    private static final String DESC_BUSINESS_OPERATION = "//label[text()='* Description of business operations']//..//input[contains(@name, 'DescriptionOfBusinessOperations')]";
    private static final String YEARS_IN_BUSINESS = "//label[text()='* Years in business']//..//input[contains(@name, 'YearsInBusiness')]";
    private static final String VOLUME_PER_YEAR = "//label[text()='* Expected volume per year']//..//input[contains(@name,'TotalExpectedCreditCardSales')]";
    private static final String CARD_VOLUME_PER_YEAR = "//label[text()='* Of which expected card volume per year']//..//input[contains(@name,'ExpectedCashSales')]";
    private static final String SAVE_NEW_APP_BTN = "//button[@name='SaveEdit']";


    private final Page page;

    public NewApplicationBusinessDetailsPartitionPage(Page page) {
        this.page = page;
    }


    public NewApplicationBusinessDetailsPartitionPage fillCardPerYear(int num) {
        fillElementField(page.locator(CARD_VOLUME_PER_YEAR), String.valueOf(num));
        return this;
    }

    public NewApplicationBusinessDetailsPartitionPage fillVolumePerYear(int num) {
        fillElementField(page.locator(VOLUME_PER_YEAR), String.valueOf(num));
        return this;
    }

    public NewApplicationBusinessDetailsPartitionPage fillDescOfBusinessOperation(String msg) {
        fillElementField(page.locator(DESC_BUSINESS_OPERATION), msg);
        return this;
    }

    public NewApplicationBusinessDetailsPartitionPage fillYearsInBusiness(int year) {
        fillElementField(page.locator(YEARS_IN_BUSINESS), String.valueOf(year));
        return this;
    }


    public NewApplicationBusinessDetailsPartitionPage fillBusinessLine(String mode) {
        pressKeyBtnWithDelay(page.locator(BUSINESS_LINE), "Enter", 1000);

        String blOpt = String.format(BUSINESS_LINE_OPTION, mode);
        doClickOnElement(page.locator(blOpt));
        return this;
    }

    public void clickOnNewAppSaveBtn() {
        page.locator(SAVE_NEW_APP_BTN).click();
        //page.waitForTimeout(9000);
    }
}
