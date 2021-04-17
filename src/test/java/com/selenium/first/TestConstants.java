package com.selenium.first;


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

    public By SIGNIN = By.id("kayitol");

    public By USERNAME = By.id("uye_ad_soyad");
    public By EMAIL = By.id("uye_posta");
    public By PASSWORD = By.id("uye_parola");
    public By PASSAGAIN = By.id("uye_parola_tekrar");
    public By SUBMIT = By.id("a5d4");

}
