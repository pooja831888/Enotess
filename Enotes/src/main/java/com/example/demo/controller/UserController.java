package com.example.demo.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Notes;
import com.example.demo.entity.User;
import com.example.demo.repository.userRepository;
import com.example.demo.service.NotesService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private userRepository userRepo;
	
	@Autowired
	private NotesService notesService;;
	
	

	@ModelAttribute
	public User getUser(Principal p, Model m) {
		String email = p.getName();
		User user = userRepo.findByEmail(email);
		m.addAttribute("user", user);
		return user;
	}
	
	@GetMapping("/addNotes")
	public String addNotes()
	{
		return "add_notes";
	}
	
	@GetMapping("/viewNotes")
	public String viewNotes(Model m,Principal p)
	{
		User user=getUser(p,m);
		List<Notes> notes = notesService.getNotesByUser(user);
		m.addAttribute("notesList",notes);
		
		return "views_notes";
	}
	
	@GetMapping("/editNotes/{id}")
	public String editNotes(@PathVariable int id,Model m)
	{
		Notes notes=notesService.getNotesById(id);
		m.addAttribute("n", notes);
		return "edit";
	}
	
	@PostMapping("/saveNotes")
	public String saveNotes(@ModelAttribute Notes notes,HttpSession session,Principal p,Model m)
	{
		notes.setDate(LocalDate.now());
		notes.setUser(getUser(p,m));
		
		Notes saveNotes=notesService.saveNotes(notes);
		
		if(saveNotes!=null)
		{
			session.setAttribute("msg", "note save successfully");

			
		}else
		{
			session.setAttribute("msg", "something wrong on the server");

			
		}

	
	return "redirect:/user/addNotes";
		
	}
	
	
	
	@PostMapping("/updateNotes")
	public String updateNotes(@ModelAttribute Notes notes,HttpSession session,Principal p,Model m)
	{
		notes.setDate(LocalDate.now());
		notes.setUser(getUser(p,m));
		
		Notes saveNotes=notesService.saveNotes(notes);
		
		if(saveNotes!=null)
		{
			session.setAttribute("msg", "note update successfully");

			
		}else
		{
			session.setAttribute("msg", "something wrong on the server");

			
		}

	
	return "redirect:/user/viewNotes";
		
	}
	
	
	@GetMapping("/deleteNotes/{id}")
	public String deleteNotes(@PathVariable int id,HttpSession session)
	{
		boolean f=notesService.deleteNotes(id);
		if(f)
		{
			session.setAttribute("msg", "delete successfully");

			
		}else
		{
			session.setAttribute("msg", "something wrong on the server");

			
		}
		
		return "redirect:/user/viewNotes";
	}

}
