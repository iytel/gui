
package com.selenium.first.Test;
import com.selenium.first.Page.CustomListener;
import com.selenium.first.Page.TestPage;
import com.selenium.first.Base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;

import java.io.IOException;

public class AppTest extends BaseTest {


    @Test
    public void checkResponseCodesOfAllLinks() throws IOException {

        new TestPage(driver)
        .findResponseCodeOfAllLinks();

    }

    @Test
    public void registerTest() {

        new TestPage(driver)
                .goToSignPage()
                .signUp()
                .checkSignUp();

    }

    @Test
    public void loginTest() throws Exception {

        new TestPage(driver)
                .goToSignPage()
                .signIn()
                .checkSignIn();

    }

    @Test
    public void resetPasswordTest() throws Exception {

        new TestPage(driver)
                .goToSignPage()
                .resetPassword();

    }

}