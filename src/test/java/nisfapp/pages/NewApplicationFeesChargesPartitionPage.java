package nisfapp.pages;

import com.microsoft.playwright.Page;
import nisfapp.utils.MethodActionsForPO;

public class NewApplicationFeesChargesPartitionPage extends MethodActionsForPO {

    private static final String MIS_MONTH_REPORT_FEE = "//input[contains(@name, 'MisMonthlyReportFee')]";


    private final Page page;

    public NewApplicationFeesChargesPartitionPage(Page page) {
        this.page = page;
    }


    public NewApplicationFeesChargesPartitionPage fillMisMonthReportFee(double num) {
        fillElementField(page.locator(MIS_MONTH_REPORT_FEE), String.valueOf(num));
        return this;
    }
}
