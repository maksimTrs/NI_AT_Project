package nisfapp.pages;

import com.microsoft.playwright.Page;
import nisfapp.utils.MethodActionForPO;

public class NewApplicationSalesOfficerInspectionPartitionPage extends MethodActionForPO {

    private static String originalDocumentImageVerifiedCheckBox = "//div[contains(@class,'maximized active')]//span[text()='* Original document image verified']";


    private final Page page;

    public NewApplicationSalesOfficerInspectionPartitionPage(Page page) {
        this.page = page;
    }


    public NewApplicationSalesOfficerInspectionPartitionPage clickOnOriginalDocumentImageVerifiedCheckBox(boolean activateCheckbox) {
        if (activateCheckbox && !page.locator(originalDocumentImageVerifiedCheckBox).isChecked()) {
            doClickOnElement(page.locator(originalDocumentImageVerifiedCheckBox));
        }
        return this;
    }
}
