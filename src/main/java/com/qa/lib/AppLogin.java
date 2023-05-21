package com.qa.lib;

import org.openqa.selenium.WebDriver;

public class AppLogin {

    public static void loginInAdmin(WebDriver driver, String userName, String passWord) {

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
