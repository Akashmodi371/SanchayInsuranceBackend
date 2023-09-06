package com.issurance.Application.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.issurance.Application.Service.AdminService;
import com.issurance.Application.Service.EmployeeService;
import com.issurance.Application.entities.Admin;
import com.issurance.Application.entities.Employee;
import com.issurance.Application.repository.AdminRepo;
import com.issurance.Application.repository.EmployeeRepo;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("adminapp")
@Slf4j
public class AdminController {
	
	private static final Logger log = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private AdminRepo adminRepo;
	
	
//Register admin
		@PostMapping("/register")
		public ResponseEntity<String> addAdmin(@RequestBody Admin admin) {
			System.out.println("Admin Here!!");
			
			adminService.registerAdmins(admin);
			
			return new ResponseEntity<>("Admin Added Successfully",HttpStatus.OK);
		}
		
		
//Get All Admins
		@GetMapping("/admins")
		public ResponseEntity<List<Admin>> getEmployees(){
			
			List<Admin> admins=adminRepo.findAll();
			
			return new ResponseEntity<>(admins,HttpStatus.OK);
		}

}
