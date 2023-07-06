package nisfapp.pages;

import com.microsoft.playwright.Page;
import nisfapp.utils.MethodActionsForPO;

import static com.microsoft.playwright.options.WaitForSelectorState.VISIBLE;

public class DocumentPage extends MethodActionsForPO {

    private static final String MAIN_WINDOW = "//div[contains(@class,'windowViewMode-maximized active')]";
    private static final String APP_DOCUMENT_UPLOAD_BTN = "(//button[@name='Document__c.UploadFiles'])[last()]";

    private static final String APP_DOCUMENT_CLOSE_BTN = "(//a[starts-with(@title,'D-')]/..//button[starts-with(@title,'Close D-')])[last()]";
    private static final String APP_DOCUMENT_FILE_UPLOADER_FIELD = "//input[@name='fileUploader']";
    private static final String APP_DOCUMENT_FILE_UPLOADER_DONE_BTN = "//span[text()='Done']/parent::button[not(@disabled='true')]";

    private final Page page;

    public DocumentPage(Page page) {
        this.page = page;
    }


    public DocumentPage clickOnUploadDocFilesBtn() {
        //page.waitForTimeout(5000);
        waitForLocatorLoadState(page, APP_DOCUMENT_UPLOAD_BTN, VISIBLE);
        doClickOnElement(page.locator(APP_DOCUMENT_UPLOAD_BTN));
        return this;
    }

    public DocumentPage uploadDocFileViaPopUp(String filePath) {
        //page.setInputFiles(APP_DOCUMENT_FILE_UPLOADER_FIELD, Paths.get(filePath));
        setFile(page, APP_DOCUMENT_FILE_UPLOADER_FIELD, filePath);
        doClickOnElement(page.locator(APP_DOCUMENT_FILE_UPLOADER_DONE_BTN));
        return this;
    }


    public void closeDocPartitionAndMoveToAppPage() {
        doClickOnElement(page.locator(APP_DOCUMENT_CLOSE_BTN));
    }
}
