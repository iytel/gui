package com.selenium.first.Base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {

    static Properties userProperties;
    static {
        userProperties = new Properties();
        try {
            InputStream propertiesStream = BaseTest.class.getClassLoader().getResourceAsStream("user.properties");
            userProperties.load(propertiesStream);
            propertiesStream.close();
            propertiesStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getCustomerMail() {
        String customerMail = userProperties.getProperty("CUSTOMER_MAIL");
        return customerMail;
    }

    public static String getCustomerPassword() {
        String customerPassword = userProperties.getProperty("CUSTOMER_PASSWORD");
        return customerPassword;
    }

    public static String getRandomMail() {
        String randomMail = userProperties.getProperty("RANDOMMAIL");
        return randomMail;
    }

    public static String getRandomPassword() {
        String randomPassword = userProperties.getProperty("RANDOMPASS");
        return randomPassword;
    }

    public static String getScreenShotPath() {
        String ScreenShotPath = userProperties.getProperty("SCREENSHOTPATH");
        return ScreenShotPath;
    }

    public static String getCSVPath() {
        String csvPath = userProperties.getProperty("SCREENSHOTPATH");
        return csvPath;
    }
}
