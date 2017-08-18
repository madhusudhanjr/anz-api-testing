package nz.co.anz.utils;

import static com.jayway.restassured.RestAssured.given;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

public class RestUtil {

	/**
	 *** Sets Base URI*** Before starting the test, we should set the
	 * RestAssured.baseURI
	 */
	public static void setBaseURI(String baseURI) {
		RestAssured.baseURI = baseURI;
	}

	/**
	 *** Reset Base URI (after test)*** After the test, we should reset the
	 * RestAssured.baseURI
	 */
	public static void resetBaseURI() {
		RestAssured.baseURI = null;
	}

	/**
	 *** Sets ContentType*** We should set content type as JSON or XML before
	 * starting the test
	 */
	public static void setContentType(ContentType Type) {
		given().accept(Type);
	}

	/**
	 *** Returns response*** We send "path" as a parameter to the Rest Assured'a
	 * "get" method and "get" method returns response of API
	 */
	public static Response getResponse(String path) {
		return given().accept(ContentType.JSON).get(path);
	}

	/**
	 *** Returns JsonPath object*** First convert the API's response to String
	 * type with "asString()" method. Then, send this String formatted json
	 * response to the JsonPath class and return the JsonPath
	 */
	public static JsonPath getJsonPath(Response res) {
		String json = res.asString();
		return new JsonPath(json);
	}

}