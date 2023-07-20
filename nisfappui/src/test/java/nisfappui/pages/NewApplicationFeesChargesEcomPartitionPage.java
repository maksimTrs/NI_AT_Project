package nisfappui.pages;

import com.microsoft.playwright.Page;

public class NewApplicationFeesChargesEcomPartitionPage {

    private static final String SETTLEMENT_FREQ_ECOM = "(//button[contains(@aria-label, 'Settlement frequency (Ecom)')])[1]";
    private static final String REFUND_CATEGORY_ECOM = "(//button[contains(@aria-label, '*Refund category (Ecom)')])[1]";
    private static final String SETTLEMENT_FREQ_ECOM_OPTION = "//span[@title='%s']";
    private static final String REFUND_CATEGORY_OPTION = "//span[@title='%s']";


    private final Page page;

    public NewApplicationFeesChargesEcomPartitionPage(Page page) {
        this.page = page;
    }


    public NewApplicationFeesChargesEcomPartitionPage fillRefundCategory(String option) {
        page.locator(REFUND_CATEGORY_ECOM).press("Enter");
        page.locator(String.format(REFUND_CATEGORY_OPTION, option)).click();
        return this;
    }

    public NewApplicationFeesChargesEcomPartitionPage fillSettlementFreqEcomOption(String option) {
        page.locator(SETTLEMENT_FREQ_ECOM).press("Enter");
        page.locator(String.format(SETTLEMENT_FREQ_ECOM_OPTION, option)).click();
        return this;
    }
}
