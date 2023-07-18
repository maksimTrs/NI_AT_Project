package nisfapp.tests;

import org.testng.annotations.Test;

public class AppTabsClosingTest extends BaseTest {


    @Test(enabled = true)
    public void closeAllAppUITabs() {
        doSFLogIn(SF_URL, SALES_OFFICER_USER);
        applicationPage.closeAllSFTabs();
        applicationPage.assertAllClosedSFTabs();
    }
}

