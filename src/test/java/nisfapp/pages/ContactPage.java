package nisfapp.pages;

import com.microsoft.playwright.Page;
import nisfapp.utils.MethodActionsForPO;

public class ContactPage extends MethodActionsForPO {

    private static final String MAIN_WINDOW = "//div[contains(@class,'windowViewMode-maximized active')]";

    private static final String PEP_FIELD = "//button[contains(@aria-label,'as PEP')]";
    private static final String EDIT_BTN = "//button[text()='Edit']";
    private static final String SAVE_BTN = "//div[contains(@class,'isModal active')]//button[text()='Save']";
    private static final String PEP_NO = "//lightning-base-combobox-item[@data-value='No']";
    private static final String PEP_YES = "//lightning-base-combobox-item[@data-value='Yes']";


    private final Page page;

    public ContactPage(Page page) {
        this.page = page;
    }


    public ContactPage editContact() {
        doClickOnElement(page.locator(EDIT_BTN));
        return this;
    }

    public ContactPage editPepField(boolean yesNo) {
        String pepTypeYesOrNo = yesNo ? PEP_YES : PEP_NO;

        doClickOnElement(page.locator(PEP_FIELD));
        //doClickOnElement(page.locator(pepTypeYesOrNo));
        doClickOnElementWithDelay(page.locator(pepTypeYesOrNo), 1000);
        return this;
    }

    public ContactPage saveContact() {
        //page.waitForTimeout(3000);
        doClickOnElementWithDelay(page.locator(SAVE_BTN), 1000);
        return this;
    }

    public void moveToAppPage() {
        //page.bringToFront();
        page.close();
    }
}
