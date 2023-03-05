package com.crm.customerservice.model;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CustomerDto {

	@NotBlank(message = "First Name cannot be left empty.")
	@Size(min = 3, max = 50, message = "Lenght of First Name must be between 3 and 50.")
	private String firstName;
	@NotBlank(message = "Last Name cannot be left empty.")
	@Size(min = 3, max = 50, message = "Lenght of Last Name must be between 3 and 50.")
	private String lastName;
	@Pattern(regexp = "MALE|FEMALE|OTHERS", message = "Gender can only accept 'MALE','FEMALE' or 'OTHERS'.")
	@NotBlank(message = "Gender cannot be left empty.")
	private String gender;
	@NotBlank(message = "Email cannot be left empty.")
	@Email(message = "Invalid Email.")
	private String email;
	@NotBlank(message = "Mobile Number cannot be left empty.")
	@Pattern(regexp = "([123456789][0-9]{9})", message = "Invalid mobile number.")
	private String phone;
	@NotBlank(message = "Designation cannot be left empty.")
	@Size(min = 5, max = 50, message = "Lenght of Designation must be between 5 and 50.")
	private String designation;

	@Valid
	private PermanentAddress permanentAddress;
	@Valid
	private CurrentAddress currentAddress;
	public CustomerDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CustomerDto(
			@NotBlank(message = "First Name cannot be left empty.") @Size(min = 3, max = 50, message = "Lenght of First Name must be between 3 and 50.") String firstName,
			@NotBlank(message = "Last Name cannot be left empty.") @Size(min = 3, max = 50, message = "Lenght of Last Name must be between 3 and 50.") String lastName,
			@Pattern(regexp = "MALE|FEMALE|OTHERS", message = "Gender can only accept 'MALE','FEMALE' or 'OTHERS'.") @NotBlank(message = "Gender cannot be left empty.") String gender,
			@NotBlank(message = "Email cannot be left empty.") @Email(message = "Invalid Email.") String email,
			@NotBlank(message = "Mobile Number cannot be left empty.") @Pattern(regexp = "([123456789][0-9]{9})", message = "Invalid mobile number.") String phone,
			@NotBlank(message = "Designation cannot be left empty.") @Size(min = 5, max = 50, message = "Lenght of Designation must be between 5 and 50.") String designation,
			@Valid PermanentAddress permanentAddress, @Valid CurrentAddress currentAddress) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
		this.designation = designation;
		this.permanentAddress = permanentAddress;
		this.currentAddress = currentAddress;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public PermanentAddress getPermanentAddress() {
		return permanentAddress;
	}
	public void setPermanentAddress(PermanentAddress permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
	public CurrentAddress getCurrentAddress() {
		return currentAddress;
	}
	public void setCurrentAddress(CurrentAddress currentAddress) {
		this.currentAddress = currentAddress;
	}
	
	

}
