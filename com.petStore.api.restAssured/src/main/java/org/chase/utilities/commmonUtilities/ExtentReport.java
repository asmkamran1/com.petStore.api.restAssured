package org.chase.utilities.commmonUtilities;

import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

public class ExtentReport {
	private ExtentReport() {
	}

	public static ExtentReports extent = new ExtentReports();

	private static String reportName = getReportName();
	private static String reportPath = setReportPath();

	public static synchronized void initReports() {
		ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);

		System.out.println("REPORT PATH IS == " + reportPath);
		spark.viewConfigurer().viewOrder().as(new ViewName[] { ViewName.DASHBOARD, ViewName.TEST, ViewName.EXCEPTION,
				ViewName.LOG, ViewName.AUTHOR, ViewName.CATEGORY, ViewName.DEVICE }).apply();

		extent.attachReporter(spark);
		ExtentSparkReporterConfig configAll = spark.config();
		configAll.setEncoding("utf-8");
		configAll.setTheme(Theme.DARK);
		configAll.setDocumentTitle(reportName + " - ALL");
		configAll.setReportName(reportName + " - ALL");
		configAll.setTimeStampFormat("dd-MM-yyyy hh:mm:ss a");

		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("Organization", "O'Reilly");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
	}

	public static void compressReport() {

	}

	synchronized public static void createTest(String testCaseName, String parameters) {
		ExtentManager.setExtentTest(
				extent.createTest(Constants.ICON_LAPTOP + " : " + testCaseName + " (" + parameters + ")"));
	}

	synchronized public static void addAuthors(String[] authors) {
		for (String author : authors) {
			ExtentManager.getExtentTest().assignAuthor(author.toString());
		}
	}

	synchronized public static void addDevice() {
		ExtentManager.getExtentTest().assignDevice(System.getProperty("os.name"));
	}

	public static String getReportName() {
		String tempName = Utilities.getPropertyValueByKey("reportName");
		if (tempName == "NOT_FOUND" || tempName == "" || tempName == null) {
			tempName = "API Test Report";
		}
		return tempName;
	}

	public static String getReportFilename() {
		Date date = new Date();
		String reportFileName = reportName.replace(" ", "_") + "_" + date.toString().replace(":", "_").replace(" ", "_")
				+ ".html";
		return reportFileName;
	}

	private static String setReportPath() {
		String reportPath = "target/reports/ExtentReports/" + getReportFilename();
		return reportPath;
	}

	public static String getReportPath() {
		return reportPath;
	}
}