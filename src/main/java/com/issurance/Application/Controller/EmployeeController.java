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

import com.issurance.Application.Service.EmployeeService;
import com.issurance.Application.entities.Customer;
import com.issurance.Application.entities.Employee;
import com.issurance.Application.repository.EmployeeRepo;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("employeeapp")
@Slf4j
public class EmployeeController {

	
	private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
		//Register Employees
		@PostMapping("/register")
		public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
			System.out.println("Employee Here!!");
			
			employeeService.registeremployee(employee);
			
			return new ResponseEntity<>("Employee Added Successfully",HttpStatus.OK);
		}
		
		
		//Get All Employees
		@GetMapping("/employees")
		public ResponseEntity<List<Employee>> getEmployees(){
			
			List<Employee> employees=employeeRepo.findAll();
			
			return new ResponseEntity<>(employees,HttpStatus.OK);
		}
	
	
}
