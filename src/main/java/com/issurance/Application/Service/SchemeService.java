package com.issurance.Application.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.issurance.Application.entities.Scheme;

public interface SchemeService {
		
	public ResponseEntity<String> addScheme(Scheme scheme,int planid);
	
	public ResponseEntity<String> addschemebyplanname(Scheme scheme, String planname);
	
	public ResponseEntity<List<Scheme>> getall();
	
	public ResponseEntity<Scheme> getbyid(int schemeid);
	
	public ResponseEntity<Scheme> getbyschemename(String schemename);
	
	public ResponseEntity<List<Scheme>> getbyplanid(int planid);
	
	public ResponseEntity<List<Scheme>> getbyplanname(String planname);
	
	
}
