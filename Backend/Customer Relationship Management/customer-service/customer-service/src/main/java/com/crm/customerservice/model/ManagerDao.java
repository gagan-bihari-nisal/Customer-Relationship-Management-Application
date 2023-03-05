package com.crm.customerservice.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "managers")
public class ManagerDao {
	@Id
	private Long managerId;
	
	private String username;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customerId")
	private Set<CustomerDao> customers;

	public ManagerDao() {
		super();
	}

	public ManagerDao(Long managerId, String username, Set<CustomerDao> customers) {
		super();
		this.managerId = managerId;
		this.username = username;
		this.customers = customers;
	}

	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<CustomerDao> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<CustomerDao> customers) {
		this.customers = customers;
	}
	
	

	
}
