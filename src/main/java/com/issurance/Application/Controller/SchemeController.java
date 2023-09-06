package com.issurance.Application.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.issurance.Application.Service.SchemeService;
import com.issurance.Application.entities.Scheme;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("schemeapp")
@Slf4j
public class SchemeController {

	
	
	private static final Logger log = LoggerFactory.getLogger(SchemeController.class);

	@Autowired
	private SchemeService schemeService;
	
	
	
	@PostMapping("/addscheme/{planid}")
	public ResponseEntity<String> addscheme(@RequestBody Scheme scheme,@PathVariable(name="planid") int planid){
		
		return schemeService.addScheme(scheme, planid);
	}
	@PostMapping("/addschemebyplanname")
	public ResponseEntity<String> addschemebyplanname(@RequestBody Scheme scheme, @RequestParam(name="planname") String planname){
		return schemeService.addschemebyplanname(scheme, planname);
	}
	
	
	@GetMapping("/getall")
	public ResponseEntity<List<Scheme>> getall(){
		
		return schemeService.getall();
	}
	
	@GetMapping("/getbyid")
	public ResponseEntity<Scheme> getbyid(@RequestParam(name="schemeid") int schemid){
		
		return schemeService.getbyid(schemid);
	}
	
	@GetMapping("/getbyschemename")
	public ResponseEntity<Scheme> getbyschemename(@RequestParam(name="schemename") String schemename){
		
		return schemeService.getbyschemename(schemename);
	}
	
	
	
	@GetMapping("getbyplanid")
	public ResponseEntity<List<Scheme>> getbyplanid(@RequestParam(name="planid") int planid){
		return schemeService.getbyplanid(planid);
	}
	
	@GetMapping("getbyplanname")
	public ResponseEntity<List<Scheme>> getbyplanname(@RequestParam(name="planname") String planname){
		return schemeService.getbyplanname(planname);
	}
	

	
	
	
	
	
}
