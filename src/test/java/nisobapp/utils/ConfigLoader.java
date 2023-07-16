package nisobapp.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {

    private static final String ERR_MSG = " is not specified in the API config.properties file";
    private static final String API_PROP_FILE = System.getProperty("API_CONF_TYPE");
    private static ConfigLoader configLoader;
    private Properties properties;

    private ConfigLoader() {
        properties = PropertyUtils.propertyLoader("src/test/resources/" + API_PROP_FILE);
    }

    public static ConfigLoader getSingletonInstance() {
        if (configLoader == null) {
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getClientId() {
        String prop = properties.getProperty("client_id");
        if (prop != null) {
            return prop;
        } else {
            throw new RuntimeException("property << client_id >>" + ERR_MSG);
        }
    }

    public String getClientSecret() {
        String prop = properties.getProperty("client_secret");
        if (prop != null) {
            return prop;
        } else {
            throw new RuntimeException("property << client_secret >>" + ERR_MSG);
        }
    }

    public String getGrantType() {
        String prop = properties.getProperty("grant_type");
        if (prop != null) {
            return prop;
        } else {
            throw new RuntimeException("property << grant_type >>" + ERR_MSG);
        }
    }


    public String getUserName() {
        String prop = properties.getProperty("username");
        if (prop != null) {
            return prop;
        } else {
            throw new RuntimeException("property << username >>" + ERR_MSG);
        }
    }

    public String getGetUserPass() {
        String prop = properties.getProperty("password");
        if (prop != null) {
            return prop;
        } else {
            throw new RuntimeException("property << password >>" + ERR_MSG);
        }
    }

    public String getApplicationNumber() {
        String prop = properties.getProperty("applicationNumber");
        if (prop != null) {
            return prop;
        } else {
            throw new RuntimeException("property << applicationNumber >>" + ERR_MSG);
        }
    }

    public void setApplicationNumber(String value) {
        properties.setProperty("applicationNumber", value);
        try {
            properties.store(new FileOutputStream("src/test/resources/" + API_PROP_FILE), null);
        } catch (IOException e) {
            throw new RuntimeException(">>>>>>>>>>>>>>> " + e);
        }
    }


    public String getSalesforceApplicationId() {
        String prop = properties.getProperty("salesforceApplicationId");
        if (prop != null) {
            return prop;
        } else {
            throw new RuntimeException("property << salesforceApplicationId >>" + ERR_MSG);
        }
    }

    public void setSalesforceApplicationId(String value) {
        properties.setProperty("salesforceApplicationId", value);
        try {
            properties.store(new FileOutputStream("src/test/resources/" + API_PROP_FILE), null);
        } catch (IOException e) {
            throw new RuntimeException(">>>>>>>>>>>>>>> " + e);
        }
    }
}
