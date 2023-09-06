package com.issurance.Application.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.data.domain.Pageable;
import com.issurance.Application.entities.Customer;
import com.issurance.Application.entities.Plan;
import com.issurance.Application.entities.Policy;
import com.issurance.Application.entities.Scheme;
import com.issurance.Application.exceptions.PolicyNotFoundException;
import com.issurance.Application.repository.CustomerRepo;
import com.issurance.Application.repository.EmployeeRepo;
import com.issurance.Application.repository.PlanRepo;
import com.issurance.Application.repository.PolicyRepo;
import com.issurance.Application.repository.RoleRepo;
import com.issurance.Application.repository.SchemeRepo;
import com.issurance.Application.repository.UserInfoRepo;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@AllArgsConstructor
//@RequiredArgsConstructor
@Slf4j
@Service
public class PolicyServiceImpl implements PolicyService{
	
	
	@Autowired
	private PlanRepo planRepo;


	@Autowired
	private PolicyRepo policyRepo;

	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private SchemeRepo schemeRepo;

	@Override
	public ResponseEntity<String> addPolicy(Policy policy, Integer customerid, Integer schemeid) {
		
		Optional<Customer> customer=customerRepo.findById(customerid);
		Optional<Scheme> scheme=schemeRepo.findById(schemeid);
		
		Customer customer2=null;
		Scheme scheme2=null;
		
		if(customer.isPresent() && scheme.isPresent()) {
			customer2=customer.get();
			scheme2=scheme.get();
			policy.setCustomer(customer2);
			policy.setScheme(scheme2);
			policyRepo.save(policy);
			
			return new ResponseEntity("Added Policy Successfully",HttpStatus.OK);
		}
		
		return new ResponseEntity("Incorrect customerid or schemeid",HttpStatus.OK);
	}
	
	//get policies by customerid in pages
		@Override
		public Page<Policy> getallinpage(int pageno, int pagesize, int customerid) {
			
			List<Policy> policies=policyRepo.findByCustomerCustomerid(customerid);			
			
			Pageable pageable = PageRequest.of(pageno, pagesize);
		
			 int start = pageno * pagesize;
			    int end = Math.min(start + pagesize, policies.size());
			    
			 List<Policy> pagepolicy=policies.subList(start, end);
			 
			 Page<Policy> generated = new PageImpl<>(pagepolicy, pageable, policies.size());
			 
			 return generated;
		}
		
		
	@Override
	public ResponseEntity<List<Policy>> getbycustomerid(Integer customerid) {
		
		Optional<Customer> customer=customerRepo.findById(customerid);
		
		Customer customer2=null;
		
		if(customer.isPresent()) {
			
			customer2=customer.get();
			
			List<Policy> policies=customer2.getPolicies();
			
			return new  ResponseEntity(policies,HttpStatus.OK);
		}
		return new ResponseEntity(null,HttpStatus.OK);
	}


	@Override
	public ResponseEntity<Policy> getbypolicynumber(Integer policynumber) {
		
		Optional<Policy> policy=policyRepo.findById(policynumber);
		
		if(policy!=null) {
			return new ResponseEntity(policy,HttpStatus.OK);
		}
		
		return new ResponseEntity(null,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Policy> addPolicyBySchemename(Policy policy, Integer customemrid, String schemename) {
		
		Optional<Customer> customer=customerRepo.findById(customemrid);
		Scheme scheme=schemeRepo.findBySchemename(schemename);
		
		Customer customer2=null;
		Policy policy2=null;
		if(customer.isPresent()) {
			customer2=customer.get();
			policy.setCustomer(customer2);
			System.out.println(scheme.getSchemename());
			policy.setScheme(scheme);
			policy2=policyRepo.save(policy);	
		}
		log.info("policy Added Successfully");
		
		return new ResponseEntity(policy2,HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<List<Policy>> getallpolicy() {
		List<Policy> policies=policyRepo.findAll();
		
		if(policies.size()==0) {
			throw new PolicyNotFoundException("Not policy present");
		}
		
		return new ResponseEntity(policies,HttpStatus.OK);
	}


	
	
	
	
	;
	
	
	
	
	
	
	
	
	







	
}
