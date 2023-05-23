package com.qa.lib;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class Wrapper {

    public static boolean WAIT_FOR_ELEMENT(WebDriver driver, By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 60);
            boolean flag = wait.until(ExpectedConditions.or(
                    ExpectedConditions.elementToBeClickable(locator),
                    ExpectedConditions.visibilityOfElementLocated(locator),
                    ExpectedConditions.presenceOfElementLocated(locator),
                    ExpectedConditions.invisibilityOfElementLocated(locator)
            ));
            return flag;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean IS_ELEMENT_CLICKABLE(WebDriver driver, By by) {
        boolean flag = false;
        WebElement ele = null;
        try {
            ele = new WebDriverWait(driver, 60).until(
                    ExpectedConditions.elementToBeClickable(by)
            );
        } catch (Exception e) {
            System.out.println("....");
        }

        if (ele.isEnabled() || ele.isDisplayed()) {
            flag = true;
        }

        return flag;
    }

    public static boolean WAIT_UNTIL_ELEMENT_IS_VISIBLE(WebDriver driver, By element) {
        boolean flag = false;
        WebElement ele = null;
        try {
            ele = new WebDriverWait(driver, 60).until(
                    ExpectedConditions.visibilityOfElementLocated(element)
            );
        } catch (Exception e) {
            System.out.println("....");
        }

        if (ele.isEnabled() || ele.isDisplayed()) {
            flag = true;
        }

        return flag;
    }
    public static boolean WAIT_UNTIL_ELEMENT_IS_PRESENT(WebDriver driver, By element) {
        boolean flag = false;
        WebElement ele = null;
        try {
            ele = new WebDriverWait(driver, 60).until(
                    ExpectedConditions.presenceOfElementLocated(element)
            );
        } catch (Exception e) {
            System.out.println("....");
        }

        if (ele.isEnabled() || ele.isDisplayed()) {
            flag = true;
        }

        return flag;
    }

    public static boolean WAIT_UNTIL_ELEMENT_IS_NOT_VISIBLE(WebDriver driver, By element) {
        boolean flag = false;
        try {
            flag = new WebDriverWait(driver, 60).until(
                    ExpectedConditions.invisibilityOf(driver.findElement(element))
            );
        } catch (Exception e) {
            System.out.println("....");
        }
        return flag;
    }

    public static boolean WAIT_FOR_ELEMENT_ELE(WebDriver driver, WebElement ele) {
        int dTime = 10000;
        boolean flag = false;
        flag = new WebDriverWait(driver, dTime).until(ExpectedConditions.visibilityOf(ele)).isDisplayed();
        return flag;
    }
}
