package nisfapp.pages;

import com.microsoft.playwright.Page;
import nisfapp.utils.MethodActionsForPO;

public class NewApplicationPaymentSettlementDetailsPartitionPage extends MethodActionsForPO {


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
        fillElementFieldWithTimeOut(page.locator(BANK_NAME), bank, 3000);
        doClickOnElementWithTimeOut(page.locator(BANK_NAME), 2000);
        doClickOnElement(page.locator(BANK_NAME_OPTION));
        return this;
    }

    public NewApplicationPaymentSettlementDetailsPartitionPage fillTaxRegNum(long num) {
        fillElementField(page.locator(TAX_REG_NUMBER), String.valueOf(num));
        return this;
    }


    public NewApplicationPaymentSettlementDetailsPartitionPage fillPaymentMode(String mode) {
        pressKeyBtn(page.locator(PAYMENT_MODE), "Enter");

        String paymentOpt = String.format(PAYMENT_MODE_OPTION, mode);
        doClickOnElement(page.locator(paymentOpt));
        return this;
    }


    public NewApplicationPaymentSettlementDetailsPartitionPage fillRentalMode(String mode) {
        pressKeyBtnWithDelay(page.locator(RENTAL_MODE), "Enter", 1000);

        String rentalOpt = (String.format(RENTAL_MODE_OPTION, mode));
        doClickOnElement(page.locator(rentalOpt));
        return this;
    }
}
