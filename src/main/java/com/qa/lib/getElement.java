package com.qa.lib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class getElement {
	
	//This class is responsible for clearing the element @wait till visible
    
    public static WebElement byXpath(WebDriver driver, String by) {
        return Wait.waitForEle(driver, by);
    }

    public static WebElement byText(WebDriver driver, String by) {
        return Wait.waitForEle(driver, "//*[text()='"+by+"']");
    }

    public static WebElement byId(WebDriver driver, String by) {
        return Wait.waitForEle(driver, "[id='"+by+"']");
    }
    
    public static WebElement byName(WebDriver driver, String by) {
		return Wait.waitForEle(driver, "[name='"+by+"']");
	}
    
    public static WebElement byCss(WebDriver driver, String by) {
		return Wait.waitForEle(driver, by); //@this
	}
    
}