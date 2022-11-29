package com.qa.lib;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenShot {

	public static String getDateAndTime() {
		SimpleDateFormat date = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		Date currentDate = new Date();
		return date.format(currentDate);
	}

	public static String takeScreenShot(WebDriver driver, String fileName) {
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./Screenshot/"+fileName+".png");
		try {
			FileUtils.copyFile(SrcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return destFile.getAbsolutePath();
	}
}
