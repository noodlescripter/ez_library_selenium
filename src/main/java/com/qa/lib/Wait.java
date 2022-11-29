package com.qa.lib;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wait {

    //Clearing the element
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

    
}
