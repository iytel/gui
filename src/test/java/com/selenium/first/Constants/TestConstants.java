package com.selenium.first.Constants;

import com.selenium.first.Base.BasePageUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestConstants extends BasePageUtil {

    public TestConstants (WebDriver driver){
        super(driver);
    }

    public By POPUPHEAD = By.id("homepage-popup-header");
    public By CLOSEBUTTON = By.className("homepage-popup-gender");
    public By CUSTOMERPANEL = By.xpath("//div[@id='account-navigation-container']/div/div/div/p");
    public By SIGNUP = By.xpath("//div[@id='login-register']/div[2]/div/button[2]");
    public By REGISTERMAIL = By.id("register-email");
    public By LOGINMAIL = By.id("login-email");
    public By REGISTERPASSWORD = By.id("register-password-input");
    public By LOGINPASSWORD = By.id("login-password-input");
    public By REGISTERCHECKBOX = By.cssSelector("div.q-checkbox-wrapper > label.q-label");
    public By REGISTERSUBMIT = By.xpath("//button[@type='submit']");
    public By LOGINSUBMIT = By.xpath("//div[@id='login-register']/div[3]/div/form/button/span");
    public By REGISTERPOPUP = By.cssSelector("input[name=\"code\"]");
    public By MYACCOUNT = By.cssSelector("p.link-text");
    public By HEADER = By.id("browsing-gw-homepage");
    public By MYORDERS = By.id("orders-container");
    public By FORGOTPASS = By.cssSelector("a > span");
    public By FORGOTMAIL = By.cssSelector("input[name=\"email\"]");
    public By FORGOTSUBMIT = By.xpath("//button[@type='submit']");
    public By FAILBUTTON = By.id("fail");
    public By TAG = By.tagName("a");


}
