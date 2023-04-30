package com.qa.config;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.qa.lib.GetCurrDir;
import com.qa.lib.GetProp;
import com.qa.lib.Wait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserConfig {

    private static String DEVICE_NAME = "Device_1";
    private static String UDID_NUMBER = "emulator-5554";
    private static String PLATFORM_VERSION = "13";
    private static String PLATFORM_NAME = "Android";
    private static String SERVER_URL = "http://localhost:4723/wd/hub";
    private static String APK = GetCurrDir.currDir() + "data/apk/MyBank_1.apk";

    private static final String DOCKER_URL = "http://localhost:4444/wd/hub";
    private static String msg = "Something went wrong";

    public static WebDriver getBrowser_new(WebDriver driver, String headless, String browserName, String url) {
        if (browserName == null || browserName == "" || url == null || url == "") {
            throw new Error("Something went wrong please check the param!!!!!");
        }

        try {
            if (browserName.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                ChromeOptions op = new ChromeOptions();
                if (headless.equals("false")) {
                    op.addArguments("--window-size=400,800");
                    driver = new ChromeDriver(op);
                }
                if (headless.equals("true")) {
                    op.addArguments("--no-sandbox");
                    op.addArguments("--headless");
                    op.addArguments("disable-gpu");
                    driver = new ChromeDriver(op);
                }

            }

            if (browserName.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions op = new FirefoxOptions();
                if (headless.equals("false")) {
                    op.addArguments("--window-size=1920,1080");
                    driver = new FirefoxDriver(op);
                }
                if (headless.equals("true")) {
                    op.addArguments("--no-sandbox");
                    op.addArguments("--headless");
                    op.addArguments("disable-gpu");
                    driver = new FirefoxDriver(op);
                }

            }

            if (browserName.equalsIgnoreCase("edge")) {
                WebDriverManager.edgedriver().setup();
                if (headless.equals("false")) {
                    driver = new EdgeDriver();
                }
                if (headless.equals("true")) {
                    System.out.println("Headless Edge Driver Not Supported By this Framework yet!!!!!!!!!");
                    driver = new EdgeDriver();
                }
                driver.manage().window().maximize();
                System.out.println("Will run in full Screen!!!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong, giving it another chance :: Chrome ONLY");
            try {
                WebDriverManager.chromedriver().setup();
                ChromeOptions op = new ChromeOptions();
                op.addArguments("--window-size=1920,1080");
                driver = new ChromeDriver(op);
            } catch (Exception EA) {
                System.out.println("That did not work!!!!!!!!! Throwing big asss errrr!!!!!!!");
                EA.getMessage();
            }
        }

        // @param url should be provied thru xml or property file
        driver.get(url);
        // @checkpoint for page to be fully loaded
        // @return driver
        return driver;
    }


    public static AndroidDriver getAppium_APK(AndroidDriver mDriver, String osName) {
        URL url = null;
        DesiredCapabilities cap = null;
        if (osName.toUpperCase().contains("WINDOWS") || osName.toUpperCase().contains("MAC") || osName.toUpperCase().contains("LINUX")) {
            try {
                System.out.println("HOST IS " + osName);
                cap = new DesiredCapabilities();
                cap.setCapability("deviceName", DEVICE_NAME);
                cap.setCapability("udid", UDID_NUMBER);
                cap.setCapability("platformName", PLATFORM_NAME);
                cap.setCapability("platformVersion", PLATFORM_VERSION);
                cap.setCapability("app", APK);
                url = new URL(SERVER_URL);
                mDriver = new AndroidDriver<MobileElement>(url, cap);
                System.out.println("APPLICATION JUST STARTED!!!");
            } catch (Exception e) {
                System.out.println("FAILED TO START THE APPLICATION!!!!");
            }
        }
        return mDriver;
    }

    //get dockerHUB, selenium chrome image running in docker
    public static void startBrowserInDockerHUB(WebDriver driver) {
        DesiredCapabilities cap = null;
        ChromeOptions CHROME_OPTIONS = null;
        try {
            CHROME_OPTIONS = new ChromeOptions().addArguments("--window-size=500,650");
            cap = DesiredCapabilities.chrome();
            driver = new RemoteWebDriver(new URL(DOCKER_URL), cap);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }


}
