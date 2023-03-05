package com.crm.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.customerservice.model.CustomerDao;

public interface CustomerRepo extends JpaRepository<CustomerDao,Long>{

}
