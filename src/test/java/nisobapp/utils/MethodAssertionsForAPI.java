package nisobapp.utils;

import io.qameta.allure.Allure;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class MethodAssertionsForAPI {

    public static void assertApiResponseStatusCode(int actualResult, int expectedResult) {
        try {
            assertThat(actualResult)
                    .as("Validate element has  status code " + actualResult)
                    .isEqualTo(expectedResult);

            Allure.step("Assertion passed: Actual status code matches expected status code");
        } catch (AssertionError e) {
            Allure.step("Assertion failed: Actual status code does not match expected status code: ");
            Allure.attachment("Expected Value", String.valueOf(expectedResult));
            Allure.attachment("Actual Value", String.valueOf(actualResult));
            throw e;
        }
    }


    public static void assertApiResponseElementHasValue(String actualResult, String expectedResult) {
        try {
            assertThat(actualResult)
                    .isEqualTo(expectedResult)
                    .as("Validate element has  value" + actualResult);

            Allure.step("Assertion passed: Actual value matches expected value");
        } catch (AssertionError e) {
            Allure.step("Assertion failed: Actual value does not match expected value: ");
            Allure.attachment("Expected Value", String.valueOf(expectedResult));
            Allure.attachment("Actual Value", String.valueOf(actualResult));
            throw e;
        }
    }

    public static void assertApiElementContainsText(String actualResult, String... expectedResult) {
        try {
            assertThat(actualResult)
                    .containsAnyOf(expectedResult)
                    .as("Validate element contains inner text" + actualResult);

            Allure.step("Assertion passed: Actual value matches expected value");
        } catch (AssertionError e) {
            Allure.step("Assertion failed: Actual value does not match expected value: ");
            Allure.attachment("Expected Value", String.valueOf(expectedResult));
            Allure.attachment("Actual Value", String.valueOf(actualResult));
            throw e;
        }
    }


    public static void assertApiElementHasMatches(String element, String matchesCondition) {
        try {
            assertThat(element)
                    .matches(matchesCondition)
                    .as("Validate element regex matches " + matchesCondition);

            Allure.step("Assertion passed: Actual value matches expected value");
        } catch (AssertionError e) {
            Allure.step("Assertion failed: Actual value does not match expected value: ");
            Allure.attachment("Actual Value", String.valueOf(element));
            Allure.attachment("Regex Value", String.valueOf(matchesCondition));
            throw e;
        }
    }


    public static void assertApiResponseElementIsNotEqual(long actualResult, long expectedResult) {
        try {
            assertThat(actualResult)
                    .as("Validate element value" + actualResult)
                    .isNotEqualTo(expectedResult)
                    .isNotNull();

            Allure.step("Assertion passed: Actual value matches expected value");
        } catch (AssertionError e) {
            Allure.step("Assertion failed: Actual value does not match expected value: ");
            Allure.attachment("Expected Value", String.valueOf(expectedResult));
            Allure.attachment("Actual Value", String.valueOf(actualResult));
            throw e;
        }
    }

    public static void assertApiResponseElementIsNotEmptyOrNull(String actualResult) {
        try {
            assertThat(actualResult)
                    .as("Validate element value" + actualResult)
                    .isNotEmpty()
                    .isNotNull();

            Allure.step("Assertion passed: Actual value matches expected value");
        } catch (AssertionError e) {
            Allure.step("Assertion failed: Actual value does not match expected value: ");
            Allure.attachment("Actual Value", String.valueOf(actualResult));
            throw e;
        }
    }

}
