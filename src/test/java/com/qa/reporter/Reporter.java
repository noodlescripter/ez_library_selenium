package com.qa.reporter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.Base.BaseClass;
import com.qa.lib.ScreenShot;


public class Reporter extends BaseClass implements ITestListener{
	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extentReports;
	public ExtentTest test;
	
	@Override		
    public void onFinish(ITestContext arg0) {					
		extentReports.flush();	
        		
    }		

    @Override		
    public void onStart(ITestContext arg0) {					
    	String report_folder = "Report/report_.html";
		htmlReporter = new ExtentHtmlReporter(report_folder);
		extentReports = new ExtentReports();
		extentReports.attachReporter(htmlReporter);
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.setAppendExisting(true);					
        		
    }		
	

    
    @Override		
    public void onTestFailure(ITestResult arg0) {					
    	test.fail(arg0.getThrowable() + " ::: FAILED!!!");   	
    	try {
			test.addScreenCaptureFromPath(ScreenShot.takeScreenShot(driver, arg0.getMethod().getMethodName()));
		} catch (IOException e) {
			System.out.println("Failed to Take the screenshot :::"+e.getMessage());
		}
    }
    
    @Override		
    public void onTestSkipped(ITestResult arg0) {					
    	test.pass(arg0.getMethod().getMethodName() + " ::: SKIPPED :) ");		
        		
    }		

    @Override		
    public void onTestStart(ITestResult arg0) {					
    	test = extentReports.createTest(arg0.getMethod().getMethodName());				
        		
    }		

    @Override		
    public void onTestSuccess(ITestResult arg0) {					
    	test.pass(arg0.getMethod().getMethodName() + " ::: PASSED :) ");			
        		
    }
}
