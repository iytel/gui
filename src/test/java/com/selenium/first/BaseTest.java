package com.selenium.first;

import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;

import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;


public class BaseTest {

    protected WebDriver driver;
    public static String browserName = "chrome";
    public static String baseUrl = "https://www.trendyol.com/";
    public static String browserVersion = "1";
    public static BrowserMobProxyServer proxy; // har dosyası için


    @Before
    public void setUp() throws Exception {
        // har dosyası için
     proxy = new BrowserMobProxyServer();
       proxy.start(); // har dosyası için
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
        try {
            String hostIp = Inet4Address.getLocalHost().getHostAddress();
            seleniumProxy.setHttpProxy(hostIp + ":" + proxy.getPort());
            seleniumProxy.setSslProxy(hostIp + ":" + proxy.getPort());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }


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
        //     capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);  // har dosyası için
        capabilities.setCapability("key", System.getProperty("key"));

        if (StringUtils.isEmpty(System.getProperty("key"))) {
            String os = System.getProperty("os.name").toLowerCase();

            switch (browserName) {
                case "chrome":
                    if (os.contains("mac")) {
                        System.setProperty("webdriver.chrome.driver", "/Users/cagataya/IdeaProjects/firstSampleSelenium/drivers/chromedriver");
                        driver = new ChromeDriver(capabilities);
                    } else {
                        ChromeOptions op = new ChromeOptions();
                        op.addArguments("--disable-notifications");
                        //     op.merge(capabilities);  // har dosyası için
                        System.setProperty("webdriver.chrome.driver", "/Users/cagataya/IdeaProjects/firstSampleSelenium/drivers/chromedriver");
                        driver = new ChromeDriver(op);
                        //  proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);  // har dosyası için
                    }
                    break;
                case "firefox":
                    if (os.contains("mac")) {
                        System.setProperty("webdriver.gecko.driver", "src/test/resources/driver/geckodriver");
                    } else {
                        System.setProperty("webdriver.gecko.driver", "src/test/resources/driver/geckodriver.exe");
                    }
                   // driver = new FirefoxDriver(capabilities);
                    break;
                case "safari":
                   // driver = new SafariDriver(capabilities);
                    break;
            }

            //		browserName = capabilities.getBrowserName().toLowerCase().replaceAll("\\s","");


        }

// yüklenme istatistikleri için kullanıldı
        try {
            proxy.newHar("trendyol");
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            System.out.println(baseUrl);
            driver.get(baseUrl);

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Har har = proxy.getHar();

            File harFile = new File("trendyol.har");
            har.writeTo(harFile);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        proxy.stop();
    }


//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//        driver.manage().window().maximize();
//        System.out.println(baseUrl);
//        driver.get(baseUrl);


    @After
    public void tearDown() throws Exception {
        driver.quit();
        proxy.stop();
    }
}