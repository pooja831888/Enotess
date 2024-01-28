package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Notes;
import com.example.demo.entity.User;

public interface NotesService {
	
	public Notes saveNotes(Notes notes);
	
	public Notes getNotesById(int id);
	
	public List<Notes> getNotesByUser(User user);
	
	public Notes updateNotes(Notes notes);
	
	public boolean deleteNotes(int id);
	

}
