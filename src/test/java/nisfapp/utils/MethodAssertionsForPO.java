package nisfapp.utils;

import io.qameta.allure.Allure;
import org.assertj.core.api.Assertions;

import static org.assertj.core.api.Assertions.*;
public abstract class MethodAssertionsForPO {

    public static void assertElementHasText(String actualResult, String expectedResult) {
        try {
            assertThat(actualResult)
                    .isEqualTo(expectedResult)
                    .as("Validate element has  inner text");

            Allure.step("Assertion passed: Actual value matches expected value");
        } catch (AssertionError e) {
            Allure.step("Assertion failed: Actual value does not match expected value: ");
            Allure.attachment("Expected Value", String.valueOf(expectedResult));
            Allure.attachment("Actual Value", String.valueOf(actualResult));
            throw e;
        }
    }

    public static void assertElementContainsText(String actualResult, String expectedResult) {
        try {
            assertThat(actualResult)
                    .contains(expectedResult)
                    .as("Validate element contains inner text");

            Allure.step("Assertion passed: Actual value matches expected value");
        } catch (AssertionError e) {
            Allure.step("Assertion failed: Actual value does not match expected value: ");
            Allure.attachment("Expected Value", String.valueOf(expectedResult));
            Allure.attachment("Actual Value", String.valueOf(actualResult));
            throw e;
        }
    }
}
