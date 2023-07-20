package nisfappui.utils;

import io.qameta.allure.Allure;

import static org.assertj.core.api.Assertions.assertThat;

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


    public static void assertElementIsEnabled(boolean buttonIsActive) {
        try {
            assertThat(buttonIsActive)
                    .isTrue()
                    .as("Validate element (<button>, <select>, <input> or <textarea>) is active on the page");

            Allure.step("Assertion passed: page element is active on the page");
        } catch (AssertionError e) {
            Allure.step("Assertion failed: page element  is not active on the page");
            throw e;
        }
    }

    public static void assertElementHasMatches(String element, String matchesCondition) {
        try {
            assertThat(element)
                    .matches(matchesCondition)
                    .as("Validate element regex matches");

            Allure.step("Assertion passed: Actual value matches expected value");
        } catch (AssertionError e) {
            Allure.step("Assertion failed: Actual value does not match expected value: ");
            Allure.attachment("Actual Value", String.valueOf(element));
            Allure.attachment("Regex Value", String.valueOf(matchesCondition));
            throw e;
        }
    }



    public static void assertElementHasCount(int actualResult, int expectedResult) {
        try {
            assertThat(actualResult)
                    .as("Validate element count")
                    .isEqualTo(expectedResult);

            Allure.step("Assertion passed: Actual element count has expected value");
        } catch (AssertionError e) {
            Allure.step("Assertion failed: Actual element count does not have expected value: ");
            Allure.attachment("Expected Value", String.valueOf(expectedResult));
            Allure.attachment("Actual Value", String.valueOf(actualResult));
            throw e;
        }
    }
}
