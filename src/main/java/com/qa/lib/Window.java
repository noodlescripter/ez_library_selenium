package com.qa.lib;



import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Set;

public class Window {

	// @switch to window
	/*
	 * public static void switchTo(WebDriver driver) {
	 * 
	 * System.out.println("Switching to window reeeeeeeeeeee");
	 * 
	 * // @main window String mainWindow = driver.getWindowHandle();
	 * 
	 * Set<String> allWindow = driver.getWindowHandles();
	 * 
	 * while (allWindow.iterator().hasNext()) { String childWindow =
	 * allWindow.iterator().next(); if (!mainWindow.equalsIgnoreCase(childWindow)) {
	 * try { driver.switchTo().window(childWindow);
	 * System.out.println("Now in child window reeeeeee"+ childWindow); break; }
	 * catch (Exception e) {
	 * System.out.println("Window lag gayeeeee reeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
	 * } } }
	 * 
	 * }
	 */
	private WebDriver driver = null;
	public Window(WebDriver driver) {
		this.driver = driver;
	}

	//@window handle
	public void switchTo()  {
		String mainWindowHandle = driver.getWindowHandle();

		// Get all window handles
		Set<String> windowHandles = driver.getWindowHandles();


		try {
			// Switch to the new window if available
			for (String handle : windowHandles) {
				if (!handle.equals(mainWindowHandle)) {
					driver.switchTo().window(handle);
					return;
				}
			}
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public  void closeDialog() {
		if (driver.getWindowHandles().size() > 1) {
			driver.close();
		}
		new Wait(driver).hardWait(2000);
	}

	public  void switchBack(String mainWindow)  {
		driver.switchTo().window(mainWindow);
		new Wait(driver).hardWait(2000);
	}
	public  void switchToFrame(String frameName) {
		if(frameName == "" || frameName == null) {
			throw  new Error("Something went wrong could!!!! Please prodive valid frame name");
		}
		WebElement frameLoc = getElement.byXpath(driver, frameName);
		boolean flag = frameLoc.isDisplayed();
		if(!flag) {
			throw new Error("Frame is not located :::: or frame not found");
		}
		
		try {
			driver.switchTo().frame(frameLoc);
		} catch (Exception e) {
			System.out.println("No frame found!!!!!!");
		}
	}

	public void acceptAlert(boolean flag) {
		new Wait(driver).hardWait(2000);
		if(flag)
			driver.switchTo().alert().accept();
		else
			driver.switchTo().alert().dismiss();
	}
}

