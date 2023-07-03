package nisfapp.pages;

import com.microsoft.playwright.Page;

public class NewApplicationAuthorizedSignatoryPartitionPage {

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
        page.locator(MOBILE_PHONE).fill(phoneNumber);
        return this;
    }

    public NewApplicationAuthorizedSignatoryPartitionPage fillPassportNum(String passport) {
        page.locator(PASSPORT_NUMBER).fill(passport);
        return this;
    }


    public NewApplicationAuthorizedSignatoryPartitionPage fillPassportExpDate(String expirationDate) {
        page.locator(PASSPORT_EXP_DATE).fill(expirationDate);
        return this;
    }

    public NewApplicationAuthorizedSignatoryPartitionPage fillContactBirthday(String bd) {
        page.locator(BIRTHDAY_DATE).fill(bd);
        return this;
    }

    public NewApplicationAuthorizedSignatoryPartitionPage fillFirstAndLastName(String fn, String ln) {
        page.locator(FIRST_NAME).fill(fn);
        page.locator(LAST_NAME).fill(ln);
        return this;
    }

    public NewApplicationAuthorizedSignatoryPartitionPage fillNationality(String nationality) {
        page.locator(NATIONALITY).press("Enter");
        //page.waitForSelector(NATIONALITY_OPTION, new Page.WaitForSelectorOptions().setState(VISIBLE));
        page.locator(String.format(NATIONALITY_OPTION, nationality)).click();
        return this;
    }
}
