package nisfapp.pages;

import com.microsoft.playwright.Page;
import nisfapp.utils.MethodActionForPO;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static com.microsoft.playwright.options.WaitForSelectorState.VISIBLE;

public class MainSFAppPage extends MethodActionForPO {

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

    public MainSFAppPage clickOnNavigationMenuType() {
        waitForPageLoadState(page);
        //waitForLocatorLoadState(page, NAVIGATION_MENU, VISIBLE);
        doClickOnElement(page.locator(NAVIGATION_MENU));
        return this;
    }


    public MainSFAppPage chooseOnNavigationMenuType(NavigationMenuPartitions navigationMenuPartition) {
        String locatorMenu = String.format(NAVIGATION_MENU_TYPE, navigationMenuPartition.getDisplayName());
        String locatorMenuHeader = String.format(NAVIGATION_MENU_HEADER, navigationMenuPartition.getDisplayName());


        // waitForLocatorLoadState(page, locatorMenu, VISIBLE);
        //page.locator(String.format(NAVIGATION_MENU_TYPE, navigationMenuPartition.getDisplayName())).click(new Locator.ClickOptions().setDelay(1000));
        doClickOnElementWithDelay(page.locator(locatorMenu), 1000);

        waitForLocatorLoadState(page, locatorMenuHeader, VISIBLE);
        doClickOnElementWithDelay(page.locator(locatorMenuHeader), 1000);
        return this;
    }

    public void clickOnNewAppBtn() {
        waitForLocatorLoadState(page, CREATE_NEW_APP_BTN, VISIBLE);
        doClickOnElement(page.locator(CREATE_NEW_APP_BTN));
    }


    public void assertLogOutBtn() {
        page.waitForSelector(VIEW_PROFILE, new Page.WaitForSelectorOptions().setState(VISIBLE));
        page.locator(VIEW_PROFILE).click();
        assertThat(page.locator(LOGOUT_BTN)).isEnabled();
        assertThat(page.locator(LOGOUT_BTN)).hasText("Log Out");
    }
}
