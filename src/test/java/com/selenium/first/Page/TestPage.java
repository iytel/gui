package com.selenium.first.Page;

import com.opencsv.CSVWriter;
import com.selenium.first.Base.ReadProperties;
import com.selenium.first.Constants.TestConstants;
import org.junit.Assert;
import org.openqa.selenium.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import org.apache.commons.io.FileUtils;

public class TestPage extends TestConstants {

    public int responseCode;
    public String email;
    public String password;

    public TestPage(WebDriver driver) {
        super(driver);
    }

    public void findResponseCodeOfAllLinks() throws IOException {

        closeManOrWomenPopup();

        CSVWriter writer = new CSVWriter(new FileWriter(ReadProperties.getCSVPath()));

        List<WebElement> links = driver.findElements(TAG);

        String href;

        for(WebElement link : links) {

            href = link.getAttribute("href");
            responseCode = new HttpResponseCode().httpResponseCodeViaGet(href);

            System.out.println(href + " gave a response code of " + responseCode);

            String set[] = {href, String.valueOf(responseCode)};

            writer.writeNext(set);

            writer.flush();
        }


    }

    public TestPage goToSignPage(){

        closeManOrWomenPopup();
        clickElement(CUSTOMERPANEL);

        return this;
    }

    public TestPage signUp(){

        String mail = randomEmail();
        String password = randomPassword();

        clickElement(SIGNUP);
        setKeys(REGISTERMAIL,mail);
        setKeys(REGISTERPASSWORD,password);
        clickElement(REGISTERCHECKBOX);
        clickElement(REGISTERSUBMIT);

        return this;
    }

    public TestPage checkSignUp(){

        Assert.assertTrue("Onay mesajı popup ekranı gelmedi", driver.findElement(REGISTERPOPUP).isDisplayed());

        return this;
    }

    public TestPage signIn() {

        setKeys(LOGINMAIL,ReadProperties.getCustomerMail());
        setKeys(LOGINPASSWORD,ReadProperties.getCustomerPassword());
        clickElement(LOGINSUBMIT);
        waitForElement(driver,10,HEADER);
        clickElement(MYACCOUNT);

        return this;
    }

    public TestPage checkSignIn() {

       Assert.assertTrue("Başarılı bir şekilde giriş yapılamadı", driver.findElement(MYORDERS).isDisplayed());

        return this;
    }

    public TestPage resetPassword() throws Exception {

        clickElement(FORGOTPASS);
        setKeys(FORGOTMAIL,ReadProperties.getCustomerMail());
        clickElement(FORGOTSUBMIT);
        if(!isExist(1,FAILBUTTON)){
            takeSnapShot(driver);
            Assert.assertTrue("Test başarısız",driver.findElement(FAILBUTTON).isDisplayed());
        }

        return this;
    }

    public String randomEmail() {
        String alphaNum = ReadProperties.getRandomMail();
        StringBuilder str = new StringBuilder();
        Random rnd = new Random();
        while (str.length() < 5) {
            int index = (int) (rnd.nextFloat() * alphaNum.length());
            str.append(alphaNum.charAt(index));
        }
        email = str.toString() + "@" + str.toString() + ".com";
        return email;
    }


    public String randomPassword() {
        String num = ReadProperties.getRandomPassword();
        StringBuilder str = new StringBuilder();
        Random rnd = new Random();
        while (str.length() < 9) {
            int index = (int) (rnd.nextFloat() * num.length());
            str.append(num.charAt(index));
        }
        password = str.toString();
        return password;
    }

    public void closeManOrWomenPopup(){
        if(isExist(5,POPUPHEAD)){
            clickElement(CLOSEBUTTON);
        }
    }



}
