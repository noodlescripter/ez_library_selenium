package com.qa.lib;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

	//@window handle
	public static void switchTo(WebDriver driver) {
		System.out.println("Switching to window reeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
		String mainWindow = driver.getWindowHandle();
		for (String windowHandle : driver.getWindowHandles()) {
			if (!mainWindow.contentEquals(windowHandle)) {
				try {
					driver.switchTo().window(windowHandle);
					break;
				} catch (Exception e) {
					System.out.println(e + "Window switching lag gayeeeeeeeeeeeeeeeeeee reeeeeeeeeeeeeee");
				}
			}
		}

	}
	
	public static void switchToFrame(WebDriver driver, String frameName) {
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
	
}

