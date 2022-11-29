package com.qa.Base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.config.BrowserConfig;
import com.qa.lib.GetProp;


public class BaseClass {

	public static WebDriver driver;
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extentReports;
	public ExtentTest test;

	/*
	 * @BeforeSuite public void setUpReport() { String report_folder =
	 * "Report/report_.html"; htmlReporter = new ExtentHtmlReporter(report_folder);
	 * extentReports = new ExtentReports();
	 * extentReports.attachReporter(htmlReporter);
	 * htmlReporter.config().setTheme(Theme.DARK);
	 * htmlReporter.setAppendExisting(true); }
	 */

	@BeforeClass
	public void setUpEnv() throws IOException {
		GetProp prop = new GetProp("config/env.properties");
		driver = BrowserConfig.getBrowser_new(driver, prop.getValue("isHeadless"), prop.getValue("browserNAME"), prop.getValue("baseURL"));
	}

//	@BeforeMethod(alwaysRun = true)
//	public void gettingTestCasesName(Method m) {
//		
//	}

	@AfterClass
	public void distoryEverything() throws IOException {

		/*
		 * if (result.getStatus() == ITestResult.FAILURE) {
		 * test.fail(result.getThrowable() + " ::: FAILED!!!");
		 * test.addScreenCaptureFromPath(ScreenShot.takeScreenShot(driver,
		 * "failedTCT")); }
		 * 
		 * if (result.getStatus() == ITestResult.SUCCESS) {
		 * test.pass(result.getMethod().getMethodName() + " ::: PASSED :) "); }
		 * 
		 * if (result.getStatus() == ITestResult.SKIP) {
		 * test.pass(result.getMethod().getMethodName() + " ::: SKIPPED :) "); }
		 */
		driver.quit();
	}

//	@AfterMethod(alwaysRun = true)
//	public void tear_down(ITestResult result) throws IOException {
//
//		if (result.getStatus() == ITestResult.FAILURE) {
//			test.fail(result.getThrowable() + " ::: FAILED!!!");
//			test.addScreenCaptureFromPath(ScreenShot.takeScreenShot(driver, "failedTCT"));
//		}
//
//		if (result.getStatus() == ITestResult.SUCCESS) {
//			test.pass(result.getMethod().getMethodName() + " ::: PASSED :) ");
//		}
//
//		if (result.getStatus() == ITestResult.SKIP) {
//			test.pass(result.getMethod().getMethodName() + " ::: SKIPPED :) ");
//		}
//
//	}

	/*
	 * @AfterSuite public void tearDown() { extentReports.flush(); }
	 */
	
	
	
	public String sss(String fileName) throws IOException {
		File srcFile = (File) ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File des = new File("Screenshot/"+fileName+ ".png");
		String abPath = des.getAbsolutePath();
		
		FileUtils.copyFile(srcFile, des);
		return abPath;
	}
	
}

