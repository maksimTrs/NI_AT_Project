package nisfapp.tests;

import org.testng.annotations.Test;

public class AppTabsClosingTest extends BaseTest {


    @Test
    public void closeAllAppUITabs() {
        doSFLogIn();
        applicationPage.closeAllSFTabs();
        applicationPage.assertAllClosedSFTabs();
    }
}
