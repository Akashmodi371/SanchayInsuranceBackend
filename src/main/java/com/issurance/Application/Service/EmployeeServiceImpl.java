package com.issurance.Application.Service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.issurance.Application.entities.Employee;
import com.issurance.Application.entities.Role;
import com.issurance.Application.repository.CustomerRepo;
import com.issurance.Application.repository.EmployeeRepo;
import com.issurance.Application.repository.RoleRepo;
import com.issurance.Application.repository.UserInfoRepo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@AllArgsConstructor
@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService{

	
	private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private UserInfoRepo userInfoRepo;
	
	
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	@Override
	public void registeremployee(Employee employee) {
		
		Optional<Role> role=roleRepo.findById(4);
		
		Role role2=null;
		
		if(role.isPresent()) {
			role2=role.get();
			employee.getUserInfo().setRole(role2);
		}
		else {
			employee.getUserInfo().setRole(null);
		}
		
		String pass=employee.getUserInfo().getPassword();
		
		employee.getUserInfo().setPassword(passwordEncoder.encode(pass));
		
		employeeRepo.save(employee);
		
		log.info("customer"+employee+"is added Successfully");
		
	}

}
