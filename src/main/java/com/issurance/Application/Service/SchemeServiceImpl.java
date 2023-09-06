package com.issurance.Application.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.issurance.Application.entities.Plan;
import com.issurance.Application.entities.Scheme;
import com.issurance.Application.exceptions.SchemeNotFoundException;
import com.issurance.Application.exceptions.UserApiException;
import com.issurance.Application.repository.PlanRepo;
import com.issurance.Application.repository.SchemeRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SchemeServiceImpl implements SchemeService{
	
	@Autowired
	private SchemeRepo schemeRepo;
	
	@Autowired
	private PlanRepo planRepo;
	
	@Override
	public ResponseEntity<String> addScheme(Scheme scheme, int planid) {
	 
		Optional<Plan> plan=planRepo.findById(planid);
		
		Plan plan2=null;
		
		if(plan.isPresent()) {
			plan2=plan.get();
			scheme.setPlan(plan2);
			schemeRepo.save(scheme);
			
			return new ResponseEntity("Add Successfully",HttpStatus.OK);
		}
		
		return new ResponseEntity("No plan by this id",HttpStatus.NOT_FOUND);
		
	}

	@Override
	public ResponseEntity<List<Scheme>> getall() {
		List<Scheme> schemes=schemeRepo.findAll();
		
		return new ResponseEntity(schemes,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Scheme> getbyid(int schemeid) {
		
		Optional<Scheme> scheme=schemeRepo.findById(schemeid);
		
		Scheme scheme2=null;
		
		if(scheme.isPresent()) {
			scheme2=scheme.get();	
		}
		return new ResponseEntity(scheme2,HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<List<Scheme>> getbyplanid(int planid) {
		
		Optional<Plan> plan=planRepo.findById(planid);
		
		
		List<Scheme> schemes=null;
		
		Plan plan2=null;
		if(plan.isPresent()) {
			plan2=plan.get();	
		}
		schemes=plan.get().getSchemes();
		if(schemes.size()==0) {
			throw new SchemeNotFoundException("No Scheme Available");
		}
		return new ResponseEntity(schemes,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Scheme>> getbyplanname(String planname) {
		
		List<Scheme> schemes=schemeRepo.findByPlanPlanname(planname);
		
		if(schemes.size()==0) {
			throw new SchemeNotFoundException("No Schemes Available");
		}
		return new ResponseEntity(schemes,HttpStatus.OK);
	}


	@Override
	public ResponseEntity<Scheme> getbyschemename(String schemename) {
		
		Scheme scheme=schemeRepo.findBySchemename(schemename);
		
		if(scheme==null) {
			throw new SchemeNotFoundException("scheme name not exists");
		}
		
		return new ResponseEntity(scheme,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> addschemebyplanname(Scheme scheme, String planname) {
		
		Plan plan=planRepo.findByPlanname(planname);
		
		if(scheme==null) {
			throw new SchemeNotFoundException("Scheme not preset");
		}
		
		scheme.setPlan(plan);
		schemeRepo.save(scheme);
		
		return new ResponseEntity("Added Successfully",HttpStatus.OK);
	}



}
