package nisfapp.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

import static com.microsoft.playwright.options.LoadState.LOAD;

public abstract class MethodActionForPO {

    public void customWaiterForElement(Page page, String elementXpathOrCss) {
        page.locator(elementXpathOrCss)
                .waitFor(new Locator.WaitForOptions()
                        .setState(WaitForSelectorState.VISIBLE)
                        .setTimeout(15000));
    }

    public void waitForPageLoadState(Page page) {
        page.waitForLoadState(LOAD);
    }

    public void waitForLocatorLoadTimeout(Page page, String locatorXpathOrCssPath, int timeOut) {
        page.waitForSelector(locatorXpathOrCssPath, new Page.WaitForSelectorOptions().setTimeout(timeOut));
    }

    public void waitForLocatorLoadState(Page page, String locatorXpathOrCssPath, WaitForSelectorState waitForSelectorState) {
        page.waitForSelector(locatorXpathOrCssPath, new Page.WaitForSelectorOptions().setState(waitForSelectorState)); // VISIBLE
    }

    public void doClickOnElement(Locator locator) {
        locator.click();
    }

    public void doClickOnElementWithTimeOut(Locator locator, int timeOut) {
        locator.click(new Locator.ClickOptions().setTimeout(timeOut));
    }

    public void doClickOnElementWithDelay(Locator locator, int timeOut) {
        locator.click(new Locator.ClickOptions().setDelay(timeOut));
    }

    public void fillElementField(Locator locator, String value) {
        locator.fill(value);
    }

    public void fillElementFieldWithTimeOut(Locator locator, String value, int timeOut) {
        locator.fill(value, new Locator.FillOptions().setTimeout(timeOut));
    }
}
