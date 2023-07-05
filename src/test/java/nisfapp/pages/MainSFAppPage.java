package nisfapp.pages;

import com.microsoft.playwright.Page;
import nisfapp.utils.MethodActionsForPO;

import static com.microsoft.playwright.options.WaitForSelectorState.VISIBLE;
import static nisfapp.utils.MethodAssertionsForPO.assertElementHasText;
import static nisfapp.utils.MethodAssertionsForPO.assertElementIsEnabled;

public class MainSFAppPage extends MethodActionsForPO {

    private static final String VIEW_PROFILE = "//img[@title='User']/ancestor::button[@data-aura-class='forceHeaderButton']";
    private static final String LOGOUT_BTN = "//div/a[contains(@href, '/logout.')][@dir='ltr']";
    private static final String NAVIGATION_MENU = "button[title='Show Navigation Menu']";
    private static final String CREATE_NEW_APP_BTN = "li > a[title='New']";
    private static final String NAVIGATION_MENU_TYPE = "//ul[@role='group']//a[@role='option']//span[text()='%s']";
    private static final String NAVIGATION_MENU_HEADER = "//div/a[@title='%s']";
    private final Page page;


    public MainSFAppPage(Page page) {
        this.page = page;
    }

    //@Step("Open SF navigation menu tab")
    public MainSFAppPage clickOnNavigationMenuType() {
        waitForPageLoadState(page);
        doClickOnElement(page.locator(NAVIGATION_MENU));
        return this;
    }

    //@Step("Choose navigation menu tab type: {0}")
    public MainSFAppPage chooseOnNavigationMenuType(NavigationMenuPartitions navigationMenuPartition) {
        String locatorMenu = String.format(NAVIGATION_MENU_TYPE, navigationMenuPartition.getDisplayName());
        String locatorMenuHeader = String.format(NAVIGATION_MENU_HEADER, navigationMenuPartition.getDisplayName());

        doClickOnElementWithDelay(page.locator(locatorMenu), 1000);

        waitForLocatorLoadState(page, locatorMenuHeader, VISIBLE);
        doClickOnElementWithDelay(page.locator(locatorMenuHeader), 1000);
        return this;
    }

    //@Step("Click in 'New' button and open initial 'new merchant' window")
    public void clickOnNewAppBtn() {
        waitForLocatorLoadState(page, CREATE_NEW_APP_BTN, VISIBLE);
        doClickOnElement(page.locator(CREATE_NEW_APP_BTN));
    }


    public void assertLogOutBtn() {
        waitForLocatorLoadState(page, VIEW_PROFILE, VISIBLE);
        page.locator(VIEW_PROFILE).click();

        boolean logOutBtnIsActive = page.locator(LOGOUT_BTN).isEnabled();
        String logOutBtnName = page.locator(LOGOUT_BTN).innerText();
        assertElementIsEnabled(logOutBtnIsActive);
        assertElementHasText(logOutBtnName, "Log Out");
    }
}
