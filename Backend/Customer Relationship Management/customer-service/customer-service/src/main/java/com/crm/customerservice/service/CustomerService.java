package com.crm.customerservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.crm.customerservice.exception.BadCustomerException;
import com.crm.customerservice.model.CustomerDao;
import com.crm.customerservice.model.CustomerDto;
import com.crm.customerservice.model.ManagerDao;
import com.crm.customerservice.repository.CustomerRepo;

@Service
public class CustomerService {
	@Autowired
	ManagersClient client;
	
	@Autowired
	ManagerService managerService;
	
	@Autowired
	CustomerRepo customerRepo;
	
	
	public CustomerDao saveCustomer(CustomerDto customer) throws BadCustomerException{
		String username=SecurityContextHolder.getContext().getAuthentication().getName();
		
		ResponseEntity<ManagerDao> manager = client.getManager();


		
		CustomerDao newCustomer=new CustomerDao();
		newCustomer.setFirstName(customer.getFirstName());
		newCustomer.setLastName(customer.getLastName());
		newCustomer.setGender(customer.getGender());
		newCustomer.setEmail(customer.getEmail());
		newCustomer.setPhone(customer.getPhone());
		newCustomer.setDesignation(customer.getDesignation());
		newCustomer.setPermanentAddress(customer.getPermanentAddress());
		newCustomer.setCurrentAddress(customer.getCurrentAddress());
		newCustomer.setManager(manager.getBody());
		
		managerService.saveCustomer(manager.getBody());
		
		return customerRepo.save(newCustomer);
	}
	
	
	public List<CustomerDao> getAll() throws BadCustomerException{
		//String username=SecurityContextHolder.getContext().getAuthentication().getName();
		ResponseEntity<ManagerDao> manager = client.getManager();
		Long managerId=manager.getBody().getManagerId();
		List<CustomerDao> customers=customerRepo.findAll().stream()
				.filter(c-> c.getManager().getManagerId()==managerId)
				.collect(Collectors.toList());
		if(customers.size()==0) {
			throw new BadCustomerException("No data available.");
		}
		return customers;
	}
	
	public CustomerDao getById(Long customerId) throws BadCustomerException{
		CustomerDao customer=getAll().stream()
				.filter(c-> c.getCustomerId()==customerId)
				.findFirst()
				.orElseThrow(()-> new BadCustomerException("No customer found with customer id "+customerId+"."));
		return customer;
	}
	
	public CustomerDao updateById(Long customerId,CustomerDto customer)throws BadCustomerException{
		ResponseEntity<ManagerDao> manager = client.getManager();

		CustomerDao newCustomer=getById(customerId);
		if(newCustomer==null) {
			throw new BadCustomerException("No customer found with customer id "+customerId+".");
		}
		newCustomer.setFirstName(customer.getFirstName());
		newCustomer.setLastName(customer.getLastName());
		newCustomer.setGender(customer.getGender());
		newCustomer.setEmail(customer.getEmail());
		newCustomer.setPhone(customer.getPhone());
		newCustomer.setDesignation(customer.getDesignation());
		newCustomer.setPermanentAddress(customer.getPermanentAddress());
		newCustomer.setCurrentAddress(customer.getCurrentAddress());
		newCustomer.setManager(manager.getBody());
		
		managerService.saveCustomer(manager.getBody());
		
		return customerRepo.save(newCustomer);
		
	}
	
	public Map<String, String> deleteById(Long customerId) throws BadCustomerException{
		CustomerDao customer=getById(customerId);
		if(customer==null) {
			throw new BadCustomerException("No customer found with customer id "+customerId+".");
		}
		customerRepo.deleteById(customerId);
		Map<String,String> map=new HashMap<>();
		map.put("message", "Customer with id "+customerId+" is deleted.");
		return map;
	}
	
	
	
}
