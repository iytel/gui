package com.selenium.first;

import com.opencsv.CSVWriter;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public class TestPage extends TestConstants{

    public TestPage (WebDriver driver){
        super(driver);
    }

    private HttpURLConnection huc = null;
    private String url = null;
    public int responseCode;
    public String email;
    public String password;


    public TestPage collectLinks(String baseUrl) throws IOException {

        if(isExist(5,POPUPHEAD)){
            clickElement(CLOSEBUTTON);
        }

        List<WebElement> links = driver.findElements(By.cssSelector("a"));
        Iterator<WebElement> it = links.iterator();
        //CSVWriter writer = new CSVWriter(new FileWriter("resources//data.csv"));

        while(it.hasNext()) {

            url = it.next().getAttribute("href");

           // System.out.println(url);

            if (url == null || url.isEmpty()) {
                System.out.println("URL is either not configured for anchor tag or it is empty");
            }

//            if (!url.startsWith(baseUrl)) {
//                System.out.println("URL belongs to another domain, skipping it.");
//            }

            try {
                huc = (HttpURLConnection) (new URL(url).openConnection());

                huc.setRequestMethod("HEAD");

                huc.connect();

                responseCode = huc.getResponseCode();

                String set[] = {url, String.valueOf(responseCode)};

                //writer.writeNext(set);

                if (responseCode ==200) {
                    System.out.println(url + " is a valid link" +responseCode);
                } else {
                    //System.out.println(url + " is a valid link" + responseCode);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

            return this;
    }

    public void checkBrokenLinks() throws IOException {
        if(isExist(5,POPUPHEAD)){
            clickElement(CLOSEBUTTON);
        }

        CSVWriter writer = new CSVWriter(new FileWriter("/Users/cagataya/IdeaProjects/firstSampleSelenium/src/test/resources/data.csv"));
        //Get all the links on the page
        //List<WebElement> links = driver.findElements(By.cssSelector("a"));
        List<WebElement> links = driver.findElements(By.cssSelector("img"));

        String href;

        for(WebElement link : links) {
           // href = link.getAttribute("src");

            href = link.getAttribute("src");
            responseCode = new HttpResponseCode().httpResponseCodeViaGet(href);

            if(200 != responseCode) {
                System.out.println(href + " gave a response code of " + responseCode);
            }else{
                System.out.println(href + " gave a response code of " + responseCode);
            }

            String set[] = {href, String.valueOf(responseCode)};

            writer.writeNext(set);

            writer.flush();
        }


    }


    public TestPage fillInTheBlanks(String username, String mail, String pass ){


        waitForElement(driver,20,SIGNIN);
        clickElement(SIGNIN);
        waitForElement(driver,20,USERNAME);
        setKeys(USERNAME, username);
        setKeys(EMAIL, mail);
        setKeys(PASSWORD, pass);
        setKeys(PASSAGAIN,pass);
        clickElement(SUBMIT);

        return this;
    }
    public TestPage control(){

        Assert.assertTrue("Kayıt yapılamadı", driver.getPageSource().contains("Üyeliğiniz oluşturulmuş ve e-posta adresinize doğrulama e-postası gönderilmiştir. Üyeliğinizi doğruladıktan sonra giriş yapabilirsiniz."));

        return this;
    }
    public TestPage goToSignPage(){

        if(isExist(5,POPUPHEAD)){
            clickElement(CLOSEBUTTON);
        }
        clickElement(CUSTOMERPANEL);
        clickElement(SIGNUP);
        return this;
    }

    public String randomEmail() {
        String alphaNum = "abcdefghijklmnopqrstuvwxyz";
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
        String num = "1234567890";
        StringBuilder str = new StringBuilder();
        Random rnd = new Random();
        while (str.length() < 8) {
            int index = (int) (rnd.nextFloat() * num.length());
            str.append(num.charAt(index));
        }
        password = str.toString();
        return password;
    }


}
