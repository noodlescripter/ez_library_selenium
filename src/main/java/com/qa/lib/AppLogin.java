package com.qa.lib;

import org.openqa.selenium.WebDriver;

public class AppLogin {

    public static void loginInAdmin(WebDriver driver, String userName, String passWord) {
        if (userName == "" || passWord == "") {
            throw new Error("Username and pass is not provided");
        }

        getElement.byId(driver, "Email").clear();
        getElement.byId(driver, "Email").sendKeys(userName);
        getElement.byXpath(driver, "//*[@id='Password']").clear();
        getElement.byXpath(driver, "//*[@id='Password']").sendKeys(passWord);
        getElement.byXpath(driver, "//*[@type='submit']").click();
        getElement.byText(driver, "Logout");
    }

    public static void logout(WebDriver driver) {
        if (driver == null) {
            throw new Error("Driver is null!!!!");
        }

        driver.switchTo().defaultContent();
        System.out.println("Loging out of the application now reeeeeeeeeeeeeeeeee");
        getElement.byText(driver, "Logout").click();
        getElement.byXpath(driver, "//*[@type='submit']");
    }

}
