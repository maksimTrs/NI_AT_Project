package nisfappui.utils;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static nisfappui.tests.BaseTest.logger;


public class TestListener implements ITestListener {

    public void onTestStart(ITestResult iTestResult) {
    }

    public void onTestSuccess(ITestResult iTestResult) {
    }

    public void onTestFailure(ITestResult iTestResult) {
        logger.error("<<< Test failed: " + iTestResult.getName() + ". Test result info was saved in Allure report >>>");
    }

    public void onTestSkipped(ITestResult iTestResult) {
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

    public void onStart(ITestContext iTestContext) {
    }

    public void onFinish(ITestContext iTestContext) {
    }
}
