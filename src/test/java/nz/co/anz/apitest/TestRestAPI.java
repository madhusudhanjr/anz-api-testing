package nz.co.anz.apitest;

import static com.jayway.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.xml.XmlPath;
import com.jayway.restassured.path.xml.element.Node;
import com.jayway.restassured.response.Response;

public class TestRestAPI {

	@BeforeClass
	public void setBaseUri() {

		RestAssured.baseURI = "http://parabank.parasoft.com/parabank/services/bank";
	}

	@Test
	public void getCustomerDetails() {

		Response res = given().contentType(ContentType.JSON).pathParam("customerId", "12212").when()
				.get("/customers/{customerId}");

		/**
		 * Retrieve Response Data
		 */
		XmlPath xmlPath = new XmlPath(res.asString()).setRoot("customer");
		String customerId = xmlPath.getString("id");
		String firstName = xmlPath.getString("firstName");
		String lastName = xmlPath.getString("lastName");
		String street = xmlPath.getString("street");
		String city = xmlPath.getString("city");
		String state = xmlPath.getString("state");
		String zipCode = xmlPath.getString("zipCode");
		String phoneNumber = xmlPath.getString("phoneNumber");
		String ssn = xmlPath.getString("ssn");

		/**
		 * Assert Response Data
		 */
		assertTrue((res.statusCode() == 200), "Status Code mismatch!!");
		assertTrue(customerId.equals("12212"), "CustomerID mismatch!!");
		assertTrue(firstName.equals("John"), "First Name mismatch!!");
		assertTrue(lastName.equals("Smith"), "Last Name mismatch!!");
		assertTrue(street.equals("1431 Main St"), "Street mismatch!!");
		assertTrue(city.equals("Beverly Hills"), "City mismatch!!");
		assertTrue(state.equals("CA"), "State mismatch!!");
		assertTrue(zipCode.equals("90210"), "Zipcode mismatch!!");
		assertTrue(phoneNumber.equals("310-447-4121"), "Phone Number mismatch!!");
		assertTrue(ssn.equals("622-11-9999"), "SSN mismatch!!");

	}

	@Test
	public void getCustomerAccountDetails() {

		Response res = given().contentType(ContentType.JSON).pathParam("customerId", "12212").when()
				.get("/customers/{customerId}/accounts");

		/**
		 * Retrieve Response Data
		 */
		List<Node> accountDetails = new XmlPath(res.asString()).getList("accounts.account", Node.class);

		assertTrue((accountDetails.size() > 0), "No Account Details Found!!");
		
		for (Node account : accountDetails) {

			String accountId = account.getNodes("id").toString();
			String accountType = account.getNodes("type").toString();
			String accountBalance = account.getNodes("balance").toString();
			String customerId = account.getNodes("customerId").toString();
			
			assertTrue(!accountId.isEmpty(), "Account ID is Null / Empty!!");
			assertTrue(!accountType.isEmpty(), "Account ID is Null / Empty!!");
			assertTrue(!accountBalance.isEmpty(), "Account ID is Null / Empty!!");
			assertTrue(customerId.contains("12212"), "Customer ID mismatch!!");

		}

	}

	@Test
	public void getCustomerPositionDetails() {

		Response res = given().contentType(ContentType.JSON).pathParam("customerId", "12212").when()
				.get("/customers/{customerId}/positions");

		/**
		 * Retrieve Response Data
		 */
		List<Node> positionDetails = new XmlPath(res.asString()).getList("positions.position", Node.class);

		assertTrue((positionDetails.size() > 0), "No Position Details Found!!");
		
		for (Node position : positionDetails) {

			String positionId = position.getNodes("positionId").toString();
			String name = position.getNodes("name").toString();
			String symbol = position.getNodes("symbol").toString();
			String shares = position.getNodes("shares").toString();
			String purchasePrice = position.getNodes("purchasePrice").toString();
			String customerId = position.getNodes("customerId").toString();
			
			assertTrue(!positionId.isEmpty(), "Position ID is Null / Empty!!");
			assertTrue(!name.isEmpty(), "Name is Null / Empty!!");
			assertTrue(!symbol.isEmpty(), "Symbol is Null / Empty!!");
			assertTrue(!shares.isEmpty(), "Shares is Null / Empty!!");
			assertTrue(!purchasePrice.isEmpty(), "Purchase Price is Null / Empty!!");
			assertTrue(customerId.contains("12212"), "Customer ID mismatch!!");

		}

	}

}
