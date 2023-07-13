package nisfapp.utils;

import java.util.ResourceBundle;

public class PropertyReader {

    private static String BUNDLE_TYPE = System.getProperty("BUNDLE_TYPE");

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_TYPE);

    public static String getTestDataFromBundle(String prop) {
        return resourceBundle.getString(prop);
    }
}
