package com.crm.customerservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.customerservice.model.ManagerDao;
import com.crm.customerservice.repository.ManagerRepo;

@Service
public class ManagerService {

	@Autowired
	ManagersClient client;

	@Autowired
	ManagerRepo repo;

	public ManagerDao saveCustomer(ManagerDao manager) {
		return repo.save(manager);
	}
	
	

}
