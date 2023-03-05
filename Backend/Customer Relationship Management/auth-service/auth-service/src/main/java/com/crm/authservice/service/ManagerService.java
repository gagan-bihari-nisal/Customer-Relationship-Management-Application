package com.crm.authservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.crm.authservice.exception.BadManagerException;
import com.crm.authservice.model.ManagerDao;
import com.crm.authservice.model.ManagerDto;
import com.crm.authservice.repository.ManagerRepository;

@Service
public class ManagerService implements UserDetailsService{

	@Autowired
	ManagerRepository managerRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		ManagerDao manager = managerRepo.findByUsername(username);
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		if (null != manager) {
			authorities.add(new SimpleGrantedAuthority(manager.getRole()));
			return new User(manager.getUsername(), manager.getPassword(), authorities);
		} else {
			throw new UsernameNotFoundException(username + " not found in our records.");
		}
	}
	
	
	
	
	public ManagerDao getManager(String username) {
		return managerRepo.findByUsername(username);
	}
	
	
	
	public boolean checkManagerExists(String username) {
		ManagerDao manager = managerRepo.findByUsername(username);
		if(manager==null) {
			return false;
		}else {
			return true;
		}
	}

	public ManagerDao saveManager(ManagerDto manager) throws BadManagerException {
		if(checkManagerExists(manager.getUsername())==true) {
			throw new BadManagerException(manager.getUsername()+" already exists in our records. Try with a different username");
		}
		ManagerDao newManager = new ManagerDao();
		newManager.setRole("MANAGER");
		newManager.setFirstName(manager.getFirstName());
		newManager.setLastName(manager.getLastName());
		newManager.setGender(manager.getGender());
		
		newManager.setPhone(manager.getPhone());
		newManager.setUsername(manager.getUsername());
		newManager.setPassword(passwordEncoder.encode(manager.getPassword()));		
		return managerRepo.save(newManager);
	}
	
	public ManagerDao updateManager(ManagerDao manager) {
		return managerRepo.save(manager);
	}

	
	
	
	

}
