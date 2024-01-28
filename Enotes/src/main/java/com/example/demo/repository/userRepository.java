package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.User;

public interface userRepository  extends JpaRepository<User,Integer>{
	
	public boolean existsByEmail(String email);
	
	public User findByEmail(String email);
	

}
