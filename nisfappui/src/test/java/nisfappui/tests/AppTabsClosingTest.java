package nisfappui.tests;

import org.testng.annotations.Test;

import static nisfappui.utils.MethodAssertionsForPO.assertElementHasCount;

public class AppTabsClosingTest extends BaseTest {


    @Test(enabled = true)
    public void closeAllAppUITabs() {
        doSFLogIn(SF_URL, SALES_OFFICER_USER);

        applicationPage.closeAllSFTabs();

        assertElementHasCount(applicationPage.getSFApplicationTabsCount(), 0);
    }
}

