package com.issurance.Application.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.issurance.Application.entities.Policy;


public interface PolicyRepo extends JpaRepository<Policy, Integer> {

	
	List<Policy> findByCustomerCustomerid(int customerid);
}
