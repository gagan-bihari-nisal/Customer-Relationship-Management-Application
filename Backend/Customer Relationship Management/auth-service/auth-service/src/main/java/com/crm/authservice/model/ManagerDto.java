package com.crm.authservice.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ManagerDto {

	@NotBlank(message = "First Name cannot be left empty.")
	@Size(min = 3, max = 50, message = "Lenght of First Name must be between 3 and 50.")
	private String firstName;
	@NotBlank(message = "Last Name cannot be left empty.")
	@Size(min = 3, max = 50, message = "Lenght of Last Name must be between 3 and 50.")
	private String lastName;
	@Pattern(regexp = "MALE|FEMALE|OTHERS", message = "Gender can only accept 'MALE','FEMALE' or 'OTHERS'.")
	@NotBlank(message = "Gender cannot be left empty.")
	private String gender;
	@NotBlank(message = "Mobile Number cannot be left empty.")
	@Pattern(regexp = "([123456789][0-9]{9})", message = "Invalid mobile number.")
	private String phone;

	@NotBlank(message = "Username cannot be left empty.")
	@Size(min = 3, max = 50, message = "Lenght of User Name must be between 3 and 50.")
	private String username;
	@NotBlank(message = "Password cannot be left empty.")
	private String password;

	public ManagerDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public ManagerDto(
			@NotBlank(message = "First Name cannot be left empty.") @Size(min = 3, max = 50, message = "Lenght of First Name must be between 3 and 50.") String firstName,
			@NotBlank(message = "Last Name cannot be left empty.") @Size(min = 3, max = 50, message = "Lenght of Last Name must be between 3 and 50.") String lastName,
			@Pattern(regexp = "MALE|FEMALE|OTHERS", message = "Gender can only accept 'MALE','FEMALE' or 'OTHERS'.") @NotBlank(message = "Gender cannot be left empty.") String gender,
			@NotBlank(message = "Mobile Number cannot be left empty.") @Pattern(regexp = "([123456789][0-9]{9})", message = "Invalid mobile number.") String phone,
			@NotBlank(message = "Username cannot be left empty.") @Size(min = 3, max = 50, message = "Lenght of User Name must be between 3 and 50.") String username,
			@NotBlank(message = "Password cannot be left empty.") String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.phone = phone;
		this.username = username;
		this.password = password;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
