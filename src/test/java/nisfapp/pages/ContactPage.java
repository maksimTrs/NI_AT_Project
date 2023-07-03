package nisfapp.pages;

import com.microsoft.playwright.Page;

public class ContactPage {

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
        page.locator(EDIT_BTN).click();
        return this;
    }

    public ContactPage editPepField(boolean yesNo) {
        String pepTypeYesOrNo = yesNo ? PEP_YES : PEP_NO;
        page.locator(PEP_FIELD).click();
        page.locator(pepTypeYesOrNo).click();
        return this;
    }

    public ContactPage saveContact() {
        page.waitForTimeout(3000);
        page.locator(SAVE_BTN).click();
        return this;
    }

    public void moveToAppPage() {
        //page.bringToFront();
        page.close();
    }
}
