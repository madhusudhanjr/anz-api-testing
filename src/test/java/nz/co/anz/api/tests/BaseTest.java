package nz.co.anz.api.tests;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.jayway.restassured.http.ContentType;

import nz.co.anz.api.services.CustomerDetails;
import nz.co.anz.utils.RestUtil;

/**
 * This Class holds the common functions which are executed before / after test
 * execution
 * 
 * @author mjr
 */
public class BaseTest {

	/**
	 * Class variable which holds the reference to the Logger Object
	 */
	static Logger logger = Logger.getLogger(BaseTest.class);

	/**
	 * Class variable reference to Page Objects
	 */
	public CustomerDetails m_customerDetails = null;

	/**
	 * Executes before suite
	 * 
	 * @param method
	 */
	@BeforeSuite(alwaysRun = true)
	public void initialize() {

		logger.info("Enter @BeforeSuite");
		PropertyConfigurator.configure("src/test/resources/Logger/log4j.properties");
	}

	/**
	 * Executes before every Test
	 * 
	 * @param method
	 */
	@Parameters({ "baseURI" })
	@BeforeTest(alwaysRun = true)
	public void setUp(String baseURI) {

		logger.info("Enter @BeforeTest");

		RestUtil.setBaseURI(baseURI);
		RestUtil.setContentType(ContentType.JSON);
	}

	/**
	 * Executes before every Test Method
	 * 
	 * @param method
	 */
	@BeforeMethod(alwaysRun = true)
	public void start(Method method) {

		logger.info("\n\n******START OF TEST CASE:: " + method.getName() + "******\n");
	}

	/**
	 * Executes after every Test Method
	 * 
	 * @param method
	 */
	@AfterMethod(alwaysRun = true)
	public void end(Method method) {

		logger.info("\n\n******END OF TEST CASE:: " + method.getName() + "******\n");
	}

	/**
	 * Executes after every Test
	 * 
	 * @param method
	 */
	@AfterTest(alwaysRun = true)
	public void shutDown() {

		logger.info("Enter @AfterTest");
		RestUtil.resetBaseURI();
	}
}
