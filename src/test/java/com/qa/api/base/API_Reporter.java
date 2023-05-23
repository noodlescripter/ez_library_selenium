package com.qa.api.base;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class API_Reporter implements ITestListener {

    private StringBuilder reportBuilder;
    private int totalTestCases;

    @Override
    public void onStart(ITestContext context) {
        reportBuilder = new StringBuilder();
        reportBuilder.append("<html><head><title>API Test Report</title></head><body>");
        reportBuilder.append("<h1>API Test Report</h1>");
        totalTestCases = context.getAllTestMethods().length;
        reportBuilder.append("<p>Total Test Cases: ").append(totalTestCases).append("</p><br/>");
    }

    @Override
    public void onTestStart(ITestResult result) {
        reportBuilder.append("<h2>").append(result.getMethod().getMethodName()).append("</h2>");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        reportBuilder.append("<p style=\"color:green;\">Test Result: PASSED</p><br/>");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        reportBuilder.append("<p style=\"color:red;\">Test Result: FAILED</p>");
        reportBuilder.append("<p>").append(result.getThrowable().getMessage()).append("</p><br/>");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        reportBuilder.append("<p style=\"color:orange;\">Test Result: SKIPPED</p><br/>");
    }

    @Override
    public void onFinish(ITestContext context) {
        reportBuilder.append("<p>Total Test Cases Executed: ").append(context.getAllTestMethods().length).append("</p>");
        reportBuilder.append("</body></html>");

        String reportFilePath = "Report/API_REPORT/api-test-report.html";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(reportFilePath))) {
            writer.write(reportBuilder.toString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Reporter.log("API test report generated. You can find it at: " + reportFilePath);
    }

    // Other listener methods (onTestFailedButWithinSuccessPercentage, onTestFailedWithTimeout) can be implemented if needed


}

