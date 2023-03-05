package com.crm.customerservice.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name="customers")
public class CustomerDao {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long customerId;
	
	private String firstName;
	private String lastName;
	private String gender;
	private String email;
	private String phone;
	private String designation;
	
	@Embedded
	private PermanentAddress permanentAddress;
	@Embedded 
	private CurrentAddress currentAddress; 
	
	
	@ManyToOne(targetEntity=ManagerDao.class)
	@JoinColumn(name="managerId")
	@JsonProperty(access = Access.WRITE_ONLY)
	private ManagerDao manager;
	
	public CustomerDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerDao(Long customerId, String firstName, String lastName, String gender, String email, String phone,
			String designation, PermanentAddress permanentAddress, CurrentAddress currentAddress, ManagerDao manager) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
		this.designation = designation;
		this.permanentAddress = permanentAddress;
		this.currentAddress = currentAddress;
		this.manager = manager;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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

	public ManagerDao getManager() {
		return manager;
	}

	public void setManager(ManagerDao manager) {
		this.manager = manager;
	}
	
}
