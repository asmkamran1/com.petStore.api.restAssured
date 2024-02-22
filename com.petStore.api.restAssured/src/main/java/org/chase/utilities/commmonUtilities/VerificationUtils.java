package org.chase.utilities.commmonUtilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.Assert;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class VerificationUtils {

	/*
	 * // * Need to build out - Hard Asserts & Soft Asserts
	 *
	 */
//	public static void validateMultipleExpected(Object actual, Object expected1, Object expected2, Object expected3,
//			String message) {
//
//		try {
//			logFile(actual, "not " + expected1 + " not " + expected2 + " not " + expected3);
//
//			assertThat(actual, anyOf(is(expected1), is(expected2), is(expected3)));
//
//			ExtentLogger.pass("Assertion For - <b> <u>" + message + "</u> </b>   |   <b><i>Actual: </i> </b>" + actual
//					+ " and <b><i> Expected: NOT </i> </b>" + expected1);
//		} catch (AssertionError assertionError) {
//			ExtentLogger.fail("Assertion For - <b> <u>" + message + "   |   <b><i>Actual: </i> </b>" + actual
//					+ " and <b><i> Expected: NOT </i> </b>" + expected1 + expected2 + expected3);
//			Assert.fail(message);
//		}
//	}

	public static void validateNotEqual(Object actual, Object expected, String message) {
		try {
			logFile(actual, "not " + expected);
			Assert.assertNotEquals(actual, expected, message);
			ExtentLogger.pass("Assertion For - <b> <u>" + message + "</u> </b>   |   <b><i>Actual: </i> </b>" + actual
					+ " and <b><i> Expected: NOT </i> </b>" + expected);
		} catch (AssertionError assertionError) {
			ExtentLogger.fail("Assertion For - <b> <u>" + message + "   |   <b><i>Actual: </i> </b>" + actual
					+ " and <b><i> Expected: NOT </i> </b>" + expected);
			Assert.fail(message);
		}
	}

	public static void validateNotEqualNoActualLog(Object actual, Object expected, String message) {
		try {
			logFile("*****", "not " + expected);
			Assert.assertNotEquals(actual, expected, message);
			ExtentLogger.pass(
					"Assertion For - <b> <u>" + message + "</u> </b>   |  <b><i> Expected: NOT </i> </b>" + expected);
		} catch (AssertionError assertionError) {
			ExtentLogger.fail("Assertion For - <b> <u>" + message + "   |  <b><i> Expected: NOT </i> </b>" + expected);
			Assert.fail(message);
		}
	}

	public static void validate(Object actual, Object expected, String message) {
		try {
			logFile(actual, expected);
			Assert.assertEquals(actual, expected, message);
			ExtentLogger.pass("Assertion For - <b> <u>" + message + "</u> </b>   |   <b><i>Actual: </i> </b>" + actual
					+ " and <b><i> Expected: </i> </b>" + expected);
		} catch (AssertionError assertionError) {
			ExtentLogger.fail("Assertion For - <b> <u>" + message + "   |   <b><i>Actual: </i> </b>" + actual
					+ " and <b><i> Expected: </i> </b>" + expected);
			Assert.fail(message);
		}
	}

	/**
	 * Validates JSON with array of objects - if array is not in same order, pass
	 * primaryKeyPath; (NOTE: will not work for JSON with arrays that have objects
	 * with arrays that need sorting as well) - has better logging than
	 * validateJSONLenient
	 *
	 * @param actual             - the actual value
	 * @param expected           - the expected value
	 * @param primaryKeyPath     - the path to the primary key for the objects in
	 *                           the array (the path to what property is unique for
	 *                           each object)
	 * @param specialValidations - allows you to specify special validations for
	 *                           specific keys
	 */
//	public static void validateNestedJSONArrayOfObjects(JsonObject actual, JsonObject expected, String[] primaryKeyPath,
//			HashMap<String, String> specialValidations) {
//		Set<String> keys = expected.keySet();
//		Iterator<String> keysIterator = keys.iterator();
//		while (keysIterator.hasNext()) {
//			String currKey = keysIterator.next();
//			JsonElement currExpectedEl = expected.get(currKey);
//			JsonElement currActualEl = actual.get(currKey);
//			if (currExpectedEl == null) {
//				validate(currActualEl, currExpectedEl, "Verifying " + currKey + " equals " + currExpectedEl);
//			} else if (currExpectedEl.isJsonObject()) {
//				validate(currActualEl.isJsonObject(), true, "Verifying " + currKey + " holds Json Object");
//				validateNestedJSONArrayOfObjects(currActualEl.getAsJsonObject(), currExpectedEl.getAsJsonObject(),
//						primaryKeyPath, specialValidations);
//			} else if (currExpectedEl.isJsonArray()) {
//				validate(currActualEl.isJsonArray(), true, "Verifying " + currKey + " holds Json Array");
//				JsonArray currExpectedArr = currExpectedEl.getAsJsonArray();
//				JsonArray currActualArr = currActualEl.getAsJsonArray();
//				for (int i = 0; i < currExpectedArr.size(); i++) {
//					JsonElement currExpectedInd = currExpectedArr.get(i);
//					JsonObject holder = currExpectedInd.getAsJsonObject();
//					int k;
//					for (k = 0; k < primaryKeyPath.length - 1; k++) {
//						holder = holder.get(primaryKeyPath[k]).getAsJsonObject();
//					}
//					String expectedSearchVal = holder.get(primaryKeyPath[k]).toString();
//					JsonElement currActualInd = currActualArr.get(i);
//					if (primaryKeyPath.length == 0) {
//						validate(currActualInd.toString(), currExpectedInd.toString(),
//								"Verifying " + currKey + " value is " + currExpectedInd.toString());
//					} else {
//						boolean found = false;
//						int j = 0;
//						while (j < currActualArr.size() && found == false) {
//							currActualInd = currActualArr.get(j);
//							holder = currActualInd.getAsJsonObject();
//							int l;
//							for (l = 0; l < primaryKeyPath.length - 1; l++) {
//								holder = holder.get(primaryKeyPath[l]).getAsJsonObject();
//							}
//							String actualSearchVal = holder.get(primaryKeyPath[l]).toString();
//							if (expectedSearchVal.equals(actualSearchVal)) {
//								found = true;
//							} else {
//								j++;
//							}
//						}
//						validate(found, true, "Expecting to find primary key value " + expectedSearchVal);
//						if (currExpectedInd.isJsonObject()) {
//							validate(currActualInd.isJsonObject(), true, "Verifying " + currKey + " holds Json Object");
//							validateNestedJSONArrayOfObjects(currActualInd.getAsJsonObject(),
//									currExpectedInd.getAsJsonObject(), primaryKeyPath, specialValidations);
//						}
//					}
//				}
//			} else if (currExpectedEl.isJsonPrimitive()) {
//				if (specialValidations.containsKey(currKey)) {
//					switch (specialValidations.get(currKey)) {
//					case "roundFloor":
//						validate(Math.floor(currActualEl.getAsDouble()), Math.floor(currExpectedEl.getAsDouble()),
//								"Verifying " + currKey + " value matches " + currExpectedEl + " taking floor");
//						break;
//					case "roundCeil":
//						validate(Math.ceil(currActualEl.getAsDouble()), Math.ceil(currExpectedEl.getAsDouble()),
//								"Verifying " + currKey + " value matches " + currExpectedEl + " taking ceiling");
//						break;
//					case "within1":
//						validateBetween(currActualEl.getAsDouble(), currExpectedEl.getAsDouble() - 1,
//								currExpectedEl.getAsDouble() + 1,
//								"Verifying " + currKey + " value within range of 1 of " + currExpectedEl);
//						break;
//					default:
//						validate(currActualEl.getAsString().trim(), currExpectedEl.getAsString().trim(),
//								"Verifying " + currKey + " value matches " + currExpectedEl + " trimmed");
//						break;
//					}
//				} else {
//					validate(currActualEl, currExpectedEl,
//							"Verifying " + currKey + " primitive value matches " + currExpectedEl);
//				}
//
//			} else {
//				validate(currActualEl, currExpectedEl,
//						"Verifying " + currKey + " primitive value matches " + currExpectedEl);
//			}
//		}
//	}

	/**
	 * Validates JSON with array of objects - if array is not in same order, pass
	 * primaryKeyPath; (NOTE: will not work for JSON with arrays that have objects
	 * with arrays that need sorting as well) - has better logging than
	 * validateJSONLenient
	 *
	 * @param actual         - the actual value
	 * @param expected       - the expected value
	 * @param primaryKeyPath - the path to the primary keys for the objects in the
	 *                       array (the path to what property is unique for each
	 *                       object)
	 */
	public static void validateNestedJSONArrayOfObjects(JsonObject actual, JsonObject expected,
			String[][] primaryKeyArr) {
		Set<String> keys = expected.keySet();
		Iterator<String> keysIterator = keys.iterator();
		while (keysIterator.hasNext()) {
			String currKey = keysIterator.next();
			JsonElement currExpectedEl = expected.get(currKey);
			JsonElement currActualEl = actual.get(currKey);
			if (currExpectedEl == null) {
				validate(currActualEl, currExpectedEl, "Verifying " + currKey + " equals " + currExpectedEl);
			} else if (currExpectedEl.isJsonObject()) {
				validate(currActualEl.isJsonObject(), true, "Verifying " + currKey + " holds Json Object");
				validateNestedJSONArrayOfObjects(currActualEl.getAsJsonObject(), currExpectedEl.getAsJsonObject(),
						primaryKeyArr);
			} else if (currExpectedEl.isJsonArray()) {
				validate(currActualEl.isJsonArray(), true, "Verifying " + currKey + " holds Json Array");
				JsonArray currExpectedArr = currExpectedEl.getAsJsonArray();
				JsonArray currActualArr = currActualEl.getAsJsonArray();
				for (int i = 0; i < currExpectedArr.size(); i++) {
					JsonElement currExpectedInd = currExpectedArr.get(i);
					JsonObject holder = currExpectedInd.getAsJsonObject();
					List<String> expectedVals = new ArrayList<String>();
					for (int k = 0; k < primaryKeyArr.length; k++) {
						String[] currPKey = primaryKeyArr[k];
						int l = 0;
						for (l = 0; l < currPKey.length - 1; l++) {
							holder = holder.get(currPKey[l]).getAsJsonObject();
						}
						expectedVals.add(holder.get(currPKey[l]).toString());
					}
					JsonElement currActualInd = currActualArr.get(i);
					if (primaryKeyArr.length == 0) {
						validate(currActualInd.toString(), currExpectedInd.toString(),
								"Verifying " + currKey + " value is " + currExpectedInd.toString());
					} else {
						boolean found = false;
						int j = 0;
						int keyNumber = 0;
						while (j < currActualArr.size() && found == false) {
							currActualInd = currActualArr.get(j);
							holder = currActualInd.getAsJsonObject();
							for (int k = 0; k < primaryKeyArr.length; k++) {
								String[] currPKeys = primaryKeyArr[k];
								int l;
								for (l = 0; l < currPKeys.length - 1; l++) {
									holder = holder.get(currPKeys[l]).getAsJsonObject();
								}
								String actualSearchVal = holder.get(currPKeys[l]).toString();
								if (expectedVals.get(keyNumber).equals(actualSearchVal)) {
									keyNumber++;
									if (expectedVals.size() == keyNumber) {
										found = true;
									}
								} else {
									keyNumber = 0;
								}
							}
							if (found == false) {
								j++;
							}
						}
						validate(found, true,
								"Expecting to find primary key values " + String.join(", ", expectedVals));
						if (currExpectedInd.isJsonObject()) {
							validate(currActualInd.isJsonObject(), true, "Verifying " + currKey + " holds Json Object");
							validateNestedJSONArrayOfObjects(currActualInd.getAsJsonObject(),
									currExpectedInd.getAsJsonObject(), primaryKeyArr);
						}
					}
				}
			} else if (currExpectedEl.isJsonPrimitive()) {
				validate(currActualEl, currExpectedEl, "Verifying " + currKey + " value matches " + currExpectedEl);
			} else {
				validate(currActualEl, currExpectedEl,
						"Verifying " + currKey + " primitive value matches " + currExpectedEl);
			}
		}
	}

	/**
	 * Validates JSON with array of objects - if array is not in same order, pass
	 * primaryKeyPath; (NOTE: will not work for JSON with arrays that have objects
	 * with arrays that need sorting as well) - has better logging than
	 * validateJSONLenient
	 *
	 * @param actual         - the actual value
	 * @param expected       - the expected value
	 * @param primaryKeyPath - the path to the primary key for the objects in the
	 *                       array (the path to what property is unique for each
	 *                       object)
	 */
	public static void validateNestedJSONArrayOfObjects(JsonObject actual, JsonObject expected,
			String[] primaryKeyPath) {
		Set<String> keys = expected.keySet();
		Iterator<String> keysIterator = keys.iterator();
		while (keysIterator.hasNext()) {
			String currKey = keysIterator.next();
			JsonElement currExpectedEl = expected.get(currKey);
			JsonElement currActualEl = actual.get(currKey);
			if (currExpectedEl == null) {
				validate(currActualEl, currExpectedEl, "Verifying " + currKey + " equals " + currExpectedEl);
			} else if (currExpectedEl.isJsonObject()) {
				validate(currActualEl.isJsonObject(), true, "Verifying " + currKey + " holds Json Object");
				validateNestedJSONArrayOfObjects(currActualEl.getAsJsonObject(), currExpectedEl.getAsJsonObject(),
						primaryKeyPath);
			} else if (currExpectedEl.isJsonArray()) {
				validate(currActualEl.isJsonArray(), true, "Verifying " + currKey + " holds Json Array");
				JsonArray currExpectedArr = currExpectedEl.getAsJsonArray();
				JsonArray currActualArr = currActualEl.getAsJsonArray();
				for (int i = 0; i < currExpectedArr.size(); i++) {
					JsonElement currExpectedInd = currExpectedArr.get(i);
					JsonObject holder = currExpectedInd.getAsJsonObject();
					int k;
					for (k = 0; k < primaryKeyPath.length - 1; k++) {
						holder = holder.get(primaryKeyPath[k]).getAsJsonObject();
					}
					String expectedSearchVal = holder.get(primaryKeyPath[k]).toString();
					JsonElement currActualInd = currActualArr.get(i);
					if (primaryKeyPath.length == 0) {
						validate(currActualInd.toString(), currExpectedInd.toString(),
								"Verifying " + currKey + " value is " + currExpectedInd.toString());
					} else {
						boolean found = false;
						int j = 0;
						while (j < currActualArr.size() && found == false) {
							currActualInd = currActualArr.get(j);
							holder = currActualInd.getAsJsonObject();
							int l;
							for (l = 0; l < primaryKeyPath.length - 1; l++) {
								holder = holder.get(primaryKeyPath[l]).getAsJsonObject();
							}
							String actualSearchVal = holder.get(primaryKeyPath[l]).toString();
							if (expectedSearchVal.equals(actualSearchVal)) {
								found = true;
							} else {
								j++;
							}
						}
						validate(found, true, "Expecting to find primary key value " + expectedSearchVal);
						if (currExpectedInd.isJsonObject()) {
							validate(currActualInd.isJsonObject(), true, "Verifying " + currKey + " holds Json Object");
							validateNestedJSONArrayOfObjects(currActualInd.getAsJsonObject(),
									currExpectedInd.getAsJsonObject(), primaryKeyPath);
						}
					}
				}
			} else if (currExpectedEl.isJsonPrimitive()) {
				validate(currActualEl, currExpectedEl, "Verifying " + currKey + " value matches " + currExpectedEl);
			} else {
				validate(currActualEl, currExpectedEl,
						"Verifying " + currKey + " primitive value matches " + currExpectedEl);
			}
		}
	}

	public static void validateJSONLenient(String actual, String expected, String message) {
		try {
			logFile(actual, expected, message);
			JSONAssert.assertEquals(actual, expected, JSONCompareMode.LENIENT);
			ExtentLogger.pass("Assertion For - <b> <u>" + message + "</u> </b>   |   <b><i>Actual: </i> </b>" + actual
					+ " and <b><i> Expected: </i> </b>" + expected);
		} catch (AssertionError assertionError) {
			ExtentLogger.fail("FAILED: Assertion For - <b> <u>" + message + "   |   <b><i>Actual: </i> </b>" + actual
					+ " and <b><i> Expected: </i> </b>" + expected);
			Assert.fail(message);
		}
	}

//	public static void validateBetween(double actual, double min, double max, String message) {
//		String range = "Between Min: " + min + " and Max: " + max;
//		try {
//			logFile(actual, range, message);
//			assertThat(actual, allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max)));
//			ExtentLogger.pass("Assertion For - <b> <u>" + message + "</u> </b>   |   <b><i>Actual: </i> </b>" + actual
//					+ " and <b><i> Expected: </i> </b>" + range);
//		} catch (AssertionError assertionError) {
//			ExtentLogger.fail("FAILED: Assertion For - <b> <u>" + message + "   |   <b><i>Actual: </i> </b>" + actual
//					+ " and <b><i> Expected: </i> </b>" + range);
//			Assert.fail(message);
//		}
//	}

	public static void validate(int actual, int expected, String message) {
		try {
			logFile(actual, expected);
			Assert.assertEquals(actual, expected, message);
			ExtentLogger.pass("Assertion For - <b> <u>" + message + "</u> </b>   |   <b><i>Actual: </i> </b>" + actual
					+ " and <b><i> Expected: </i> </b>" + expected);
		} catch (AssertionError assertionError) {
			ExtentLogger.fail("Assertion For - <b> <u>" + message + "   |   <b><i>Actual: </i> </b>" + actual
					+ " and <b><i> Expected: </i> </b>" + expected);
			Assert.fail(message);
		}
	}

	/**
	 * Note: this is only to be used if you do not desire a fail to be logged to the
	 * extent report for the validation
	 */
	public static void validateNoFailLog(int actual, int expected, String message) {
		try {
			logFile(actual, expected);
			Assert.assertEquals(actual, expected, message);
			ExtentLogger.pass("Assertion For - <b> <u>" + message + "</u> </b>   |   <b><i>Actual: </i> </b>" + actual
					+ " and <b><i> Expected: </i> </b>" + expected);
		} catch (AssertionError assertionError) {
			Helper.log().info("Assertion For - <b> <u>" + message + "   |   <b><i>Actual: </i> </b>" + actual
					+ " and <b><i> Expected: </i> </b>" + expected);
			Assert.fail(message);
		}
	}

	public static void validateContains(String actual, String expected) {
		String message = "";
		try {
			message = "Verifying actual response: " + actual + " \n contains expected response: " + expected;
			Helper.log().info(message);
			Assert.assertTrue(actual.indexOf(expected) > -1);
			ExtentLogger.pass("Assertion For - <b> <u>" + message + "</u> </b>   |   <b><i>Actual: </i> </b>" + actual
					+ " and <b><i> Expected: </i> </b>" + expected);
		} catch (AssertionError assertionError) {
			ExtentLogger.fail("Assertion For - <b> <u>" + message + "   |   <b><i>Actual: </i> </b>" + actual
					+ " and <b><i> Expected: </i> </b>" + expected);
			Assert.fail(message);
		}
	}

	public static void validateStringDoesNotContain(String actual, String expected) {
		String message = "";
		try {
			message = "Verifying actual response: " + actual + " \n does not contain expected response: " + expected;
			Helper.log().info(message);
			Assert.assertTrue(actual.indexOf(expected) == -1);
			ExtentLogger.pass("Assertion For - <b> <u>" + message + "</u> </b>   |   <b><i>Actual: </i> </b>" + actual
					+ " and <b><i> Expected: </i> </b>" + expected);
		} catch (AssertionError assertionError) {
			ExtentLogger.fail("Assertion For - <b> <u>" + message + "   |   <b><i>Actual: </i> </b>" + actual
					+ " and <b><i> Expected: </i> </b>" + expected);
			Assert.fail(message);
		}
	}

	public static void validateResponse(boolean result, String message) {
		try {
			Assert.assertTrue(result);
			ExtentLogger.pass("<b><i>" + message + "</b></i>");
		} catch (AssertionError assertionError) {
			ExtentLogger.fail("<b><i>" + message + "</b></i>");
			Assert.fail(message);
		}
	}

	private static void logFile(Object actual, Object expected) {
		Helper.log().info("Actual: " + actual + ", Expected: " + expected);
	}

	private static void logFile(Object actual, Object expected, String message) {
		Helper.log().info(message);
		Helper.log().info(" Actual: " + actual + ", Expected: " + expected);
	}

	/**
	 * validates whether a number is greater than or equal to the expected number
	 *
	 * @param actual   - the actual number
	 * @param expected - the expected number
	 * @param message
	 */
	public static void validateGTE(int actual, int expected, String message) {
		try {
			logFile(actual, expected);
			Assert.assertTrue(actual >= expected, message);
			ExtentLogger.pass("Assertion For - <b> <u>" + message + "</u> </b>   |   <b><i>Actual: </i> </b>" + actual
					+ " and <b><i> Expected: </b> </b>" + expected);
		} catch (AssertionError assertionError) {
			ExtentLogger.fail("Assertion For - <b> <u>" + message + "   |   <b><i>Actual: </b> </b>" + actual
					+ " and <b><i> Expected: </b> </b>" + expected);
			Assert.fail(message);
		}
	}

	/**
	 * Validates whether a number is less than or equal to the expected number
	 *
	 * @param actual   - the actual number
	 * @param expected - the expected number
	 * @param message
	 */
	public static void validateLTE(int actual, int expected, String message) {
		try {
			logFile(actual, expected);
			Assert.assertTrue(actual <= expected, message);
			ExtentLogger.pass("Assertion For - <b> <u>" + message + "</u> </b>   |   <b><i>Actual: </i> </b>" + actual
					+ " and <b><i> Expected: </b> </b>" + expected);
		} catch (AssertionError assertionError) {
			ExtentLogger.fail("Assertion For - <b> <u>" + message + "   |   <b><i>Actual: </b> </b>" + actual
					+ " and <b><i> Expected: </b> </b>" + expected);
			Assert.fail(message);
		}
	}
}
