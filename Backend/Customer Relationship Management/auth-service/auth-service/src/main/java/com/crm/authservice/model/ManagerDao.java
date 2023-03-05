package com.crm.authservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "managers", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
public class ManagerDao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long managerId;

	private String role;

	private String firstName;

	private String lastName;

	private String gender;

	private String phone;

	// credentials

	private String username;

	private String password;

	public ManagerDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ManagerDao(Long managerId, String role, String firstName, String lastName, String gender, String phone,
			String username, String password) {
		super();
		this.managerId = managerId;
		this.role = role;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.phone = phone;
		this.username = username;
		this.password = password;
	}

	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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
