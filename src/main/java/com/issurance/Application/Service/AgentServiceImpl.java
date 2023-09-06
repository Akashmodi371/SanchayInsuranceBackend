package com.issurance.Application.Service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.issurance.Application.entities.Agent;
import com.issurance.Application.entities.Role;
import com.issurance.Application.repository.AgentRepo;
import com.issurance.Application.repository.RoleRepo;
import com.issurance.Application.repository.UserInfoRepo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Slf4j
public class AgentServiceImpl implements AgentService{
	
	
	
	private static final Logger log = LoggerFactory.getLogger(AgentServiceImpl.class);

	@Autowired
	private AgentRepo agentRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private UserInfoRepo userInfoRepo;
	
	
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void registerAgent(Agent agent) {
		Optional<Role> role=roleRepo.findById(3);
		
		Role role2=null;
		
		if(role.isPresent()) {
			role2=role.get();
			agent.getUserInfo().setRole(role2);
		}
		else {
			agent.getUserInfo().setRole(null);
		}
		
		String pass=agent.getUserInfo().getPassword();
		
		agent.getUserInfo().setPassword(passwordEncoder.encode(pass));
		
		agentRepo.save(agent);
		
		log.info("Agent "+ agent +"is added Successfully");
		
	}

}
