package com.qa.config;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.qa.lib.Wait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserConfig {

    private static String msg = "Something went wrong";

    /* public static WebDriver getBrowser(WebDriver driver, String browserName) {
        //@checkpoint

        if (browserName == null || browserName == "") {
            throw new Error("Something went wrong please check the param!!!!!");
        }

        //@config
        try {
            if (browserName.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            } else if (browserName.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            } else if (browserName.equalsIgnoreCase("edge")) {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            } else {
                throw new Error(msg);
            }
        } catch (Exception e) {
            System.out.println("Something went wrong and trying to open default browser :: ++ Chrome");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }

        // navigating to the url
        driver.get("https://admin-demo.nopcommerce.com/login");

        // puting somevalidation
        Wait.waitForEle(driver, "//h1");

        // @return driver for future uses in the tc
        return driver;
    } */

    public static WebDriver getBrowser_new(WebDriver driver, String headless, String browserName, String url) {
        if (browserName == null || browserName == "" || url == null || url == "") {
            throw new Error("Something went wrong please check the param!!!!!");
        }

        try {
            if (browserName.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                if (headless.equals("false")) {
                    driver = new ChromeDriver();
                }
                if (headless.equals("true")) {
                    ChromeOptions op = new ChromeOptions();
                    op.addArguments("--no-sandbox");
                    op.addArguments("--headless");
                    op.addArguments("disable-gpu");
                    driver = new ChromeDriver(op);
                }

            }

            if (browserName.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                if (headless.equals("false")) {
                    driver = new FirefoxDriver();
                }
                if (headless.equals("true")) {
                    FirefoxOptions op = new FirefoxOptions();
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
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong, giving it another chance :: Chrome ONLY");
            try {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            } catch (Exception EA) {
                System.out.println("That did not work!!!!!!!!! Throwing big asss errrr!!!!!!!");
                EA.getMessage();
            }
        }

        // @param url should be provied thru xml or property file
        driver.get(url);

        // @checkpoint for page to be fully loaded
        Wait.waitForEle(driver, "//h1");

        // @return driver
        return driver;
    }

}
