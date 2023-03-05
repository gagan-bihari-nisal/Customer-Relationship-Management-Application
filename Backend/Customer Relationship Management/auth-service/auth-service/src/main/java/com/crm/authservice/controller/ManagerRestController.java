package com.crm.authservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crm.authservice.exception.BadManagerException;
import com.crm.authservice.filter.TokenGeneratorFilter;
import com.crm.authservice.model.JWTRequest;
import com.crm.authservice.model.JWTResponse;
import com.crm.authservice.model.ManagerDao;
import com.crm.authservice.model.ManagerDto;
import com.crm.authservice.service.ManagerService;

@RestController
public class ManagerRestController {

	@Autowired
	ManagerService managerService;

	@Autowired
	AuthenticationManager authentication;

	@Autowired
	TokenGeneratorFilter jwtUtil;

	@GetMapping("/hell")
	public String hello() {
		return "hello";
	}
	

	@GetMapping("/manager")
	public ResponseEntity<ManagerDao> getManager() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return ResponseEntity.ok(managerService.getManager(username));
	}

	@PostMapping("/register")
	public ResponseEntity<ManagerDao> registerManager(@RequestBody @Valid ManagerDto manager,
			BindingResult bindingResult) throws BadManagerException {
		if (bindingResult.hasErrors()) {
			throw new BadManagerException(bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage())
					.reduce((s1, s2) -> s1 + " " + s2).orElse(""));
		}
		System.out.println("============IN REGISTER==========");
		return ResponseEntity.ok(managerService.saveManager(manager));
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginManager(@RequestBody JWTRequest request) throws BadManagerException {
		System.out.println("\n\n\n\n\n\n============IN LOGIN==========\n\n\n\n\n\n");

		UserDetails userDetails = managerService.loadUserByUsername(request.getUsername());
		try {
			authentication.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),
					request.getPassword(), userDetails.getAuthorities()));
		} catch (Exception e) {
			throw new BadManagerException("Authentication Failed. Try Again.");
		}
		final String jwt = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JWTResponse(jwt));
	}

	@GetMapping("/{username}/exists")
	public boolean managerExists(@PathVariable("username") String username) {
		if (managerService.checkManagerExists(username)) {
			return true;
		}
		return false;
	}

	@PutMapping("/updateManager")
	public ResponseEntity<ManagerDao> updateProfile(@RequestBody @Valid ManagerDao manager, BindingResult bindingResult)
			throws BadManagerException {
		if (bindingResult.hasErrors()) {
			throw new BadManagerException(bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage())
					.reduce((s1, s2) -> s1 + " " + s2).orElse(""));
		}
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		ManagerDao newManager = managerService.getManager(username);
		newManager.setFirstName(manager.getFirstName());
		newManager.setLastName(manager.getLastName());
		newManager.setGender(manager.getGender());
		newManager.setPhone(manager.getPhone());
		return ResponseEntity.ok(managerService.updateManager(newManager));
	}

}
