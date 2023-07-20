package nisfappui.pages;

import com.microsoft.playwright.Page;

public class NGeniusOnlinePartitionPage {

    private static final String INTEGRATION_METHOD = "(//button[contains(@aria-label, '*Integration Method')])[1]";
    private static final String INTEGRATION_METHOD_OPTION = "//span[@title='%s']";

    private static final String WEB_INTEGRATION_CHECKBOX = "//div[contains(@class,'maximized active')]//span[text()='*Web Integration']";
    private static final String PAY_BY_LINK_CHECKBOX = "//div[contains(@class,'maximized active')]//span[text()='*Pay By Link']";
    private static final String NUMBER_OF_TRANS_ANNUAL = "//label[text()='*Number of Transactions expected(Annual)']//ancestor::span//child::input";
    private static final String ANNUAL_ECOM_VALUE = "//label[text()='*Annual expected Ecommerce value']//ancestor::span//child::input";


    private static final String SELECTED_PAYMENT_TYPE = "//div[text()='*Payment Types to be accepted']//..//ul/li//span[text()='%s']";
    private static final String SELECTED_PAYMENT_TYPE_BTN = "//div[text()='*Payment Types to be accepted']//..//button[@title='Move selection to Chosen']";


    private final Page page;

    public NGeniusOnlinePartitionPage(Page page) {
        this.page = page;
    }


    public NGeniusOnlinePartitionPage clickOnWebIntegrationCheckbox(boolean activateCheckbox) {
        if (activateCheckbox) {
            page.locator(WEB_INTEGRATION_CHECKBOX).click();
        }
        return this;
    }


    public NGeniusOnlinePartitionPage clickOnPayByLinkCheckbox(boolean activateCheckbox) {
        if (activateCheckbox) {
            page.locator(PAY_BY_LINK_CHECKBOX).click();
        }
        return this;
    }

    public NGeniusOnlinePartitionPage fillNumberTransactionsAnnual(int countTrans) {
        page.locator(NUMBER_OF_TRANS_ANNUAL).fill(String.valueOf(countTrans));
        return this;
    }

    public NGeniusOnlinePartitionPage fillNumberEcomAnnualValue(int countTrans) {
        page.locator(ANNUAL_ECOM_VALUE).fill(String.valueOf(countTrans));
        return this;
    }


    public NGeniusOnlinePartitionPage fillIntegrationMethod(String option) {
        page.locator(INTEGRATION_METHOD).press("Enter");
        page.locator(String.format(INTEGRATION_METHOD_OPTION, option)).click();
        return this;
    }


    public NGeniusOnlinePartitionPage selectCardPaymentType(String selectedProduct) {
        page.locator(String.format(SELECTED_PAYMENT_TYPE, selectedProduct)).click();
        page.locator(SELECTED_PAYMENT_TYPE_BTN).click();
        return this;
    }

}
