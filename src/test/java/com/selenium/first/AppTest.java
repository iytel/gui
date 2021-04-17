
package com.selenium.first;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.selenium.first.BaseTest;
import com.selenium.first.BasePageUtil;

import java.io.IOException;

public class AppTest extends BaseTest{

    @Test
    public void checkResponseCodesOfAllLinks() throws IOException {

        new TestPage(driver)
        .checkBrokenLinks();

    }

    @Test
    public void loginPageTest() {

        new TestPage(driver)
        .goToSignPage()
        .fillInTheBlanks("cagatay aktas","cagcag378988@gmail.com","123456asdfQ")
        .control();

    }

}