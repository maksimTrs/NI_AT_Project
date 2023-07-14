package nisfapp.tests;

import org.testng.annotations.Test;

public class AppTabsClosingTest extends BaseApiTest {


    @Test(enabled = false)
    public void closeAllAppUITabs() {
        doSFLogIn(SF_URL, SALES_OFFICER_USER);
        applicationPage.closeAllSFTabs();
        applicationPage.assertAllClosedSFTabs();
    }
}
