package org.chase.utilities.apiUtilities;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.springframework.http.MediaType;

import io.restassured.filter.cookie.CookieFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIUtils {

	protected RequestSpecification requestSpecPayload(String baseUri, String payload) {
		return given().log().all().baseUri(baseUri).accept("application/json").contentType("application/json")
				.body(payload);
	}

	protected RequestSpecification requestSpec(String baseUri, String payload, String token) {
		return given().log().all().baseUri(baseUri).header("Authorization", token).accept("application/json")
				.contentType("application/json").body(payload);
	}

	protected RequestSpecification requestSpec(String baseUri) {
		return given().log().all().baseUri(baseUri).accept("application/json");
	}

	protected RequestSpecification requestSpecAcceptJSON(String baseUri, String token) {
		return given().log().all().baseUri(baseUri).header("Authorization", token).relaxedHTTPSValidation()
				.accept(MediaType.APPLICATION_JSON_VALUE);// "application/json"
	}

	protected RequestSpecification requestSpecAcceptAll(String baseUri, String token) {
		return given().log().all().baseUri(baseUri).header("Authorization", token).relaxedHTTPSValidation()
				.accept(MediaType.ALL_VALUE);// "application/json"
	}

	protected RequestSpecification requestSpecAcceptAll(String baseUri) {
		return given().log().all().baseUri(baseUri).relaxedHTTPSValidation().accept(MediaType.ALL_VALUE);// "application/json"
	}

	protected RequestSpecification requestSpecAcceptAllContentTypeJSON(String baseUri, String token) {
		return given().log().all().baseUri(baseUri).header("Authorization", token).relaxedHTTPSValidation()
				.accept(MediaType.ALL_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE);// "application/json"
	}

	protected RequestSpecification requestSpecAcceptAllContentTypeJSON(String baseUri) {
		return given().log().all().baseUri(baseUri).relaxedHTTPSValidation().accept(MediaType.ALL_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE);
	}

	protected RequestSpecification requestSpecAcceptJSON(String baseUri) {
		return given().log().all().baseUri(baseUri).relaxedHTTPSValidation().accept(MediaType.APPLICATION_JSON_VALUE);
	}

	protected RequestSpecification requestSpecAcceptText(String baseUri, String token) {
		return given().log().all().baseUri(baseUri).header("Authorization", token).relaxedHTTPSValidation()
				.accept(MediaType.TEXT_PLAIN_VALUE);// "text/plain"
	}

	protected RequestSpecification requestSpecAcceptText(String baseUri) {
		return given().log().all().baseUri(baseUri).relaxedHTTPSValidation().accept(MediaType.TEXT_PLAIN_VALUE);// "text/plain"
	}

	protected RequestSpecification requestSpecification(String baseUri, String token) {
		return given().log().all().baseUri(baseUri).header("Authorization", token).relaxedHTTPSValidation()
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE);
	}

	protected RequestSpecification requestSpecification(String baseUri) {
		return given().log().all().baseUri(baseUri).relaxedHTTPSValidation()
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE);
	}

	protected RequestSpecification requestSpecificationFormParam(String baseUri) {
		return given().log().all().baseUri(baseUri).relaxedHTTPSValidation()
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE).accept(MediaType.APPLICATION_JSON_VALUE);
	}

	/**
	 * RequestSpecification With Base URI and the MediaType Accepted
	 */
	protected RequestSpecification requestSpecification(String baseUri, ContentType acceptType) {
		return given().log().all().baseUri(baseUri).relaxedHTTPSValidation().accept(acceptType);
	}

	/**
	 * RequestSpecification With Base URI, the MediaType Accepted, and the MediaType
	 * for ContentType
	 */
	protected RequestSpecification requestSpecification(String baseUri, ContentType acceptType,
			ContentType contentType) {
		return given().log().all().baseUri(baseUri).relaxedHTTPSValidation().accept(acceptType)
				.contentType(contentType);
	}

	/**
	 * RequestSpecification With Base URI, the Auth Token in the Header, and the
	 * MediaType Accepted
	 */
	protected RequestSpecification requestSpecification(String baseUri, String token, ContentType acceptType) {
		return given().log().all().baseUri(baseUri).header("Authorization", token).relaxedHTTPSValidation()
				.accept(acceptType);
	}

	/**
	 * RequestSpecification With Base URI, the Auth Token in the Header, the
	 * MediaType Accepted, and the MediaType for ContentType
	 */
	protected RequestSpecification requestSpecification(String baseUri, String token, ContentType acceptType,
			ContentType contentType) {
		return given().log().all().baseUri(baseUri).header("Authorization", token).relaxedHTTPSValidation()
				.accept(acceptType).contentType(contentType);
	}

	public Response endpointWrongMethod(String baseURI, String endpoint, String method) {
		return requestSpecification(baseURI).request(method, endpoint);
	}

	public Response endpointWrongMethod(String baseURI, String endpoint, CookieFilter session, String method) {
		return requestSpecification(baseURI).filter(session).request(method, endpoint);
	}

	public Response endpointWrongMethod(String baseURI, String endpoint, CookieFilter session, String token,
			String method) {
		return requestSpecification(baseURI).filter(session).header("x-csrf-token", token).request(method, endpoint);
	}

	public Response endpointWrongMethod(String baseURI, String auth, String endpoint, String method,
			Map<String, String> queryParams) {
		return requestSpecification(baseURI, auth).queryParams(queryParams).request(method, endpoint);
	}

	public Response endpointWrongMethod(String baseURI, String auth, String endpoint, String method,
			Map<String, String> queryParams, String payload) {
		return requestSpecification(baseURI, auth).queryParams(queryParams).body(payload).request(method, endpoint);
	}

	public Response endpointWrongMethod(String baseURI, String auth, String endpoint, String method) {
		return requestSpecification(baseURI, auth).request(method, endpoint);
	}

	public Response endpointWrongMethod(String baseURI, String auth, String payload, String endpoint, String method) {
		return requestSpecification(baseURI, auth).body(payload).request(method, endpoint);
	}

	public Response endpointWrongMethod(String baseURI, String endpoint, CookieFilter session, String method,
			Map<String, String> queryParams) {
		return requestSpecification(baseURI).filter(session).queryParams(queryParams).request(method, endpoint);
	}

	public Response endpointWrongMethod(String baseURI, String endpoint, CookieFilter session, String method,
			Map<String, String> queryParams, String payload) {
		return requestSpecification(baseURI).filter(session).queryParams(queryParams).body(payload).request(method,
				endpoint);
	}
}