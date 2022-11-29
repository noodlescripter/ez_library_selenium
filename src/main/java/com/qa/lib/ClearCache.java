package com.qa.lib;

import org.openqa.selenium.WebDriver;

public class ClearCache {

	public static void clearCache(WebDriver driver, boolean flag) {

		getElement.byCss(driver, "div[id='navbarText']>ul>li:nth-child(5)").click();
		if (flag) {
			try {
				System.out.println("Trying to clear cache reeeeeeeeeeeeeeeeeeeeeeeee");
				getElement.byText(driver, "Clear cache").click();
				getElement.byText(driver,
						"For security purposes, the feature you have requested is not available on the demo site.");
			} catch (Exception e) {
				System.out.println("Shoot clear cache lagggggg gayeeeeeeeeeeeeee reeeeeeeeeeeeeeeeeeeeeeeeee");
			}
		}

		if (!flag) {
			System.out.println("did nothing!!!! Please continue!!!!!!");
		}

	}

}
