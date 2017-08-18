package nz.co.anz.enums;

public enum CustomerData {

	ID("id"), FIRST_NAME("firstName"), LAST_NAME("lastName"), STREET("address.street"), CITY("address.city"), STATE("address.state"), ZIPCODE(
			"address.zipCode"), PHONENUMBER("phoneNumber"), SSN("ssn");

	private String data;

	CustomerData(String data) {
		this.data = data;
	}

	public String value() {
		return data;
	}
}
