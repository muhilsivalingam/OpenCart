package utilities;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	public ExtentSparkReporter sparkReporter;// UI of the Report
	public ExtentReports extent;// popular common info on the report
	public ExtentTest test;
	 public String repName; // creating testcase entries in the report and update status of test methods

	public void onStart(ITestContext testContext) {

	    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	    repName = "Test-Report-" + timeStamp + ".html";

	    String reportPath = System.getProperty("user.dir") + "/reports/" + repName;

	    sparkReporter = new ExtentSparkReporter(reportPath);
	    sparkReporter.config().setDocumentTitle("Automation Report");
	    sparkReporter.config().setReportName("Functional Testing");
	    sparkReporter.config().setTheme(Theme.DARK);

	    extent = new ExtentReports();
	    extent.attachReporter(sparkReporter);

	    // Static project info
	    extent.setSystemInfo("Application", "opencart");
	    extent.setSystemInfo("Module", "Admin");
	    extent.setSystemInfo("Sub Module", "Customers");
	    extent.setSystemInfo("User Name", System.getProperty("user.name"));
	    extent.setSystemInfo("Environment", "QA");

	    // OS from XML parameter
	    String os = testContext.getCurrentXmlTest().getParameter("os");
	    extent.setSystemInfo("Operating System", os);

	    // Browser from XML parameter
	    String browser = testContext.getCurrentXmlTest().getParameter("browser");
	    extent.setSystemInfo("Browser", browser);

	    // Included groups from XML (if any)
	    List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
	    if (!includedGroups.isEmpty()) {
	        extent.setSystemInfo("Groups", includedGroups.toString());
	    }
	}


	@Override
	public void onTestSuccess(ITestResult result) {

	    // Create test node with class name
	    test = extent.createTest(result.getTestClass().getName());

	    // Show TestNG groups as categories in report
	    test.assignCategory(result.getMethod().getGroups());

	    // Log status with method name
	    test.log(Status.PASS, result.getName() + " got successfully executed");
	}


	@Override
	public void onTestFailure(ITestResult result) {

	    // Create node and assign group categories
	    test = extent.createTest(result.getTestClass().getName());
	    test.assignCategory(result.getMethod().getGroups());

	    // Log failure info
	    test.log(Status.FAIL, result.getName() + " got failed");
	    test.log(Status.INFO, result.getThrowable().getMessage());

	    // Capture screenshot and add to report
	    try {
	        String imgPath = new BaseClass().captureScreen(result.getName());
	        test.addScreenCaptureFromPath(imgPath);
	    } catch (IOException e1) {
	        e1.printStackTrace();
	    }
	}
	public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, result.getName() + " got skipped");
        test.log(Status.INFO, result.getThrowable().getMessage());
    }

	public void onFinish(ITestContext testContext) {
	    extent.flush();
	    
	    String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
	    File extentReport = new File(pathOfExtentReport);
	    
	    try {
	        Desktop.getDesktop().browse(extentReport.toURI());  // Opens only 1 report
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

}
