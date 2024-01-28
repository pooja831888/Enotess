package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Notes;
import com.example.demo.entity.User;

public interface NotesRepository extends JpaRepository<Notes,Integer> {
	
	public List<Notes> findByUser(User user);	

}
