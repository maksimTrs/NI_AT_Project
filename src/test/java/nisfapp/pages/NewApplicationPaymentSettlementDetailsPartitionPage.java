package nisfapp.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class NewApplicationPaymentSettlementDetailsPartitionPage {


    private static final String BANK_NAME = "//input[@Placeholder='Search Banks...']";
    private static final String BANK_NAME_OPTION = "(//label[contains(text(),'Bank name')]/following-sibling::*//li//*[@title='ENBD'])[1]";
    private static final String TAX_REG_NUMBER = "//label[text()='*Tax registration number (TRN)']//..//input[contains(@name, 'TaxRegistrationNumberTRN')]";
    private static final String PAYMENT_MODE = "//label[contains(text(),'Payment mode')]//..//button[contains(@aria-label, 'Payment mode')]";
    private static final String PAYMENT_MODE_OPTION = "//span[text()='%s']";
    private static final String RENTAL_MODE = "//label[contains(text(),'Rental mode')]//..//button[contains(@aria-label, 'Rental mode')]";
    private static final String RENTAL_MODE_OPTION = "//span[text()='%s']";


    private final Page page;

    public NewApplicationPaymentSettlementDetailsPartitionPage(Page page) {
        this.page = page;
    }


    public NewApplicationPaymentSettlementDetailsPartitionPage fillBankName(String bank) {
        page.locator(BANK_NAME).fill(bank, new Locator.FillOptions().setTimeout(3000));
        page.locator(BANK_NAME).click(new Locator.ClickOptions().setTimeout(2000));
        page.locator(BANK_NAME_OPTION).click();
        return this;
    }

    public NewApplicationPaymentSettlementDetailsPartitionPage fillTaxRegNum(long num) {
        page.locator(TAX_REG_NUMBER).fill(String.valueOf(num));
        return this;
    }


    public NewApplicationPaymentSettlementDetailsPartitionPage fillPaymentMode(String mode) {
        page.locator(PAYMENT_MODE).press("Enter");
        //page.waitForSelector(PAYMENT_MODE_OPTION, new Page.WaitForSelectorOptions().setState(VISIBLE));
        page.locator(String.format(PAYMENT_MODE_OPTION, mode)).click();
        return this;
    }


    public NewApplicationPaymentSettlementDetailsPartitionPage fillRentalMode(String mode) {
        page.locator(RENTAL_MODE).press("Enter", new Locator.PressOptions().setDelay(1000));
        //page.waitForSelector(PAYMENT_MODE_OPTION, new Page.WaitForSelectorOptions().setState(VISIBLE));
        page.locator(String.format(RENTAL_MODE_OPTION, mode)).click();
        return this;
    }
}
