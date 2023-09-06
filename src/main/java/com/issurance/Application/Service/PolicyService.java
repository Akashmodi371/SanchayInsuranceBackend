package com.issurance.Application.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.issurance.Application.entities.Payment;
import com.issurance.Application.entities.Policy;

public interface PolicyService {

	public ResponseEntity<String> addPolicy(Policy policy,Integer customerid,Integer schemeid);
	
	public ResponseEntity<List<Policy>> getbycustomerid(Integer customerid);
	
	public ResponseEntity<Policy> getbypolicynumber(Integer policynumber);
	
	public Page<Policy> getallinpage(int pageno,int pagesize,int customerid);
	
	public ResponseEntity<Policy> addPolicyBySchemename(Policy policy,Integer customemrid,String schemename);
	
	public ResponseEntity<List<Policy>> getallpolicy();
}
