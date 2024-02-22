package org.chase.utilities.commmonUtilities;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.testng.Reporter;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Utilities {
	private static final String UNICODE_FORMAT = "UTF8";
	public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
	private KeySpec ks;
	private SecretKeyFactory skf;
	private Cipher cipher;
	byte[] arrayBytes;
	private String myEncryptionKey;
	private String myEncryptionScheme;
	SecretKey key;

	/**
	 * Takes raw response and converts to JsonPath object
	 *
	 * @param response
	 * @return JsonPath object
	 */
	public static JsonPath rawToJson(String response) {
		JsonPath js1 = new JsonPath(response);
		return js1;
	}

	/**
	 * Gets file text as string using path
	 *
	 * @param path - path to file
	 * @return fileText - string for file's contents
	 * @throws IOException
	 */
	public static String getFileText(String path) {
		String fileTxt = "";
		try {
			fileTxt = Files.readString(Paths.get(path));
		} catch (IOException e) {

		}
		return fileTxt;
	}

	/**
	 * Gets specified json from json directory
	 *
	 * @param pathToJson - path to reach json; starts in json directory
	 * @return jsonText - the text from the json file specified
	 * @throws IOException
	 */
	public static String getJSON(String pathToJson) {
		String json = "";
		try {
			json = Files.readString(Paths.get(Constants.TEST_RESOURCES_PATH + "json/" + pathToJson));
		} catch (IOException e) {

		}
		return json;
	}

	public static String getJSON(String resourcePath, String pathToJson) {
//		Helper.logInfo("Getting JSON");
		String json = "";
		try {
			json = Files.readString(Paths.get(resourcePath + "json/" + pathToJson));
		} catch (IOException e) {
			Helper.logError("Error\nCause: " + e.getCause() + "\nMessage: " + e.getMessage() + "\nStacktrace"
					+ e.getStackTrace());
		}
		return json;
	}

	/**
	 * This function parses json to array
	 *
	 * @param jsonMessage : message that need to parse
	 */
	public static JsonArray parseJsonToArray(String jsonMessage) {
		JsonArray array = JsonParser.parseString(jsonMessage).getAsJsonArray();

		return array;
	}

	/**
	 * This function parses json to array
	 *
	 * @param jsonMessage : message that need to parse
	 */
	public static JsonArray parseJSONRecordsToArray(String jsonMessage, Boolean isPSQL) {
		JsonElement elem = JsonParser.parseString(jsonMessage);

		JsonArray array = null;
		if (isPSQL) {
			JsonObject obj = elem.getAsJsonObject();
			array = obj.get("rows").getAsJsonArray();
		} else {
			array = elem.getAsJsonArray();
		}
		return array;
	}

	/**
	 * This function parses json to array
	 *
	 * @param jsonFilePath: path to the file from json directory
	 */
	public static JsonObject getJsonObject(String pathToJson) {
		String jsonString = getJSON(pathToJson);
		return JsonParser.parseString(jsonString).getAsJsonObject();
	}

	/**
	 * This function parses json to object
	 *
	 * @param resoucePath: resource path to use
	 * @param pathToJson:  path to the file from json directory
	 */
	public static JsonObject getJsonObject(String resourcePath, String pathToJson) {
		String jsonString = getJSON(resourcePath, pathToJson);
		return JsonParser.parseString(jsonString).getAsJsonObject();
	}

	/**
	 * This function parses json to array
	 *
	 * @param resp: response object
	 */
	public static JsonObject getJsonObjectFromResponse(Response resp) {
		String jsonString = resp.getBody().asString();
		return JsonParser.parseString(jsonString).getAsJsonObject();
	}

	public static JsonObject getJsonObjectFromString(String jsonStr) {
		return JsonParser.parseString(jsonStr).getAsJsonObject();
	}

	/**
	 * This function gets the values from json
	 *
	 * @param jsonfield : json field to get
	 * @param jsonMsg   : jsonMsg
	 * @return returns the vales of json key.
	 * @throws IOException : Throws exception if
	 */

	public static String getJsonFieldValue(String jsonfield, String jsonMsg) {
		return JsonParser.parseString(jsonMsg).getAsJsonObject().get(jsonfield).getAsString();
	}

	public static String getPayload(String pathToJson, HashMap<String, String> targetMap) {
		Helper.logInfo("Getting Payload");
		String jsonString = Utilities.getJSON(Constants.TEST_RESOURCES_PATH, pathToJson);
		String payload = "";
		for (HashMap.Entry<String, String> val : targetMap.entrySet()) {
			if (jsonString.contains(val.getKey())) {
				payload = jsonString.replace(val.getKey(), val.getValue());
				jsonString = payload;
			}
		}
		return payload;
	}

	public static String getPayload(String pathToJson, HashMap<String, String> targetMap, String resource) {
		Helper.logInfo("Getting Payload");
		String jsonString = Utilities.getJSON(resource, pathToJson);
		String payload = "";
		for (HashMap.Entry<String, String> val : targetMap.entrySet()) {
			if (jsonString.contains(val.getKey())) {
				payload = jsonString.replace(val.getKey(), val.getValue());
				jsonString = payload;
			}
		}
		return payload;
	}

	/**
	 * Gets the value of key from properties file
	 *
	 * @param key : Key is field for which value need to be fetched
	 * @return : returns the String : value of a key
	 * @throws IOException : throws exception if file not found
	 */
	public static String getPropertyValueByKey(String key) {
		String propValue = "";
		try {
			FileInputStream in = new FileInputStream(Constants.PROPERTIES_PATH);
			Properties props = new Properties();
			props.load(in);
			propValue = props.getProperty(key);
		} catch (IOException e) {
			// do something
			propValue = "NOT_FOUND";
		}
		return propValue;
	}

	public static Document convertStringToXMLDocument(String xmlString) {
		// Parser that produces DOM object trees from XML content
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		// API to obtain DOM Document instance
		DocumentBuilder builder = null;
		try {
			// Create DocumentBuilder with default configuration
			builder = factory.newDocumentBuilder();

			// Parse the content to Document object
			Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Document stringToDom(String xmlSource)
			throws SAXException, ParserConfigurationException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		return builder.parse(new InputSource(new StringReader(xmlSource)));
	}

	public static void writeFile(String stringToConvert, String path) throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter(path));
		try {
			out.write(stringToConvert);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	/**
	 * Writes and appends a String in a file.
	 *
	 * @param path           : the file path along with file name
	 * @param stringToAppend : the value that needs to be saved in the file
	 * @return : returns a file with the stringToAppend values in the specified path
	 */
	public static void writeToFileAppend(String path, String stringToAppend) {
		try (FileWriter writer = new FileWriter(path, true)) {
			writer.write(stringToAppend + System.lineSeparator());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Generates random string with a limit specified
	 *
	 * @param minLimit minimum number of letters required to generate a string
	 * @return returns the random string that is generated
	 */
	public static String generateRandomString(int minLimit, int maxLimit) {
		if (minLimit < 0 || maxLimit < 0 || minLimit > maxLimit) {
			throw new IllegalArgumentException("Invalid input limits");
		}

		Random random = new Random();
		int length = random.nextInt(maxLimit - minLimit + 1) + minLimit;
		StringBuilder randomString = new StringBuilder();

		for (int i = 0; i < length; i++) {
			char randomChar = (char) (random.nextInt(26) + 'a'); // Generating a random lowercase letter
			randomString.append(randomChar);
		}
		return randomString.toString();

	}

	public static int getRandomInt(int min, int max) {
		int value = 0;
		Random rand = new Random();
		value = rand.nextInt(max - min) + min;
		return value;
	}

	// Get the list of items based on node path
	private List<Object> getListByNode(Response response, String fullNodePath) {
		JsonPath jsonPathEvaluator = response.jsonPath();
		List<Object> objectList = jsonPathEvaluator.get(fullNodePath); // like "test", "test.subTest" etc
		return objectList;
	}

	// Get the list of dublicates in ArrayList
	public static Set<String> searchDuplicates(ArrayList<String> stringArrayList) {
		Set<String> duplicateSearch = stringArrayList.stream()
				.filter(a -> Collections.frequency(stringArrayList, a) > 1).collect(Collectors.toSet());
		return duplicateSearch;
	}

	// Compare two arrayList,( a --> has b --> does not have )if any value not match
	// return
	public static Set<String> compareTwoArrayListForMatch(ArrayList<String> Actual, ArrayList<String> Expected) {

		Set<String> listOfNotMatchingValue = Actual.stream().filter(a -> !Expected.contains(a))
				.collect(Collectors.toSet());
		return listOfNotMatchingValue;
	}

	public static JsonArray getJsonRecordsArrayFromResponse(Response resp, Boolean isPSQL) {
		return parseJSONRecordsToArray(resp.then().extract().response().asString(), isPSQL);
	}

	/**
	 * Converts CSV to 2-D array
	 *
	 * @param resourcePath - path to csv from csv folder
	 * @param delimiter    - delimiter for csv
	 * @param skipFirst    - skips first line if true
	 * @returns csvList - array of csv data
	 */
	public static String[][] convertCSVToArray(String resourcePath, String delimiter, Boolean skipFirst) {
		String[][] csvArr = null;
		Stream<String> csvLines = null;
		try {
			Reporter.log("Attempting to read lines from csv", true);
			csvLines = Files.lines(Paths.get(Constants.TEST_RESOURCES_PATH + "csv/" + resourcePath));
			Reporter.log("Attempting to convert csv to 2d string array", true);
			if (skipFirst) {
				csvArr = csvLines.skip(1).map(line -> line.split(delimiter)).toArray(String[][]::new);
			} else {
				csvArr = csvLines.map(line -> line.split(delimiter)).toArray(String[][]::new);
			}
			Reporter.log("Attempting to close csv", true);
			csvLines.close();
		} catch (IOException err) {
			Reporter.log("An IO exception occurred reading from csv:\n" + err, true);
		}
		return csvArr;
	}

	public static String[][] getRandomSampleFromCSVArray(String[][] arr, int sampleSize) {
		int start = getRandomInt(0, arr.length - sampleSize);
		return Arrays.copyOfRange(arr, start, sampleSize + start);
	}

	public static String[] getRandomSampleFromArray(String[] arr, int sampleSize) {
		int start = getRandomInt(0, arr.length - sampleSize);
		return Arrays.copyOfRange(arr, start, sampleSize + start);
	}

	/**
	 * Removes the brackets, parenthesis and curly-braces from given string
	 *
	 * @param response
	 * @param path
	 * @return
	 */
	public static String removeEnclosingBrackets(Response response, String path) {
		return response.jsonPath().getString(path).replaceAll("[\\[\\](){}]", "");
	}

	/**
	 * Returns a List<String> from given Response object from given path
	 *
	 * @param response
	 * @param path
	 * @return
	 */
	public static List<String> getResponseAsList(Response response, String path) {
		return response.jsonPath().getList(path);
	}

	/*
	 * Converts an unformatted Object to JSON using gson Example: List<Object} obj =
	 * {garageItemAttributeId=108, garageItemAttributeTypeId=null}; by default gson
	 * ignores the null values. By using serializeNulls(), it will save the null
	 * keys to json as well.
	 */
	public static String convertToJsonPrettyPrint(Object obj) {
		Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
		return gson.toJson(obj);
	}

	/**
	 * Converts an unformatted Object to JSON using gson library. Example:
	 * List<Object> obj = {garageItemAttributeId=108,
	 * garageItemAttributeTypeId=null}; by default gson ignores the null values. By
	 * using serializeNulls(), it will save the null keys to json as well and will
	 * convert it like this {"garageItemAttributeId":108,
	 * "garageItemAttributeTypeId":null}
	 * 
	 * @param - obj
	 * @return - returns a proper JSON
	 * @author - A S M Kamran
	 * 
	 */
	public static String convertToJson(Object obj) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		return gson.toJson(obj);
	}

	public static String convertToJson(List<Object> obj) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		return gson.toJson(obj);
	}

	/**
	 * Converts an unformatted String to JSON using gson. Example: String str =
	 * {garageItemAttributeId=108, garageItemAttributeTypeId=2}; This will be
	 * converted to {"garageItemAttributeId":108, "garageItemAttributeTypeId":2}
	 * 
	 * @param - string
	 * @return - returns a proper JSON
	 * @author - A S M Kamran
	 */
	public static String convertToJson(String str) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		return gson.toJson(str);
	}

	/**
	 * Takes an address as a parameter and extracts the street Address,
	 * Apartment/Floor, City, State, and Zip code from the address
	 *
	 * @param address
	 * @returns a map containing streetAddress, floorNumber, city, state, zipCode.
	 *          It fails to correctly parse the address if it does not have a zip
	 *          code.
	 */
	public static Map<String, String> parseAddress(String address) {
		String streetAddress = "", floorNumber = "", city = "", state = "", zipCode = "";
		address = address.replace(",", "").trim();
		address = address.replace(".", "");

		// Extract street address
		Pattern streetAddressPattern = Pattern.compile("\\d+\\s+\\w+(?:\\s+\\w+)*");
		Matcher streetAddressMatcher = streetAddressPattern.matcher(address);
		if (streetAddressMatcher.find()) {
			streetAddress = streetAddressMatcher.group().trim();
		}

		// Extract floor or apartment number
		Pattern floorNumberPattern = Pattern.compile("(?i)(?:Apt|Floor)\\s+(\\w+)");
		Matcher floorNumberMatcher = floorNumberPattern.matcher(address);
		if (floorNumberMatcher.find()) {
			floorNumber = floorNumberMatcher.group(1).trim();
		}

		// Extract city, state, and zip code
		Pattern cityStateZipPattern = Pattern.compile("(\\w+)\\s+([A-Za-z]{2})\\s+(\\d{5}(?:-\\d{4})?)");
		Matcher cityStateZipMatcher = cityStateZipPattern.matcher(address);
		if (cityStateZipMatcher.find()) {
			city = cityStateZipMatcher.group(1).trim();
			state = cityStateZipMatcher.group(2).trim();
			zipCode = cityStateZipMatcher.group(3).trim();
		}

		// Create a map to store the extracted address components
		Map<String, String> addressComponents = new HashMap<>();
		addressComponents.put("streetAddress", streetAddress);
		addressComponents.put("zipCode", zipCode);
		addressComponents.put("city", city);
		addressComponents.put("state", state);
		addressComponents.put("floorNumber", floorNumber);

		// Create a regular expression to match the elements to be removed.
		String regex = "\\b(?:" + addressComponents.get("zipCode") + "|" + addressComponents.get("city") + "|"
				+ addressComponents.get("floorNumber") + "|" + addressComponents.get("state") + ")\\b";
		// Replace all matching elements with an empty string.
		addressComponents.put("streetAddress", addressComponents.get("streetAddress").replaceAll(regex, "").trim());

		// Return the map of extracted address components
		return addressComponents;
	}

	/**
	 * Gets specified xml from XML directory
	 *
	 * @param pathToXML - path to reach xml; starts in XML directory
	 * @return xmlText - the text from the xml file specified
	 * @throws IOException
	 */
	public static String getXML(String pathToXml) {
		String xmlText = "";
		try {
			xmlText = Files.readString(Paths.get(Constants.TEST_RESOURCES_PATH + "xml/" + pathToXml));
		} catch (IOException e) {
			Helper.logError("Error\nCause: " + e.getCause() + "\nMessage: " + e.getMessage() + "\nStacktrace"
					+ e.getStackTrace());
		}
		return xmlText;
	}

	public static String getXML(String resourcePath, String pathToXml) {
		String xmlText = "";
		try {
			xmlText = Files.readString(Paths.get(resourcePath + "xml/" + pathToXml));
		} catch (IOException e) {
			Helper.logError("Error\nCause: " + e.getCause() + "\nMessage: " + e.getMessage() + "\nStacktrace"
					+ e.getStackTrace());
		}
		return xmlText;
	}

	/**
	 * Removes specified tag from XML for negative testing
	 * 
	 * @param payload
	 * @param tag
	 * @return
	 */
	public static String removeXMLTagAndReturnAsString(String payload, String tag) {
		Transformer transformer;
		Document doc;
		try {
			doc = convertStringToXMLDocument(payload);
			Node node = doc.getElementsByTagName(tag).item(0);
			Node parent = node.getParentNode();
			parent.removeChild(node);
			parent.normalize();

			DOMSource domSource = new DOMSource(doc);
			transformer = TransformerFactory.newInstance().newTransformer();
			StringWriter sw = new StringWriter();
			StreamResult sr = new StreamResult(sw);
			transformer.transform(domSource, sr);
			return sw.toString();
		} catch (TransformerFactoryConfigurationError | TransformerException e) {
			Helper.logError("Error\nCause: " + e.getCause() + "\nMessage: " + e.getMessage() + "\nStacktrace"
					+ e.getStackTrace());
		}
		return null;
	}

	/**
	 * Replaces text for specified tag for negative testing
	 * 
	 * @param payload
	 * @param tag
	 * @param text
	 * @return
	 */
	public static String replaceXMLTagTextAndReturnAsString(String payload, String tag, String text) {
		Transformer transformer;
		Document doc;
		try {
			doc = convertStringToXMLDocument(payload);
			Node node = doc.getElementsByTagName(tag).item(0);
			node.setTextContent(text);
			doc.normalize();

			DOMSource domSource = new DOMSource(doc);
			transformer = TransformerFactory.newInstance().newTransformer();
			StringWriter sw = new StringWriter();
			StreamResult sr = new StreamResult(sw);
			transformer.transform(domSource, sr);
			return sw.toString();
		} catch (TransformerFactoryConfigurationError | TransformerException e) {
			Helper.logError("Error\nCause: " + e.getCause() + "\nMessage: " + e.getMessage() + "\nStacktrace"
					+ e.getStackTrace());
		}
		return null;

	}

}
