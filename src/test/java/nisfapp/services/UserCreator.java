package nisfapp.services;

import nisfapp.model.User;

import static nisfapp.utils.PropertyReader.getTestDataFromBundle;

public class UserCreator {
    private static final String USER_NAME = "USER_EMAIL";
    private static final String USER_PASSWORD = "USER_PASSWORD";
    private static final String USER_PASSWORD_WRONG = "USER_PASSWORD_WRONG";

    public static User withCredentialsFromPropertyFile() {
        return User.builder()
                .username(getTestDataFromBundle(USER_NAME))
                .password(getTestDataFromBundle(USER_PASSWORD))
                .build();
    }

    public static User withWrongCredentialsFromPropertyFile() {
        return User.builder()
                .username(getTestDataFromBundle(USER_NAME))
                .password(getTestDataFromBundle(USER_PASSWORD_WRONG))
                .build();
    }
}
