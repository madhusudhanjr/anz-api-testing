package nz.co.anz.api.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

import nz.co.anz.api.services.CustomerDetails;
import nz.co.anz.api.sources.Account;
import nz.co.anz.api.sources.Position;
import nz.co.anz.enums.CustomerData;
import nz.co.anz.utils.RestUtil;

public class CustomerDetailsTest extends BaseTest {

	/**
	 * Class variable which holds the reference to the Logger Object
	 */
	static Logger logger = Logger.getLogger(CustomerDetailsTest.class);

	/**
	 * This method is executed before Class to initialize API Services
	 */
	@BeforeClass(alwaysRun = true)
	public void setup() {

		logger.info("Enter @Before CLass");
		m_customerDetails = new CustomerDetails();
	}

	/**
	 * Test Scenarios: GET Customer Details
	 * 
	 * @param customerID
	 */
	@Parameters({ "customerID" })
	@Test()
	public void getCustomerDetails(String customerID) {

		logger.info("Enter Test Method");
		
		/**
		 * Test Data
		 */
		String path = "/customers/{customerId}";
		path = String.format("/customers/%s", customerID);

		Response response = m_customerDetails.getCustomerDetails(path);
		assertTrue((response.statusCode() == 200), "Status Code mismatch!!");
		logger.info("Response Status Code:: " + response.statusCode());
		
		/**
		 * Retrieve Response Data
		 */
		JsonPath jsonPath = RestUtil.getJsonPath(response).setRoot("customer");

		String customerId = jsonPath.getString(CustomerData.ID.value());
		String firstName = jsonPath.getString(CustomerData.FIRST_NAME.value());
		String lastName = jsonPath.getString(CustomerData.LAST_NAME.value());
		String street = jsonPath.getString(CustomerData.STREET.value());
		String city = jsonPath.getString(CustomerData.CITY.value());
		String state = jsonPath.getString(CustomerData.STATE.value());
		String zipCode = jsonPath.getString(CustomerData.ZIPCODE.value());
		String phoneNumber = jsonPath.getString(CustomerData.PHONENUMBER.value());
		String ssn = jsonPath.getString(CustomerData.SSN.value());

		/**
		 * Assert Response Data
		 */
		logger.info("*****Verify Response Details*****");
		
		assertTrue(customerId.equals(customerID), "CustomerID mismatch!!");
		logger.info("CustomerID :: " + customerId);
		
		assertTrue(firstName.equals("John"), "First Name mismatch!!");
		logger.info("FirstName :: " + firstName);
		
		assertTrue(lastName.equals("Smith"), "Last Name mismatch!!");
		logger.info("LastName :: " + lastName);
		
		assertTrue(street.equals("1431 Main St"), "Street mismatch!!");
		logger.info("Street :: " + street);
		
		assertTrue(city.equals("Beverly Hills"), "City mismatch!!");
		logger.info("City :: " + city);
		
		assertTrue(state.equals("CA"), "State mismatch!!");
		logger.info("State :: " + state);
		
		assertTrue(zipCode.equals("90210"), "Zipcode mismatch!!");
		logger.info("ZipCode :: " + zipCode);
		
		assertTrue(phoneNumber.equals("310-447-4121"), "Phone Number mismatch!!");
		logger.info("PhoneNumber :: " + phoneNumber);
		
		assertTrue(ssn.equals("622-11-9999"), "SSN mismatch!!");
		logger.info("SSN :: " + ssn);
		
		logger.info("*****Response Details Verified Successfully!!*****");
	}

	/**
	 * Test Scenarios: GET Customer Account Details
	 * 
	 * @param customerID
	 */
	@Parameters({ "customerID" })
	@Test()
	public void getCustomerAccountDetails(String customerID) {

		logger.info("Start Test Method");
		
		/**
		 * Test Data
		 */
		String path = "/customers/{customerId}/accounts";
		path = String.format("/customers/%s/accounts", customerID);

		Response response = m_customerDetails.getCustomerDetails(path);
		assertTrue((response.statusCode() == 200), "Status Code mismatch!!");
		logger.info("Response Status Code:: " + response.statusCode());
		
		/**
		 * Retrieve Response Data
		 */
		JSONObject data = new JSONObject(response.asString());
		JSONArray accounts = data.getJSONArray("account");
		List<Account> accountDetails = new Gson().fromJson(accounts.toString(), new TypeToken<ArrayList<Account>>() {
		}.getType());

		assertTrue((accountDetails.size() > 0), "No Account Details Found!!");
		logger.info("Account Details Size:: " + accountDetails.size());
		
		int i =1;
		logger.info("*****Verify Response Details*****");
		for (Account accountData : accountDetails) {

			logger.info("Verify AccountDetails Iteration :: " + i);
			
			assertEquals(accountData.getCustomerId(), customerID, "Customer ID mismatch!!");
			logger.info("CustomerID :: " + accountData.getCustomerId());
			
			assertTrue(!accountData.getId().isEmpty(), "Account ID is Null / Empty!!");
			logger.info("Account ID :: " + accountData.getId());
			
			assertTrue(!accountData.getBalance().isEmpty(), "Account Balance is Null!!");
			logger.info("Accont Balance :: " + accountData.getBalance());
			
			assertTrue(!accountData.getType().isEmpty(), "Account Type is Null / Emtpy!!");
			logger.info("Account Type :: " + accountData.getType());
			
			i++;
		}

		logger.info("*****Response Details Verified Successfully!!*****");
	}

	/**
	 * Test Scenarios: GET Customer Position Details
	 * 
	 * @param customerID
	 */
	@Parameters({ "customerID" })
	@Test()
	public void getCustomerPositionDetails(String customerID) {

		logger.info("Start Test Method");
		
		/**
		 * Test Data
		 */
		String path = "/customers/{customerId}/positions";
		path = String.format("/customers/%s/positions", customerID);

		Response response = m_customerDetails.getCustomerDetails(path);
		assertTrue((response.statusCode() == 200), "Status Code mismatch!!");
		logger.info("Response Status Code:: " + response.statusCode());
		
		/**
		 * Retrieve Response Data
		 */
		JSONObject data = new JSONObject(response.asString());
		JSONArray accounts = data.getJSONArray("position");
		List<Position> positionDetails = new Gson().fromJson(accounts.toString(), new TypeToken<ArrayList<Position>>() {
		}.getType());

		assertTrue((positionDetails.size() > 0), "No Position Details Found!!");
		logger.info("Account Details Size:: " + positionDetails.size());
		
		int i =1;
		logger.info("*****Verify Response Details*****");
		for (Position position : positionDetails) {

			logger.info("Verify PositionDetails Iteration :: " + i);
			
			assertEquals(position.getCustomerId(), customerID, "Customer ID mismatch!!");
			logger.info("Customer ID :: " + position.getCustomerId());
			
			assertTrue(!position.getPositionId().isEmpty(), "Position ID is Null / Empty!!");
			logger.info("Position ID :: " + position.getPositionId());
			
			assertTrue(!position.getName().isEmpty(), "Name is Null / Empty!!");
			logger.info("Position Name :: " + position.getName());
			
			assertTrue(!position.getSymbol().isEmpty(), "Symbol is Null / Empty!!");
			logger.info("Position Symbol :: " + position.getSymbol());
			
			assertTrue(!position.getShares().isEmpty(), "Shares is Null / Empty!!");
			logger.info("Position Shares :: " + position.getShares());
			
			assertTrue(!position.getPurchasePrice().isEmpty(), "Purchase Price is Null / Empty!!");
			logger.info("Purchase Price :: " + position.getPurchasePrice());

		}

		logger.info("*****Response Details Verified Successfully!!*****");
	}

}
