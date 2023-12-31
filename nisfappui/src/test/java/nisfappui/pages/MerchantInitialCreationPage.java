package nisfappui.pages;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import nisfappui.utils.MethodActionsForPO;

import java.util.List;

import static com.microsoft.playwright.options.WaitForSelectorState.VISIBLE;
import static nisfappui.tests.BaseTest.logger;
import static nisfappui.utils.AppDataFaker.getRandomIntValue;


public class MerchantInitialCreationPage extends MethodActionsForPO {

    private static final String TRADE_NAME = "//input[@placeholder='Merchant inc.']";
    private static final String MERCHANT_EMAIL = "//input[@inputmode='email']";
    private static final String BUSINESS_NATURE = "(//*[text()='Business Nature']/../..//select)[1]";
    private static final String BUSINESS_NATURE_LIST = "(//*[text()='Business Nature']/../..//select)[1]/option";
    private static final String SELECTED_PRODUCT = "(//span[text()='Selected Product']//parent::div//span[text()='%s'])[1]";
    private static final String AVAILABLE_PRODUCT = "(//span[text()='Available Product']//parent::div//span[text()='%s'])[1]";
    private static final String UNSELECT_SELECTED_PRODUCT_BTN = "(//button[@title='Move selection to Available Product'])[1]";
    private static final String SELECT_SELECTED_PRODUCT_BTN = "(//button[@title='Move selection to Selected Product'])[1]";
    private static final String POS_TYPE = "(//span[text()='POS Type']//parent::label//following-sibling::div//select)[1]";
    private static final String ECOM_TYPE = "(//span[text()='Gateway Type']//parent::label//following-sibling::div//select)[1]";
    private static final String NUMBER_OF_POS = "(//div/input[@id='input-02'])[1]";
    private static final String NEXT_BTN = "//button[text()='Next']";
    private static final String PAY_BY_QR = "//span[text()='Selected Payment Method']/..//ul//span[text()='Pay by QR']";
    private static final String NG1_ECOM_AUTH = "//select[@name='Select11']";
    private static final String NG1_SOFTPOS_AUTH = "//select[@name='Select12']";
    private static final String NEW_MERCHANT_SELECTOR = "//option[text()='New Merchant']/parent::select";


    private final Page page;


    public MerchantInitialCreationPage(Page page) {
        this.page = page;
    }

    public MerchantInitialCreationPage fillTradeName(String tradeName) {
        //page.waitForTimeout(5000);
        waitForLocatorLoadState(page, NEW_MERCHANT_SELECTOR, VISIBLE);
        fillElementField(page.locator(TRADE_NAME), tradeName);
        return this;
    }

    public MerchantInitialCreationPage fillMerchantEmail(String merchantEmail) {
        waitForLocatorLoadState(page, NEW_MERCHANT_SELECTOR, VISIBLE);
        fillElementField(page.locator(MERCHANT_EMAIL), merchantEmail);
        return this;
    }


    public MerchantInitialCreationPage chooseBusinessNatureType(String businessNatureType) {
        waitForLocatorLoadState(page, BUSINESS_NATURE, VISIBLE);
        Locator businessNature = page.locator(BUSINESS_NATURE);
        doClickOnElement(businessNature);
        selectOptionFromList(businessNature, businessNatureType);
        doClickOnElement(businessNature);
        return this;
    }


    public MerchantInitialCreationPage chooseRandomBusinessNatureType() {

        Locator businessNature = page.locator(BUSINESS_NATURE);
        businessNature.click();

        List<String> businessNatureList = page.querySelectorAll(BUSINESS_NATURE_LIST)
                .stream()
                .map(ElementHandle::innerText)
                .filter(x -> !x.startsWith(" ")) // text doesn't  start from space sign
                .toList();

        logger.debug("businessNatureList list was aggregated with lengths: " + businessNatureList.size()
                + " and list data:" + businessNatureList);

        String randomBusinessNature = businessNatureList.get(getRandomIntValue(1, businessNatureList.size() - 1));

        businessNature.selectOption(randomBusinessNature);
        businessNature.click();

        return this;
    }


    public MerchantInitialCreationPage unselectSelectedProduct(String selectedProduct) {
        String product = (String.format(SELECTED_PRODUCT, selectedProduct));

        doClickOnElement(page.locator(product));
        doClickOnElement(page.locator(UNSELECT_SELECTED_PRODUCT_BTN));
        return this;
    }

    public MerchantInitialCreationPage selectPosType(String selectedPosType) {
        doClickOnElement(page.locator(POS_TYPE));
        selectOptionFromList(page.locator(POS_TYPE), selectedPosType);
        return this;
    }


    public MerchantInitialCreationPage selectAvailableProduct(String product) {
        String productToChoose = (String.format(AVAILABLE_PRODUCT, product));

        doClickOnElement(page.locator(productToChoose));
        doClickOnElement(page.locator(SELECT_SELECTED_PRODUCT_BTN));
        return this;
    }

    public MerchantInitialCreationPage selectEcomType(String selectedEcomType) {
        doClickOnElement(page.locator(ECOM_TYPE));
        selectOptionFromList(page.locator(ECOM_TYPE), selectedEcomType);
        return this;
    }


    public MerchantInitialCreationPage setNumberOfPos(int posNumber) {
        fillElementField(page.locator(NUMBER_OF_POS), String.valueOf(posNumber));
        return this;
    }


    public void moveToTheSecondApplicationScreen() {
        doClickOnElement(page.locator(NEXT_BTN));
    }


    public MerchantInitialCreationPage selectNg1ECOMAuthType(String ng1AuthType) {
        //doClickOnElement(page.locator(NG1_ECOM_AUTH));
        selectOptionFromList(page.locator(NG1_ECOM_AUTH), ng1AuthType);
        return this;
    }


    public MerchantInitialCreationPage selectNg1SoftPOSAuthType(String ng1AuthType) {
        selectOptionFromList(page.locator(NG1_SOFTPOS_AUTH), ng1AuthType);
        return this;
    }
}
