package com.crm.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.authservice.model.ManagerDao;


@Repository
public interface ManagerRepository extends JpaRepository<ManagerDao, Long> {
	public ManagerDao findByUsername(String username);

	public boolean existsByUsername(String username);
}
