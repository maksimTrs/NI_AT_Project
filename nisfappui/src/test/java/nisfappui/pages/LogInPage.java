package nisfappui.pages;

import com.microsoft.playwright.Page;
import io.qameta.allure.Param;
import io.qameta.allure.Step;
import nisfappui.model.User;
import nisfappui.utils.MethodActionsForPO;

import static com.microsoft.playwright.options.WaitForSelectorState.VISIBLE;
import static io.qameta.allure.model.Parameter.Mode.MASKED;
import static nisfappui.utils.MethodAssertionsForPO.assertElementContainsText;
import static nisfappui.utils.MethodAssertionsForPO.assertElementHasText;

public class LogInPage extends MethodActionsForPO {

    private static final String USER_NAME_FIELD = "input#username";
    private static final String PASSWORD_FIELD = "input#password";
    private static final String LOGIN_TO_SANDBOX_BTN = "input#Login";
    private static final String LOGIN_ERROR_MSG = "div#error";
    private final Page page;


    public LogInPage(Page page) {
        this.page = page;
    }

    @Step("Open SF URL: {0}")
    public LogInPage openUrl(String pageUrl) {
        page.navigate(pageUrl);
        waitForPageLoadState(page);
        return this;
    }

    @Step("Fill SF credentials: username and password")
    public LogInPage fillUserNameAndPasswordFields(@Param(name = "UserCredentials", mode = MASKED) User user) {
        waitForLocatorLoadState(page, USER_NAME_FIELD, VISIBLE);
        fillElementField(page.locator(USER_NAME_FIELD), user.getUsername());
        fillElementField(page.locator(PASSWORD_FIELD), user.getPassword());
        return this;
    }

    @Step("Click the login button and enter SF Application")
    public void doLogIn() {
        waitForLocatorLoadState(page, LOGIN_TO_SANDBOX_BTN, VISIBLE);
        doClickOnElement(page.locator(LOGIN_TO_SANDBOX_BTN));
        waitForPageLoadState(page);
    }


    public String getLogInErrorMsg() {
        waitForLocatorLoadState(page, LOGIN_ERROR_MSG, VISIBLE);
        return page.locator(LOGIN_ERROR_MSG).innerText();
    }

    public String getLogInPageTitle() {
        return page.title();
    }
}
