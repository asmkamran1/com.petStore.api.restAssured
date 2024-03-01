package org.chase.utilities.commmonUtilities;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.util.HtmlUtils;

import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;

public class Helper {
	public String setRandomId(String baseId) {
//		logInfo("Generating Random Id");
		return baseId + Utilities.generateRandomString(5, 5);
	}

	public <K, V> void addNotNullIfAbsent(Map<K, V> map, K key, V value) {
		if (value != null)
			map.putIfAbsent(key, value);
	}

	public static Logger log() {
		return LogManager.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
	}

	public static void logInfo(String logString) {
		log().info(logString);
	}

	public static void logError(String logString) {
		log().error(logString);
	}

	public static void logRequest(FilterableRequestSpecification requestSpec, Response response) {
		log().info(requestSpec.getMethod() + " - " + requestSpec.getURI());
		log().info(requestSpec.getPathParams());
		log().info(requestSpec.getQueryParams());
		log().info(requestSpec.getHeaders().asList());
		log().info(requestSpec.getCookies().asList());
		if (requestSpec.getBody() != null) {
			log().info(requestSpec.getBody().toString());
		}
		log().info("RESPONSE STATUS LINE - " + response.getStatusLine());
		log().info(response.headers().asList());
		log().info(response.getBody().asPrettyString(), "Response");

		String reqBody = "<b>No Request Body</b>";
		String pathParams = "<b>No Path Parameters</b>";
		String queryParams = "<b>No Query Parameters</b>";
		String cookies = "<b>No Cookies</b>";
		String responseBody = response.headers().get("content-type") != null
				&& response.headers().get("content-type").toString().contains("html") == true
				&& response.getBody().asString().contains("<html") == true
						? HtmlUtils.htmlEscape(response.getBody().asString())
						: response.getBody().asPrettyString();

		if (requestSpec.getBody() != null) {
			reqBody = "<b>Request Body</b><br><pre>" + requestSpec.getBody().toString() + "</pre>";
		}
		if (requestSpec.getPathParams().size() > 0) {
			pathParams = "<b>Request Path Params</b><br><pre>" + requestSpec.getPathParams().toString() + "</pre>";
		}
		if (requestSpec.getQueryParams().size() > 0) {
			queryParams = "<b>Request Query Params</b><br><pre>" + requestSpec.getQueryParams().toString() + "</pre>";
		}
		if (requestSpec.getCookies().size() > 0) {
			cookies = "<b>Request Cookies</b><br><pre>" + requestSpec.getCookies().toString() + "</pre>";
		}
		String[] codeBlocks = { "<b>Request</b><br>" + requestSpec.getMethod() + " - " + requestSpec.getURI(),
				pathParams, queryParams,
				"<b>Request Headers</b><br><pre>" + requestSpec.getHeaders().toString() + "</pre>", cookies, reqBody,
				"<b>Response Status Line</b><br><pre>" + response.getStatusLine() + "</pre>",
				"<b>Response Headers</b><br><pre>" + response.headers().toString() + "</pre>",
				"<b>Response body</b><br><pre>" + responseBody + "</pre>" };
	}

	public static void logJson(String json) {
		Markup markup = MarkupHelper.createCodeBlock(json, CodeLanguage.JSON);
		log().info(json);
		ExtentLogger.info(markup);
	}

	public static void logXml(String xml) {
		Markup m = MarkupHelper.createCodeBlock(xml, CodeLanguage.XML);
		log().info(xml);
		ExtentLogger.info(m);
	}

	public static void logList(List<?> list) {
		if (!list.isEmpty()) {
			Markup m = MarkupHelper.createUnorderedList(list);
			log().info(list);
			ExtentLogger.info(m);
		}
	}

	public static void logMap(Map<?, ?> map) {
		if (!map.isEmpty()) {
			Markup m = MarkupHelper.createUnorderedList(map);
			log().info(map);
			ExtentLogger.info(m);
		}
	}

	public static void logSet(Set<?> set) {
		if (!set.isEmpty()) {
			Markup m = MarkupHelper.createUnorderedList(set);
			log().info(set);
			ExtentLogger.info(m);
		}
	}

}
