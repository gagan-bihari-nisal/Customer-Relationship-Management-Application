package com.crm.customerservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.crm.customerservice.model.ManagerDao;

@FeignClient("auth")
public interface ManagersClient {

	@GetMapping("/manager")
	public ResponseEntity<ManagerDao> getManager();

//	@GetMapping("/{username}/exists")
//	public boolean managerExists(@PathVariable("username") String username);	
}
