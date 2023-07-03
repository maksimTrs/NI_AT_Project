package nisfapp.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static com.microsoft.playwright.options.WaitForSelectorState.VISIBLE;

public class MainSFAppPage {

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

    public void assertLogOutBtn() {
        page.waitForSelector(VIEW_PROFILE, new Page.WaitForSelectorOptions().setState(VISIBLE));
        page.locator(VIEW_PROFILE).click();
        assertThat(page.locator(LOGOUT_BTN)).isEnabled();
        assertThat(page.locator(LOGOUT_BTN)).hasText("Log Out");
    }

    public MainSFAppPage clickOnNavigationMenuType() {
        page.locator(NAVIGATION_MENU).click();
        return this;
    }


    public MainSFAppPage chooseOnNavigationMenuType(NavigationMenuPartitions navigationMenuPartition) {
        page.waitForSelector(String.format(NAVIGATION_MENU_TYPE, navigationMenuPartition.getDisplayName()), new Page.WaitForSelectorOptions().setState(VISIBLE));
        page.locator(String.format(NAVIGATION_MENU_TYPE, navigationMenuPartition.getDisplayName())).click(new Locator.ClickOptions().setDelay(1000));
        page.locator(String.format(NAVIGATION_MENU_HEADER, navigationMenuPartition.getDisplayName())).click(new Locator.ClickOptions().setDelay(1000));
        return this;
    }

    public void clickOnNewAppBtn() {
        page.waitForSelector(CREATE_NEW_APP_BTN, new Page.WaitForSelectorOptions().setState(VISIBLE));
        page.locator(CREATE_NEW_APP_BTN).click();
        page.waitForTimeout(5000);
    }
}
