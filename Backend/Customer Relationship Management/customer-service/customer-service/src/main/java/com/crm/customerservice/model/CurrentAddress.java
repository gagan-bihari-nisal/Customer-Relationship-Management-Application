package com.crm.customerservice.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Embeddable
public class CurrentAddress {
	@NotBlank(message = "Current State name cannot be left empty.")
	@Size(min = 3, max = 50, message = "Lenght of Current State name must be between 3 and 50.")
	private String current_state;
	@NotBlank(message = "Current City name cannot be left empty.")
	@Size(min = 3, max = 50, message = "Lenght of Current City name must be between 3 and 50.")
	private String current_city;
	@NotBlank(message = "Current District name cannot be left empty.")
	@Size(min = 3, max = 50, message = "Lenght of Current District name must be between 3 and 50.")
	private String current_district;
	@NotBlank(message = "Current Pincode cannot be left empty.")
	@Pattern(regexp = "([123456789][0-9]{5})", message = "Invalid Current pincode.")
	private String current_pincode;
	@NotBlank(message = "Current Address Line cannot be left empty.")
	@Size(min = 10, max = 50, message = "Lenght of Current Address Line must be between 10 and 50.")
	private String current_addressLine;
	
	
	public CurrentAddress() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CurrentAddress(
			@NotBlank(message = "Current State name cannot be left empty.") @Size(min = 3, max = 50, message = "Lenght of Current State name must be between 3 and 50.") String current_state,
			@NotBlank(message = "Current City name cannot be left empty.") @Size(min = 3, max = 50, message = "Lenght of Current City name must be between 3 and 50.") String current_city,
			@NotBlank(message = "Current District name cannot be left empty.") @Size(min = 3, max = 50, message = "Lenght of Current District name must be between 3 and 50.") String current_district,
			@NotBlank(message = "Current Pincode cannot be left empty.") @Pattern(regexp = "([123456789][0-9]{5})", message = "Invalid Current pincode.") String current_pincode,
			@NotBlank(message = "Current Address Line cannot be left empty.") @Size(min = 10, max = 50, message = "Lenght of Current Address Line must be between 10 and 50.") String current_addressLine) {
		super();
		this.current_state = current_state;
		this.current_city = current_city;
		this.current_district = current_district;
		this.current_pincode = current_pincode;
		this.current_addressLine = current_addressLine;
	}


	public String getCurrent_state() {
		return current_state;
	}


	public void setCurrent_state(String current_state) {
		this.current_state = current_state;
	}


	public String getCurrent_city() {
		return current_city;
	}


	public void setCurrent_city(String current_city) {
		this.current_city = current_city;
	}


	public String getCurrent_district() {
		return current_district;
	}


	public void setCurrent_district(String current_district) {
		this.current_district = current_district;
	}


	public String getCurrent_pincode() {
		return current_pincode;
	}


	public void setCurrent_pincode(String current_pincode) {
		this.current_pincode = current_pincode;
	}


	public String getCurrent_addressLine() {
		return current_addressLine;
	}


	public void setCurrent_addressLine(String current_addressLine) {
		this.current_addressLine = current_addressLine;
	}
	
	
	
	
}
