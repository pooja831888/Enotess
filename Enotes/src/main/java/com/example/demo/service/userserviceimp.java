package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.demo.entity.User;
import com.example.demo.repository.userRepository;

import jakarta.servlet.http.HttpSession;

@Service

public class userserviceimp implements userservice{
	
	
	
	@Autowired
	private userRepository userepo;
	
	@Autowired
	public BCryptPasswordEncoder passwordEncoder;

	@Override
	public User saveUser(User user) {
		user.setRole("ROLE_USER");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User newUser=userepo.save(user);
		return newUser;
	}

	@Override
	public boolean existEmailCheck(String email) {
		
		return userepo.existsByEmail(email);
	}
	
	public void removeSessionMessage() {
		HttpSession session=((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
		session.removeAttribute("msg");
	}
	

}
