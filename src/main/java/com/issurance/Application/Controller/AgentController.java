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

import com.issurance.Application.Service.AgentService;
import com.issurance.Application.entities.Agent;
import com.issurance.Application.entities.Customer;
import com.issurance.Application.repository.AgentRepo;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("agentapp")
@Slf4j
public class AgentController {

	
	private static final Logger log = LoggerFactory.getLogger(AgentController.class);

	
	@Autowired
	private AgentService agentService;
	
	@Autowired
	private AgentRepo agentRepo;
	
	
	//Register Agent
		@PostMapping("/register")
		public ResponseEntity<String>  addAgent(@RequestBody Agent agent) {
			agentService.registerAgent(agent);
			return new ResponseEntity<>("AgentRegistered Successfully",HttpStatus.OK);
		}
		
		
		//Get All Agent
		@GetMapping("/agents")
		public ResponseEntity<List<Agent>> getAgents(){
			
			List<Agent> agents=agentRepo.findAll();
			
			return new ResponseEntity<>(agents,HttpStatus.OK);
		}
	
	
}

