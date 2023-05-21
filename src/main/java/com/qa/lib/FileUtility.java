package com.qa.lib;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;

public class FileUtility {

	
	public static void upload(WebDriver driver ,String by, String filePath) {
		//@checkpoint
		if(by == "" || filePath == null || filePath == "") {
			ThrowError.error();
		}
		try {
			getElement.byXpath(driver, by).sendKeys(filePath);
			System.out.println("File uploaded successfully!!!!");
		} catch (Exception e) {
			System.out.println("File upload lag gayeeeeeeeeeeee reeeeeeeee ::::"+e);
		}
	}
	
	//Takes three @param (driver, locator, file name)
	public static void downloader(WebDriver driver, String by, String fileName) {
		String userName = System.getProperty("user.name");
		String currDir = System.getProperty("user.dir");
		File fileDes = new File(currDir+"/data");
		try {
			getElement.byXpath(driver, by).click();
			Thread.sleep(10000);
			System.out.println("Finish Downloading!!!!!");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		File srcFile = new File("C://Users/"+userName+"/Downloads/"+fileName+".xlsx");
		try {
			srcFile.renameTo(new File(currDir+"/data/"+fileName+".xlsx"));
			Thread.sleep(5000);
			System.out.println("File Copied successfully!!!!!");
		} catch (Exception e) {
			System.out.println(e);
		}
		
		//trying to delete the file if exist
		try {
			if(srcFile.exists()) {
				System.out.println("File exists trying to delete it now!!!!!!");
				srcFile.delete();
				System.out.println("File Deleted successfully");
			}
		} catch (Exception e) {
			System.out.println("File does not exist!!!!!!!");
		}
	}

	public boolean isFileExist(String filePath) {
		File file = new File(GetCurrDir.currDir()+filePath);
		return file.exists() && file.isFile();
	}
}
