package com.issurance.Application.Controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.issurance.Application.Service.PolicyService;
import com.issurance.Application.entities.Policy;

import com.issurance.Application.repository.PlanRepo;
import com.issurance.Application.repository.PolicyRepo;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("policyapp")
@Slf4j
public class PolicyController {

	
	private static final Logger log = LoggerFactory.getLogger(PolicyController.class);

	
	@Autowired
	private PolicyRepo policyRepo;
	
	@Autowired
	private PlanRepo planRepo;
	
	@Autowired
	private PolicyService policyService;
	
	//Adding policy
		@PostMapping("/addpolicy/{customerid}/{schemeid}")
		public ResponseEntity<String> addPolicy(@RequestBody Policy policy,@PathVariable(name="customerid") Integer customerid ,
				@PathVariable(name="schemeid") Integer schemeid) {
			
			return policyService.addPolicy(policy, customerid,schemeid);
		}
	
	//adding policy by schemename
		@PostMapping("/addpolicyScheme/{customerid}/{schemename}")
		public ResponseEntity<Policy> addPolicyBySchemename(@RequestBody Policy policy,@PathVariable(name="customerid") Integer customerid ,
				@PathVariable(name="schemename") String schemename) {
			
			return policyService.addPolicyBySchemename(policy, customerid,schemename);
		}
		
	//get all policy
	@GetMapping("/getall")
	public ResponseEntity<List<Policy>> getallpolicy() {
		
		return policyService.getallpolicy();
	}	

	//get policy by customerid in pageable
		
		@GetMapping("/getallpolicy")
		public Page<Policy> getallpolicies(@RequestParam(defaultValue = "0") int pageno,@RequestParam(defaultValue = "4")int pagesize,
				@RequestParam(name = "customerid") int customerid){
			return policyService.getallinpage(pageno, pagesize, customerid);
		}
		
		
	//get all policy by customerid	
		@GetMapping("getbycustomerid")
		public ResponseEntity<List<Policy>> getbycustomerid(@RequestParam(name="customerid") Integer customerid){
			return policyService.getbycustomerid(customerid);
		}
		
		
	//get policy by policy number
		@GetMapping("getbypolicynumber")
		public ResponseEntity<Policy> getbypolicynumber(@RequestParam(name="policynumber") Integer policynumber){
			
			return policyService.getbypolicynumber(policynumber);
		}
	
	
	
	
}
