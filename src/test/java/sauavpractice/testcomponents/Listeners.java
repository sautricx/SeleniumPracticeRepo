package sauavpractice.testcomponents;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import sauravpractice.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{

	ExtentReports extentReport = ExtentReporterNG.getReportObject();
	ExtentTest test;
	//String filePath;
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestStart(result);
		test = extentReport.createTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSuccess(result);
		test.log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailure(result);
		test.fail(result.getThrowable());
		
		//When test is failed we will attach a screen shot here so we will write code here.
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")

					.get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
			String filePath = null;
			try {
				filePath = getScreenShot(result.getMethod().getMethodName(), driver);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		 
		test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
		extentReport.flush();
	}

}
