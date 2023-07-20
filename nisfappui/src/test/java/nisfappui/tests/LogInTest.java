package nisfappui.tests;

import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import io.qameta.allure.testng.Tags;
import org.testng.annotations.Test;
//import static org.assertj.core.api.Assertions.*;


public class LogInTest extends BaseTest {


    @Severity(SeverityLevel.BLOCKER)
    @Owner("Maksim T")
    @Tags({@Tag("UI_TEST"), @Tag("SMOKE_TEST")})
    @Description("Test logIn with correct credentials")
    @Epic("SF Application Testing")
    @Feature("Create POS Application in SF UI")
    @Story("POS Ngenius Application Creation Test")
    @Test(groups = {"SmokeTest"})
    public void signUpTest() {
        logInPage
                .openUrl(SF_URL)
                .fillUserNameAndPasswordFields(SALES_OFFICER_USER)
                .doLogIn();

        mainSFAppPage.assertLogOutBtn();
    }


    @Severity(SeverityLevel.BLOCKER)
    @Owner("Maksim T")
    @Tags({@Tag("UI_TEST"), @Tag("SMOKE_TEST")})
    @Description("Test logIn with wrong credentials")
    @Epic("SF Application Testing")
    @Feature("Create POS Application in SF UI")
    @Story("POS Ngenius Application Creation Test")
    @Test(groups = {"SmokeTest"})
    public void signUpWithErrorTest() {
        logInPage
                .openUrl(SF_URL)
                .fillUserNameAndPasswordFields(INCORRECT_SF_USER)
                .doLogIn();

        logInPage.assertLogInPageTitle();
        logInPage.assertLogInErrorMsg();
    }
}
