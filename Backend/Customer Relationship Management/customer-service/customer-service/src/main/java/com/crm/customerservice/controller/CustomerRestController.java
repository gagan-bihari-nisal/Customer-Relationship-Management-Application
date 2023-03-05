package com.crm.customerservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crm.customerservice.exception.BadCustomerException;
import com.crm.customerservice.model.CustomerDao;
import com.crm.customerservice.model.CustomerDto;
import com.crm.customerservice.service.CustomerService;

@RestController
public class CustomerRestController {
	@Autowired
	CustomerService customerService;
	
	
	@GetMapping("/hello")
	public String hello(){
		return "hello customer service";
	}

	@PostMapping("/create")
	public ResponseEntity<CustomerDao> create(@RequestBody @Valid CustomerDto customer, BindingResult bindingResults)
			throws Exception {
		if (bindingResults.hasErrors()) {
			throw new BadCustomerException(bindingResults.getAllErrors().stream().map(e -> e.getDefaultMessage())
					.reduce((s1, s2) -> s1 + " " + s2).orElse(""));
		}

		return ResponseEntity.ok(customerService.saveCustomer(customer));
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<CustomerDao>> getAll() throws BadCustomerException {
		return ResponseEntity.ok(customerService.getAll());
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<CustomerDao> getById(@PathVariable("id") Long id) throws BadCustomerException {
		return ResponseEntity.ok(customerService.getById(id));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<CustomerDao> updateById(@PathVariable("id") Long id, @RequestBody @Valid CustomerDto customer,
			BindingResult bindingResult) throws BadCustomerException {
		if (bindingResult.hasErrors()) {
			throw new BadCustomerException(bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage())
					.reduce((s1, s2) -> s1 + " " + s2).orElse(""));
		}
		return ResponseEntity.ok(customerService.updateById(id,customer));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id)throws BadCustomerException{
		return ResponseEntity.ok(customerService.deleteById(id));
	}

}
