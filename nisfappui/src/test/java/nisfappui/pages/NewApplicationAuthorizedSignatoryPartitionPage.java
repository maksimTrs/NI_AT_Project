package nisfappui.pages;

import com.microsoft.playwright.Page;
import nisfappui.utils.MethodActionsForPO;

public class NewApplicationAuthorizedSignatoryPartitionPage extends MethodActionsForPO {

    private static final String FIRST_NAME = "//label[text()='First Name']//..//input[contains(@name, 'Contact_FirstName')]";
    private static final String LAST_NAME = "//label[text()='Last Name']//..//input[contains(@name, 'Contact_LastName')]";
    private static final String MOBILE_PHONE = "//label[text()='* Mobile Phone']//..//input[contains(@name,'Contact_MobilePhone')]";
    private static final String PASSPORT_NUMBER = "//label[text()='Passport number']//..//input[contains(@name, 'PassportNumber')]";
    private static final String NATIONALITY = "//label[text()='* Nationality']//..//button[contains(@aria-label, 'Nationality')]";
    private static final String NATIONALITY_OPTION = "(//span[@title='%s'])[2]";
    private static final String PASSPORT_EXP_DATE = "//label[text()='* Passport expiration date']//..//input[contains(@name,'Contact_PassportExpirationDate')]";
    private static final String BIRTHDAY_DATE = "//label[text()='Birthdate']//..//input[contains(@name,'BirthDate')]";


    private final Page page;

    public NewApplicationAuthorizedSignatoryPartitionPage(Page page) {
        this.page = page;
    }


    public NewApplicationAuthorizedSignatoryPartitionPage fillMobilPhone(String phoneNumber) {
        fillElementField(page.locator(MOBILE_PHONE), phoneNumber);
        return this;
    }

    public NewApplicationAuthorizedSignatoryPartitionPage fillPassportNum(String passport) {
        fillElementField(page.locator(PASSPORT_NUMBER), passport);
        return this;
    }


    public NewApplicationAuthorizedSignatoryPartitionPage fillPassportExpDate(String expirationDate) {
        fillElementField(page.locator(PASSPORT_EXP_DATE), expirationDate);
        return this;
    }

    public NewApplicationAuthorizedSignatoryPartitionPage fillContactBirthday(String bd) {
        fillElementField(page.locator(BIRTHDAY_DATE), bd);
        return this;
    }

    public NewApplicationAuthorizedSignatoryPartitionPage fillFirstAndLastName(String fn, String ln) {
        fillElementField(page.locator(FIRST_NAME), fn);
        fillElementField(page.locator(LAST_NAME), ln);
        return this;
    }

    public NewApplicationAuthorizedSignatoryPartitionPage fillNationality(String nationality) {
        pressKeyBtn(page.locator(NATIONALITY), "Enter");

        String nationOpt = String.format(NATIONALITY_OPTION, nationality);
        doClickOnElement(page.locator(nationOpt));
        return this;
    }
}
