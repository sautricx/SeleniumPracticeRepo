package sauravpractice.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports  getReportObject()
	{
		String path = System.getProperty("user.dir") + "//reports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Extent report Practice");
		reporter.config().setDocumentTitle("Saurav Extent Report");
		
		ExtentReports extentReport = new ExtentReports();
		extentReport.attachReporter(reporter);
		extentReport.setSystemInfo("Tester", "Saurav Kumar");
		return extentReport;
		//extentReport.createTest("");
		
	}

}
