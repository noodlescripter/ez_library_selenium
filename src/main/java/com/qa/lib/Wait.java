package com.qa.lib;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Wait {
    
    private WebDriver driver = null;

    public Wait(WebDriver driver) {
        this.driver = driver;
    }

    //Clearing the element
    /*
    * Note: this function is not being used in the latest version of this framework, if needed please create a constructor
    * */
    public static WebElement waitForEle(WebDriver driver, String element) {
        int dTime = 10000;
        WebElement sElement = null;
        WebDriverWait wait = new WebDriverWait(driver, dTime);
        if(element.startsWith("//")){
            sElement = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(element))));
        } 
        if(element.startsWith("[") || element.contains(".") || element.contains(">")){
            sElement = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(element))));
        }
        //validation
        if(!sElement.isDisplayed() || element == null){
            throw new Error("Element is not displayed ::: or not initilized!!");
        }
        return sElement;
    }

    public static MobileElement waitForMobileEle(AndroidDriver mDriver, String element) {
        int dTime = 10000;
        WebDriverWait wait = new WebDriverWait(mDriver, dTime);
        MobileElement sElement = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(element)));
        return sElement;
    }


    public void hardWait(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
