package nisfapp.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.LocatorAssertions;
import nisfapp.utils.MethodActionsForPO;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static com.microsoft.playwright.options.WaitForSelectorState.VISIBLE;
import static nisfapp.tests.BaseTest.logger;
import static nisfapp.utils.MethodAssertionsForPO.*;

public class ApplicationPage extends MethodActionsForPO {

    private static final String MAIN_WINDOW = "//div[contains(@class,'windowViewMode-maximized active')]";
    private static final String PRIMARY_APP_ID = "(//h1/div[text()='Application']//..//slot[@name='primaryField']/lightning-formatted-text)[last()]";
    private static final String CURRENT_APP_SF_TAB = "(//div[@aria-label=\"Workspace tabs for NI Onboarding Console\"]" +
            "//ul[@role='presentation']//a[contains(@title, 'Application')])[last()]";
    private static final String TRADE_NAME = "(//p[contains(@title,'Trade name')]//..//slot//lightning-formatted-text)[last()]";
    private static final String DRAFT_STAGE_FIELD = "(//ul[@role='listbox']/li[@data-name='Draft']/a[@data-tab-name='Draft'])[last()]";
    private static final String APP_ALL_SF_TAB_CLOSE_BTN = "//div[@aria-label=\"Workspace tabs for NI Onboarding Console\"]//ul[@role='presentation']//a[contains(@title, 'Application')]/..//button[contains(@title, 'Close')]";
    private static final String ALL_APP_SF_TABS = "//div[@aria-label=\"Workspace tabs for NI Onboarding Console\"]//a[contains(@title, 'Application')][not(contains(@href, 'javascript'))]";

    private static final String APP_SF_TAB_CLOSE_BTN = "//div[@aria-label=\"Workspace tabs for NI Onboarding Console\"]//ul[@role='presentation']//a[contains(@title, 'Application')]/..//button[@title=\"Close %s | Application\"]";
    private static final String APP_SF_TAB_CLOSE_BTN2 = "(//div[@aria-label=\"Workspace tabs for NI Onboarding Console\"]//ul[@role='presentation']//a[contains(@title, 'Application')]/..//button[contains(@title, 'Close')])[last()]";
    private static final String IBAN = "(//label[contains(text(),'IBAN')]/parent::div//input)[last()]";
    private static final String ACCOUNT_NUMBER = "(//label[contains(text(),'Account number')]/parent::div//input)[last()]";
    private static final String IBAN_ACC_SAVE_BTN = "(//button[text()='Save' and not(@disabled='true')])[last()]";
    private static final String CONTACT_NAME = "(//dt[text()='Contact Name:']/following-sibling::dd//a[@target='_blank'])[last()]";
    private static final String SUBMIT_TO_NEXT_STAGE_BTN = "(//button[text()='Submit to new stage'])[last()]";
    private static final String SUBMIT_TO_NEXT_STAGE_PROCESS_BTN = "//button[text()='Process']";
    private static final String APP_GENERIC_DOCUMENT = "//a//span[starts-with(text(),'D-')]";
    private final Page page;
    private String appSFID;
    private String appSFName;

    public ApplicationPage(Page page) {
        this.page = page;
    }


    public void closeAllSFTabs() {
        page.waitForTimeout(9000);

        Locator listEle = page.locator(ALL_APP_SF_TABS);
        logger.debug("list app tabs count: " + listEle.count());
        logger.debug("list app tabs  info: " + listEle.allInnerTexts());
        for (int i = 0; i < listEle.count(); i++) {
            (listEle.nth(i)).waitFor(new Locator.WaitForOptions()
                    .setTimeout(5000));

            logger.debug(listEle.nth(i).getAttribute("title"));

            String titleText = listEle.nth(i).getAttribute("title");
            String appID = titleText.substring(titleText.indexOf("A-"), titleText.indexOf("|")).trim();

            Locator closeLocator = page.locator(String.format(APP_SF_TAB_CLOSE_BTN, appID));
            closeLocator.waitFor(new Locator.WaitForOptions()
                    .setState(VISIBLE)
                    .setTimeout(5000));

            // listEle.nth(i).click(new Locator.ClickOptions().setDelay(1000));
            listEle.nth(i).click();
            closeLocator.click();
        }
    }


    public ApplicationPage openCurrentSFAppTab() {
        waitForLocatorLoadState(page, CURRENT_APP_SF_TAB, VISIBLE);
        doClickOnElement(page.locator(CURRENT_APP_SF_TAB));
        return this;
    }


    public ApplicationPage fillBusinessSensitivePartition(String iban, String accNum) {
        //page.waitForTimeout(3000);
        waitForLocatorLoadState(page, IBAN, VISIBLE);

        fillElementField(page.locator(IBAN), iban);
        fillElementField(page.locator(ACCOUNT_NUMBER), accNum);
        doClickOnElement(page.locator(IBAN_ACC_SAVE_BTN));
        return this;
    }


    public ContactPage openAppContactPage() {
        //page.waitForTimeout(5000);
        appSFID = page.locator(PRIMARY_APP_ID).innerText();

        waitForLocatorLoadState(page, CONTACT_NAME, VISIBLE);
        // page.locator(CONTACT_NAME).click();
        //page.waitForTimeout(3000);
        Page contactPage = page.waitForPopup(() -> doClickOnElement(page.locator(CONTACT_NAME)));
        return new ContactPage(contactPage);
    }


    public void submitToNextStage() {
        page.reload();
        doClickOnElement(page.locator(SUBMIT_TO_NEXT_STAGE_BTN));
        doClickOnElement(page.locator(SUBMIT_TO_NEXT_STAGE_PROCESS_BTN));
    }

    public void openAppGenericDocument() {
        //page.waitForTimeout(5000);
        waitForLocatorLoadState(page, MAIN_WINDOW + APP_GENERIC_DOCUMENT, VISIBLE);
        doClickOnElement(page.locator(MAIN_WINDOW + APP_GENERIC_DOCUMENT));
    }


    public ApplicationPage assertApplicationPrimaryId() {
        //assertThat(page.locator(PRIMARY_APP_ID)).hasText(Pattern.compile("A-\\d{9,}"));
        String appID = page.locator(PRIMARY_APP_ID).innerText();
        logger.info("<<<<< Application was created with ID: " + appID + " >>>>>");

        String appId = page.locator(PRIMARY_APP_ID).innerText();
        assertElementHasMatches(appId, "A-\\d{9,}");
        return this;
    }

    public ApplicationPage assertApplicationTradeName() {
        String appName = page.locator(TRADE_NAME).innerText();
        logger.info("<<<<< Application was created with Trade Name: " + appName + " >>>>>");
        appSFName = page.locator(TRADE_NAME).innerText();

        assertElementContainsText(appName, "AT TEST");
        return this;
    }

    public ApplicationPage assertDraftStageIsChosen() {
        waitForLocatorLoadState(page, DRAFT_STAGE_FIELD, VISIBLE);

        String ariaSelected = page.locator(DRAFT_STAGE_FIELD).getAttribute("aria-selected");
        String ariaCurrent = page.locator(DRAFT_STAGE_FIELD).getAttribute("aria-current");
        assertElementHasText(ariaSelected, "true");
        assertElementHasText(ariaCurrent, "true");

        return this;
    }


    public void assertAllClosedSFTabs() {
        assertThat(page.locator(ALL_APP_SF_TABS)).hasCount(0, new LocatorAssertions.HasCountOptions().setTimeout(15000));
    }


    public void assertAppIdFromContactPageReturning() {
        // assertThat(page.locator(PRIMARY_APP_ID)).hasText(appSFID);
        logger.debug("<<<<< Contact's Application:" + appSFName + " has ID: " + appSFID + " >>>>>");

        assertElementHasText(page.locator(PRIMARY_APP_ID).innerText(), appSFID);
    }

    public void assertAppIdFromDocumentPageReturning() {
        //assertThat(page.locator(PRIMARY_APP_ID)).hasText(appSFID);
        waitForLocatorLoadState(page, PRIMARY_APP_ID, VISIBLE);
        logger.debug("<<<<< Document's Application" + appSFName + " has ID: " + appSFID + " >>>>>");

        assertElementHasText(page.locator(PRIMARY_APP_ID).innerText(), appSFID);
    }
}
