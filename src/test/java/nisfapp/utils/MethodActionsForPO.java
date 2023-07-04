package nisfapp.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.nio.file.Paths;

import static com.microsoft.playwright.options.LoadState.LOAD;

public abstract class MethodActionsForPO {

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

    public void pressKeyBtn(Locator locator, String keyBoardKey) {
        locator.press(keyBoardKey);
    }

    public void pressKeyBtnWithDelay(Locator locator, String keyBoardKey, int delay) {
        locator.press(keyBoardKey, new Locator.PressOptions().setDelay(delay));
    }

    public void setFile(Page page, String elementPath, String path) {
        page.setInputFiles(elementPath, Paths.get(path));
    }

    public void selectOptionFromList(Locator locator, String value) {
        locator.selectOption(value);
    }

    public void selectOptionFromListWithTimeOut(Locator locator, String value, int timeOut) {
        locator.selectOption(value, new Locator.SelectOptionOptions().setTimeout(timeOut));
    }
}
