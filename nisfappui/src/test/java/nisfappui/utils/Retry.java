package nisfappui.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import static nisfappui.tests.BaseTest.logger;

public class Retry implements IRetryAnalyzer {
    private int retryCount = 0;

    public boolean retry(ITestResult result) {
        int maxRetryCount = 1;
        if (retryCount < maxRetryCount) {
            retryCount++;
            logger.info("<<<<< Retry #" + retryCount + " for test: " + result.getMethod().getMethodName()
                    + ", on thread: " + Thread.currentThread().getName() + " >>>>>");

            return true;
        }
        return false;
    }
}