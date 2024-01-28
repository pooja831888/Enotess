package com.example.demo.service;

import com.example.demo.entity.User;

public interface userservice {
	
	public User saveUser(User user);
	
	public boolean existEmailCheck(String email);

}
