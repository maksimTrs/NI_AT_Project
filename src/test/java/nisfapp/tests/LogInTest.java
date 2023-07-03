package nisfapp.tests;

import org.testng.annotations.Test;
//import static org.assertj.core.api.Assertions.*;


public class LogInTest extends BaseTest {


    @Test
    public void signUpTest() {
        logInPage
                .openUrl(SF_URL)
                .fillUserNameAndPasswordFields(SALES_OFFICER_USER)
                .doLogIn();

        mainSFAppPage.assertLogOutBtn();
    }

    @Test
    public void signUpWithErrorTest() {
        logInPage
                .openUrl(SF_URL)
                .fillUserNameAndPasswordFields(INCORRECT_SF_USER)
                .doLogIn();

        logInPage.assertLogInPageTitle();
        logInPage.assertErrorMsg();
    }
}
