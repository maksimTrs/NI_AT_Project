package nisfapp.pages;

import com.microsoft.playwright.Page;

import java.nio.file.Paths;

public class DocumentPage {

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
        page.waitForTimeout(15000);
        page.locator(APP_DOCUMENT_UPLOAD_BTN).click();
        return this;
    }

    public DocumentPage uploadDocFileViaPopUp(String filePath) {
        page.setInputFiles(APP_DOCUMENT_FILE_UPLOADER_FIELD, Paths.get(filePath));
        page.locator(APP_DOCUMENT_FILE_UPLOADER_DONE_BTN).click();
        return this;
    }


    public void closeDocPartitionAndMoveToAppPage() {
        //page.bringToFront();
        //page.close();
        page.locator(APP_DOCUMENT_CLOSE_BTN).click();
    }
}
