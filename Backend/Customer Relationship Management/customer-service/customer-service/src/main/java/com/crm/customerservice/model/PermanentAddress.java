package com.crm.customerservice.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Embeddable
public class PermanentAddress {
	@NotBlank(message = "State name cannot be left empty.")
	@Size(min = 3, max = 50, message = "Lenght of State name must be between 3 and 50.")
	private String state;
	@NotBlank(message = "City name cannot be left empty.")
	@Size(min = 3, max = 50, message = "Lenght of City name must be between 3 and 50.")
	private String city;
	@NotBlank(message = "District name cannot be left empty.")
	@Size(min = 3, max = 50, message = "Lenght of District name must be between 3 and 50.")
	private String district;
	@NotBlank(message = "Pincode cannot be left empty.")
	@Pattern(regexp = "([123456789][0-9]{5})", message = "Invalid pincode.")
	private String pincode;
	@NotBlank(message = "Address Line cannot be left empty.")
	@Size(min = 10, max = 50, message = "Lenght of Address Line must be between 10 and 50.")
	private String addressLine;
	public PermanentAddress() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PermanentAddress(
			@NotBlank(message = "State name cannot be left empty.") @Size(min = 3, max = 50, message = "Lenght of State name must be between 3 and 50.") String state,
			@NotBlank(message = "City name cannot be left empty.") @Size(min = 3, max = 50, message = "Lenght of City name must be between 3 and 50.") String city,
			@NotBlank(message = "District name cannot be left empty.") @Size(min = 3, max = 50, message = "Lenght of District name must be between 3 and 50.") String district,
			@NotBlank(message = "Pincode cannot be left empty.") @Pattern(regexp = "([123456789][0-9]{5})", message = "Invalid pincode.") String pincode,
			@NotBlank(message = "Address Line cannot be left empty.") @Size(min = 10, max = 50, message = "Lenght of Address Line must be between 10 and 50.") String addressLine) {
		super();
		this.state = state;
		this.city = city;
		this.district = district;
		this.pincode = pincode;
		this.addressLine = addressLine;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getAddressLine() {
		return addressLine;
	}
	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}
	
	

}
