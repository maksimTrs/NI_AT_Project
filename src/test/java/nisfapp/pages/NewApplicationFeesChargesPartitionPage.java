package nisfapp.pages;

import com.microsoft.playwright.Page;

public class NewApplicationFeesChargesPartitionPage {

    private static final String MIS_MONTH_REPORT_FEE = "//input[contains(@name, 'MisMonthlyReportFee')]";


    private final Page page;

    public NewApplicationFeesChargesPartitionPage(Page page) {
        this.page = page;
    }


    public NewApplicationFeesChargesPartitionPage fillMisMonthReportFee(double num) {
        page.locator(MIS_MONTH_REPORT_FEE).fill(String.valueOf(num));
        return this;
    }
}
