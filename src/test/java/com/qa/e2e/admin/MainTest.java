package com.qa.e2e.admin;

import org.testng.annotations.Test;

import com.qa.Base.BaseClass;
import com.qa.lib.AppLogin;
import com.qa.lib.AppNavigate;

public class MainTest extends BaseClass {

    @Test
    public void validate_user_login_with_valid_credentials() throws InterruptedException {

        AppLogin.loginInAdmin(driver, "admin@yourstore.com", "admin");
        // getElement.byXpath(driver, "//nav/ul[@role='menu']/li/a/p[contains(text(),
        // 'Catalog')]").click();
        // getElement.byXpath(driver, "//nav/ul[@role='menu']/li/a/p[contains(text(),
        // 'Catalog')]//ancestor::a//following-sibling::ul/li/a//p[contains(text(),
        // 'Products')]").click();
        AppNavigate.navigateTo(driver, "Catalog", "Products");
        AppLogin.logout(driver);
    }

    @Test
    public void validate_user_login_with_valid_credentials_2() throws InterruptedException {

        AppLogin.loginInAdmin(driver, "admin@yourstore.com", "admin");
        // getElement.byXpath(driver, "//nav/ul[@role='menu']/li/a/p[contains(text(),
        // 'Catalog')]").click();
        // getElement.byXpath(driver, "//nav/ul[@role='menu']/li/a/p[contains(text(),
        // 'Catalog')]//ancestor::a//following-sibling::ul/li/a//p[contains(text(),
        // 'Products')]").click();
        // AppNavigate.navigateTo(driver, "Catalog", "dajksjdksajd");
        AppLogin.logout(driver);
    }

    @Test
    public void validate_user_login_with_valid_credentials_3() throws InterruptedException {

        AppLogin.loginInAdmin(driver, "admin@yourstore.com", "admin");
        // getElement.byXpath(driver, "//nav/ul[@role='menu']/li/a/p[contains(text(),
        // 'Catalog')]").click();
        // getElement.byXpath(driver, "//nav/ul[@role='menu']/li/a/p[contains(text(),
        // 'Catalog')]//ancestor::a//following-sibling::ul/li/a//p[contains(text(),
        // 'Products')]").click();
        AppNavigate.navigateTo(driver, "Catalog", "dajksjdksajd");
        AppLogin.logout(driver);
    }
}
