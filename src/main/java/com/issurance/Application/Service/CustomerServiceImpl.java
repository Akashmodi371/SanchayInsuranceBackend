package com.issurance.Application.Service;
import java.util.Optional;

import org.hibernate.validator.constraints.ISBN;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.issurance.Application.entities.Customer;
import com.issurance.Application.entities.Role;
import com.issurance.Application.repository.CustomerRepo;
import com.issurance.Application.repository.RoleRepo;
import com.issurance.Application.repository.UserInfoRepo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService{
	
	
	private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private UserInfoRepo userInfoRepo;
	
	
	private PasswordEncoder passwordEncoder;

	
	@Transactional
	@Override
	public void registerCustomer(Customer customer) {
		Optional<Role> role=roleRepo.findById(1);
		
		Role role2=null;
		
		if(role.isPresent()) {
			role2=role.get();
			customer.getUserInfo().setRole(role2);
		}
		else {
			customer.getUserInfo().setRole(null);
		}
		
		String pass=customer.getUserInfo().getPassword();
		
		customer.getUserInfo().setPassword(passwordEncoder.encode(pass));
		
		customerRepo.save(customer);
		
		log.info("customer"+customer+"is added Successfully");	
	}
	
	
	

}
