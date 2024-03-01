package org.chase.utilities.commmonUtilities;

import org.testng.Reporter;

import com.google.gson.JsonObject;

public class EnvironmentUtilities {
	public static String showReport = System.getenv("SHOW_REPORT") != null ? System.getenv("SHOW_REPORT") : "";
	public static String buildNumber = System.getenv("BUILD_NUMBER") != null ? System.getenv("BUILD_NUMBER") : "1";
	public static String jobName = System.getenv("JOB_NAME") != null ? System.getenv("JOB_NAME") : "";
	public static String suite = System.getenv("SUITE") != null ? System.getenv("SUITE") : "cxs";
	public static String app = Utilities.getPropertyValueByKey("app");
	public static JsonObject utilsUrls = new JsonObject();

	public static JsonObject getUrls() {
		JsonObject utilsEnvsJSON = Utilities.getJsonObject(Constants.UTILS_RESOURCES_PATH, "envUrls.json");
		JsonObject envsJSON = Utilities.getJsonObject("envUrls.json");
		String env = System.getenv("TEST_ENV");
		String baseURL = System.getenv("CUSTOM_BASE_URL");
		Reporter.log("APP = '" + app + "'", true);
		Reporter.log("SUITE = '" + suite + "'", true);
		Reporter.log("TEST_ENV = '" + env + "'", true);
		Reporter.log("CUSTOM_BASE_URL = '" + baseURL + "'", true);
		Reporter.log("SHOW_REPORT = '" + showReport + "'", true);
		env = env != null ? env : "test";
		JsonObject urlsJSON;
		// if the base url is set, get env based off that
		if (baseURL != null && (baseURL.isEmpty() == false)) {
			Reporter.log("CUSTOM_BASE_URL is set. changing default env url to custom url", true);
			// determine the env to use first
			if (baseURL.contains("test") || baseURL.contains("-t-")) {
				env = "test";
			} else if (baseURL.contains("dev") || baseURL.contains("-d-")) {
				env = "dev";
			} else if (baseURL.contains("prod") || baseURL.contains("-p-")) {
				env = "prod";
			} else {
				env = System.getenv("TEST_ENV");
			}
			// get the urls for that env
			urlsJSON = envsJSON.get(env).getAsJsonObject();
			// reset the current suite/service url to be the custom url
			urlsJSON.remove(suite);
			urlsJSON.addProperty(suite, baseURL);
		} else {
			urlsJSON = envsJSON.get(env).getAsJsonObject();
		}
		Reporter.log("Running off " + env + " environment", true);
		utilsUrls = utilsEnvsJSON.get(env).getAsJsonObject();
		return urlsJSON;
	}

	public static final JsonObject urls = getUrls();
}
