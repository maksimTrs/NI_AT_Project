package nisfapp.services;

import nisfapp.model.User;

import static nisfapp.utils.PropertyReader.getTestDataFromBundle;

public class UserCreator {
    public static final String USER_NAME = "USER_EMAIL";
    public static final String USER_PASSWORD = "USER_PASSWORD";
    public static final String USER_PASSWORD_WRONG = "USER_PASSWORD_WRONG";

    public static User withCredentialsFromPropertyFile(String userName, String userPass) {
        return User.builder()
                .username(userName)
                .password(userPass)
                .build();
    }

    public static User withWrongCredentialsFromPropertyFile(String userName, String userWrongPass) {
        return User.builder()
                .username(userName)
                .password(userWrongPass)
                .build();
    }
}
