package org.chase.utilities.commmonUtilities;

import com.aventstack.extentreports.ExtentTest;

public class ExtentManager {
	private ExtentManager() {
	}

	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	public static void setExtentTest(ExtentTest test) {
		extentTest.set(test);
	}

	public static ExtentTest getExtentTest() {
		if (extentTest.get() == null) {
			System.out.println("Adding test to prereport");
			setExtentTest(
					ExtentReport.extent.createTest(Constants.ICON_LAPTOP + " : FrameworkInitializationPreTest", ""));
		}
		return extentTest.get();
	}

	public static void unload() {
		extentTest.remove();
	}
}
