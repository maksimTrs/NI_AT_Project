package nisfappui.tests;

import io.qameta.allure.Flaky;
import io.qameta.allure.Issue;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import static nisfappui.utils.MethodAssertionsForPO.assertElementHasCount;

public class AppTabsClosingTest extends BaseTest {


    @Flaky
    @TmsLink(value = "2057")
    @Issue(value = "2058")
    @Test(enabled = true)
    public void closeAllAppUITabs() {
        doSFLogIn(SF_URL, SALES_OFFICER_USER);

        applicationPage.closeAllSFTabs();

       // assertElementHasCount(applicationPage.getSFApplicationTabsCount(), 0);
    }
}

