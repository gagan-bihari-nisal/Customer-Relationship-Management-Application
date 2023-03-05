package com.crm.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.customerservice.model.ManagerDao;

public interface ManagerRepo extends JpaRepository<ManagerDao,Long>{

}
