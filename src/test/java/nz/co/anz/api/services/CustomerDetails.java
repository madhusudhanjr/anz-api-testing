package nz.co.anz.api.services;

import com.jayway.restassured.response.Response;

import nz.co.anz.utils.RestUtil;

/**
 * This Class contains all the customer centric operations and endpoints
 * 
 * @author jmadh
 *
 */
public class CustomerDetails {

	/**
	 * This method is used to GET customer details
	 * 
	 * @param path
	 * @return
	 */
	public Response getCustomerDetails(String path) {

		Response response = RestUtil.getResponse(path);

		return response;
	}

	/**
	 * This method is used to GET customer account details
	 * 
	 * @param path
	 * @return
	 */
	public Response getCustomerAccountDetails(String path) {

		Response response = RestUtil.getResponse(path);

		return response;
	}

	/**
	 * This method is used to GET customer position details
	 * 
	 * @param path
	 * @return
	 */
	public Response getCustomerPositionDetails(String path) {

		Response response = RestUtil.getResponse(path);

		return response;
	}

}
