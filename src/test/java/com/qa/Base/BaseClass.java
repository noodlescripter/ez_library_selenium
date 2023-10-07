package com.qa.Base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import com.qa.lib.LIB;
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
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.config.BrowserConfig;
import com.qa.lib.GetProp;
import com.qa.lib.InvokeBatFile;
import com.qa.lib.OSUTIL;

import io.appium.java_client.android.AndroidDriver;

public class BaseClass {

	public static AndroidDriver mDriver;
	public static WebDriver driver;
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extentReports;
	public ExtentTest test;

	public GetLibrary lib = null;

	public String GET_HOST() {
		return OSUTIL.getOS();
	}

	@Parameters({
			"isMobile"
	})
	@BeforeClass
	public void setUpEnvWeb(boolean isMobile) throws IOException {
		if (!isMobile) {
			GetProp prop = new GetProp("config/env.properties");
			driver = BrowserConfig.getBrowser_new(driver, prop.getValue("isHeadless"), prop.getValue("browserNAME"));
			lib = new GetLibrary(driver);
		}
		if (isMobile) {
			//Just a simple flag since Appium does not like @BeforeClass!
			System.out.println("NOTHING TO DO IN THE BEFORE CLASS!!");
		}
	}

	@Parameters({
			"isMobile"
	})
	@BeforeMethod
	public void setEnv_Mobile(@Optional("false") boolean isMobile) {
		driver.get(new GetProp("config/env.properties").getValue("baseURL"));
		if (!isMobile) {
			System.out.println("");
		}
		if (isMobile) {
			mDriver = BrowserConfig.getAppium_APK(mDriver, GET_HOST());
		}
	}

	@AfterClass
	public void distoryEverything() throws IOException {
		if (driver == null) {
			mDriver.closeApp();
		}

		if (driver != null) {
			/*
			* In case you need to restore your data
			* */
			//driver.get("http://localhost:8001/restore-database");
			driver.quit();
		}

	}
}
