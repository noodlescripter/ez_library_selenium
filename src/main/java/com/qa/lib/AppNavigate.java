package com.qa.lib;

import org.openqa.selenium.WebDriver;

public class AppNavigate {
	
	
	private static void getMainMenu(WebDriver driver, String main) {
		 getElement.byXpath(driver, "//nav/ul[@role='menu']/li/a/p[contains(text(), '"+main+"')]").click();
	}

	public static void navigateTo(WebDriver driver, String main, String sub) {
		
		if(main == "" || sub == "") {
			throw new Error("Something went wrong please check!!!!");
		}
		
		getMainMenu(driver, main);
		boolean flag = getElement.byXpath(driver, "//nav/ul[@role='menu']/li/a/p[contains(text(), '"+main+"')]//ancestor::a//following-sibling::ul/li/a//p[contains(text(), '"+sub+"')]").isDisplayed();
		if(!flag) {
			ThrowError.error();
		}
		getElement.byXpath(driver, "//nav/ul[@role='menu']/li/a/p[contains(text(), '"+main+"')]//ancestor::a//following-sibling::ul/li/a//p[contains(text(), '"+sub+"')]").click();
		
	}
	
	
}
