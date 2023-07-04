package nisfapp.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import nisfapp.utils.MethodActionForPO;

import java.util.List;

import static com.microsoft.playwright.options.WaitForSelectorState.VISIBLE;
import static nisfapp.tests.BaseTest.logger;
import static nisfapp.utils.AppDataFaker.getRandomIntValue;


public class MerchantInitialCreationPage extends MethodActionForPO {

    private static final String TRADE_NAME = "//input[@placeholder='Merchant inc.']";
    private static final String MERCHANT_EMAIL = "//input[@inputmode='email']";
    private static final String BUSINESS_NATURE = "(//*[text()='Business Nature']/../..//select)[1]";
    private static final String BUSINESS_NATURE_LIST = "(//*[text()='Business Nature']/../..//select)[1]/option";
    private static final String SELECTED_PRODUCT = "(//span[text()='Selected Product']//parent::div//span[text()='%s'])[1]";
    private static final String UNSELECT_SELECTED_PRODUCT_BTN = "(//button[@title='Move selection to Available Product'])[1]";
    private static final String POS_TYPE = "(//span[text()='POS Type']//parent::label//following-sibling::div//select)[1]";
    private static final String ECOM_TYPE = "(//span[text()='Gateway Type']//parent::label//following-sibling::div//select)[1]";
    private static final String NUMBER_OF_POS = "(//div/input[@id='input-02'])[1]";
    private static final String NEXT_BTN = "//button[text()='Next']";


    private final Page page;


    public MerchantInitialCreationPage(Page page) {
        this.page = page;
    }

    public MerchantInitialCreationPage fillTradeName(String tradeName) {
        page.waitForTimeout(5000);
        doClickOnElement(page.locator(TRADE_NAME));
        fillElementField(page.locator(TRADE_NAME), tradeName);
        return this;
    }

    public MerchantInitialCreationPage fillMerchantEmail(String merchantEmail) {
        waitForLocatorLoadState(page, MERCHANT_EMAIL, VISIBLE);
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

        //page.waitForSelector(BUSINESS_NATURE_LIST, new Page.WaitForSelectorOptions().setTimeout(5000));

        List<String> businessNatureList = page.querySelectorAll(BUSINESS_NATURE_LIST)
                .stream()
                .map(x -> x.innerText())
                .filter(x -> !x.startsWith(" ")) // text doesn't  start from space sign
                .toList();

        logger.debug("businessNatureList list was aggregated with lengths: " + businessNatureList.size()
                + " and list data:" + businessNatureList);

        String randomBusinessNature = businessNatureList.get(getRandomIntValue(1, businessNatureList.size() - 1));

        //businessNature.selectOption(randomBusinessNature, new Locator.SelectOptionOptions().setTimeout(2000));
        businessNature.selectOption(randomBusinessNature);
        businessNature.click();

        return this;
    }


    public MerchantInitialCreationPage unselectSelectedProduct(String selectedProduct) {
        String product = (String.format(SELECTED_PRODUCT, selectedProduct));

        //waitForLocatorLoadState(page, product, VISIBLE);
        doClickOnElement(page.locator(product));
        doClickOnElement(page.locator(UNSELECT_SELECTED_PRODUCT_BTN));
        return this;
    }

    public MerchantInitialCreationPage selectPosType(String selectedPosType) {
        //waitForLocatorLoadState(page, POS_TYPE, VISIBLE);
        doClickOnElement(page.locator(POS_TYPE));
        selectOptionFromList(page.locator(POS_TYPE), selectedPosType);
        return this;
    }

    public MerchantInitialCreationPage selectEcomType(String selectedEcomType) {
        //waitForLocatorLoadState(page, ECOM_TYPE, VISIBLE);
        doClickOnElement(page.locator(ECOM_TYPE));
        selectOptionFromList(page.locator(ECOM_TYPE), selectedEcomType);
        return this;
    }


    public MerchantInitialCreationPage setNumberOfPos(int posNumber) {
        //waitForLocatorLoadState(page, NUMBER_OF_POS, VISIBLE);
        fillElementField(page.locator(NUMBER_OF_POS), String.valueOf(posNumber));
        return this;
    }


    public void moveToTheSecondApplicationScreen() {
        doClickOnElement(page.locator(NEXT_BTN));
    }
}
