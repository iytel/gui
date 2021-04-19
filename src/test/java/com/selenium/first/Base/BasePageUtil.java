package com.selenium.first.Base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

public class BasePageUtil {

    protected WebDriver driver;

    public BasePageUtil(WebDriver driver) {
        this.driver = driver;
    }


    protected WebElement setKeys(By by, String value ){
        WebElement element = driver.findElement(by);
        element.sendKeys(value);
        return element;
    }

    protected WebElement clickElement(By by){
        waitForElement(driver,5,by);
        WebElement element = driver.findElement(by);
        element.click();
        return element;
    }
    public void waitForElement(WebDriver driver, int seconds, By elementBy) {
        WebDriverWait wait = new WebDriverWait(driver, seconds, 1000);
        //wait.until(ExpectedConditions.presenceOfElementLocated(elementBy));
    }

    public boolean isExist(int seconds, By by){
        try{
            waitForElement(driver, seconds, by);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public static void takeSnapShot(WebDriver webdriver) throws Exception{

        TakesScreenshot scrShot =((TakesScreenshot)webdriver);
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile=new File(ReadProperties.getScreenShotPath());
        FileUtils.copyFile(SrcFile, DestFile);

    }

}
