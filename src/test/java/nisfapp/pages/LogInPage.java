package nisfapp.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.LocatorAssertions;
import nisfapp.model.User;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LogInPage {

    private static final String USER_NAME_FIELD = "input#username";
    private static final String PASSWORD_FIELD = "input#password";
    private static final String LOGIN_TO_SANDBOX_BTN = "input#Login";
    private static final String LOGIN_ERROR_MSG = "div#error";
    private final Page page;


    public LogInPage(Page page) {
        this.page = page;
    }

    public LogInPage openUrl(String pageUrl) {
        page.navigate(pageUrl);
        return this;
    }

    public LogInPage fillUserNameAndPasswordFields(User user) {
        page.locator(USER_NAME_FIELD).fill(user.getUsername());
        page.locator(PASSWORD_FIELD).fill(user.getPassword());
        return this;
    }

    public void doLogIn() {
        page.locator(LOGIN_TO_SANDBOX_BTN).click();
        page.waitForTimeout(9000);
    }

    public void assertErrorMsg() {
        assertThat(page.locator(LOGIN_ERROR_MSG)).isVisible(new LocatorAssertions.IsVisibleOptions().setTimeout(2000));
        assertThat(page.locator(LOGIN_ERROR_MSG))
                .containsText("Please check your username and password. If you still can't log in, contact your Salesforce administrator");
    }

    public void assertLogInPageTitle() {
        assertThat(page)
                .hasTitle("Login | Salesforce111");
    }
}
