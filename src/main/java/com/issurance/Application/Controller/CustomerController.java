package com.issurance.Application.Controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.issurance.Application.Service.CustomerService;
import com.issurance.Application.entities.Customer;
import com.issurance.Application.entities.UserInfo;
import com.issurance.Application.repository.CustomerRepo;
import com.issurance.Application.repository.UserInfoRepo;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("customerapp")
@Slf4j
public class CustomerController {

	
	
	private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserInfoRepo userInfoRepo;
	
	
	
	//Register Customer
	@PostMapping("/register")
	public ResponseEntity<String>  addCustomer(@RequestBody Customer customer) {
		System.out.println("Customer Here!!");
		
		customerService.registerCustomer(customer);
		
		return new ResponseEntity<>("Customer Added Successfully",HttpStatus.OK);
	}
	
	
	//Get Customer By Id
	@GetMapping("/customer")
	public ResponseEntity<Customer> getCustomer(@RequestParam(name="accessid") int cid){
		
		Optional<Customer> customer=customerRepo.findById(cid);
		Customer customer2=null;
		
		if(customer.isPresent()) {
			customer2=customer.get();
			return new ResponseEntity<>(customer2,HttpStatus.OK);
		}
		return new ResponseEntity<>(customer2,HttpStatus.NOT_FOUND);
	}
	
	//Get All Customer
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getCustomers(){
		
		List<Customer> customers=customerRepo.findAll();
		
		return new ResponseEntity<>(customers,HttpStatus.OK);
	}
	
	//Update Customer
	
	@PostMapping("/update/{customerid}")
	public ResponseEntity<String> updateCustomer(@PathVariable(name = "customerid") Integer cid ,@RequestBody Customer updatedCustomer){
		
		Optional<Customer> existingCustomer=customerRepo.findById(cid);
		
		if (existingCustomer == null) {
	        return ResponseEntity.notFound().build();
	    }
		
		updatedCustomer.setCustomerid(cid);
		ReflectionUtils.copyNonNullFields(updatedCustomer, existingCustomer.get());
		
		Customer savedCustomer = customerRepo.save(existingCustomer.get());
		
		return new ResponseEntity<>("Updated Successfully",HttpStatus.OK);
	}
	
	//Change Password
	@PostMapping("/changepassword/{customerid}/{oldpassword}/{newpassword}")
	public ResponseEntity<String> changePassword(@PathVariable(name = "customerid") Integer cid, 
			@PathVariable(name = "oldpassword") String oldpass, @PathVariable(name = "newpassword") String newpass){
		
		Optional<Customer> existingCustomer=customerRepo.findById(cid);
		
		if (existingCustomer == null) {
			return new ResponseEntity<>("Not exists",HttpStatus.NOT_FOUND);
	    }
		UserInfo userInfo=existingCustomer.get().getUserInfo();
		
		if(passwordEncoder.matches(oldpass, userInfo.getPassword())) {
			userInfo.setPassword(passwordEncoder.encode(newpass));
			userInfoRepo.save(userInfo);
			return new ResponseEntity<>("Password changed Successfully",HttpStatus.OK);
		}
		
		return new ResponseEntity<>("Password Not match",HttpStatus.NOT_ACCEPTABLE);
	}
	
	
	
	
}
