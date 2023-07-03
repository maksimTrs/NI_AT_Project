package nisfapp.pages;

import com.microsoft.playwright.Page;

public class NewApplicationSalesOfficerInspectionPartitionPage {

    private static String originalDocumentImageVerifiedCheckBox = "//div[contains(@class,'maximized active')]//span[text()='* Original document image verified']";


    private final Page page;

    public NewApplicationSalesOfficerInspectionPartitionPage(Page page) {
        this.page = page;
    }


    public NewApplicationSalesOfficerInspectionPartitionPage clickOnOriginalDocumentImageVerifiedCheckBox(boolean activateCheckbox) {
        if (activateCheckbox) {
            page.locator(originalDocumentImageVerifiedCheckBox).click();
        }
        return this;
    }
}
