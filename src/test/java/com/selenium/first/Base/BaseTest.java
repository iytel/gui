package com.selenium.first.Base;

import com.selenium.first.Page.CustomListener;
import com.selenium.first.Page.TestPage;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Proxy;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class BaseTest {

    public WebDriver driver;
    public static String browserName = "chrome";
    public static String baseUrl = "http://www.trendyol.com/";
    public static BrowserMobProxyServer proxy;


    @BeforeMethod
    public void setUp() throws Exception {

//        proxy = new BrowserMobProxyServer();
//       proxy.start(); // har dosyası için
//        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
//        try {
//            String hostIp = Inet4Address.getLocalHost().getHostAddress();
//            seleniumProxy.setHttpProxy(hostIp + ":" + proxy.getPort());
//            seleniumProxy.setSslProxy(hostIp + ":" + proxy.getPort());
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }


        //Browser ayarları
        DesiredCapabilities capabilities = null;
        switch (browserName) {
            case "chrome":
                capabilities = DesiredCapabilities.chrome();
                break;
            case "firefox":
                capabilities = DesiredCapabilities.firefox();
                break;
            case "safari":
                capabilities = DesiredCapabilities.safari();
                break;
            case "internetexplorer":
                capabilities = DesiredCapabilities.internetExplorer();
                break;
            case "edge":
                capabilities = DesiredCapabilities.edge();
                break;
        }
        //capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);  // har dosyası için
        capabilities.setCapability("key", System.getProperty("key"));

        if (StringUtils.isEmpty(System.getProperty("key"))) {
            String os = System.getProperty("os.name").toLowerCase();

            switch (browserName) {
                case "chrome":
                    if (os.contains("mac")) {
                        ChromeOptions op = new ChromeOptions();
                        System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver");
                        op.addArguments("--disable-notifications");
                       // op.merge(capabilities);
                        driver = new ChromeDriver(capabilities);
                    } else {
                        ChromeOptions op = new ChromeOptions();
                        op.addArguments("--disable-notifications");
                      //  op.merge(capabilities);
                        System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver.exe");
                        driver = new ChromeDriver(op);
                    }
                    break;
                case "firefox":
                    if (os.contains("mac")) {
                        System.setProperty("webdriver.gecko.driver", "src/test/resources/driver/geckodriver");
                    } else {
                        System.setProperty("webdriver.gecko.driver", "src/test/resources/driver/geckodriver.exe");
                    }
                    break;
                case "internetexplorer":
                    capabilities.setCapability(InternetExplorerDriver.IE_SWITCHES,"");
                    System.setProperty("webdriver.ie.driver", "src/test/resources/driver/IEDriverServer.exe");
                    driver = new InternetExplorerDriver();
                    break;
                case "edge":
                    System.setProperty("webdriver.edge.driver","src/test/resources/driver/MicrosoftWebDriver.exe");
                    driver = new EdgeDriver(capabilities);
                    break;
            }



        }

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        System.out.println(baseUrl);
        driver.get(baseUrl);

        // Yükleme sürelerini ölçmek için kullanılmıştır
//        try {
//            proxy.newHar("trendyol");
//
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            Har har = proxy.getHar();
//
//            File harFile = new File("trendyol.har");
//            har.writeTo(harFile);
//
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
//        proxy.stop();

    }


    @AfterMethod
    public void tearDown() {

        driver.quit();
//        proxy.stop();
    }


}