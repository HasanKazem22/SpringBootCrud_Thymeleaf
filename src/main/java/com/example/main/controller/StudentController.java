package com.example.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.main.model.StudentModel;
import com.example.main.repository.StudentRepository;

@Controller
public class StudentController {
	@Autowired
	StudentRepository studentRepo;
	
	// Home Page Controller
	@GetMapping("/")
	public String home() {
		return "StudentHome.html";
	}
	
	
	// Save Data Controller
	@PostMapping("/saveData")
	public String saveStudent(@ModelAttribute StudentModel studentModel, RedirectAttributes redirect, Model model) {
		StudentModel savedStudent = studentRepo.save(studentModel);

        if (savedStudent != null) {
            // If the save was successful, add a success flash attribute
            redirect.addFlashAttribute("success", "Your Information Saved Successfully!!!");
        }
        
		return "redirect:/";
	}
	
	
	// Search Data Controller
	@PostMapping("/searchStudent")
	public String searchStudent(@RequestParam int id, Model model, RedirectAttributes redirect) {
		StudentModel ss = studentRepo.getReferenceById(id);
	    model.addAttribute("show", ss);
		
		return "StudentView.html";
	}
	
	
	//Update Data Controller
	@PostMapping("/updateStudent")
	public String updateStudent(@RequestParam int id, Model model) {
		StudentModel su =studentRepo.getReferenceById(id);
		model.addAttribute("update", su);
		
		return "StudentUpdate.html";
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute StudentModel studentModel) {
		StudentModel u = studentRepo.getReferenceById(studentModel.getId());
		u.setName(studentModel.getName());
		u.setRoll(studentModel.getRoll());
		u.setDepart(studentModel.getDepart());
		u.setSec(studentModel.getSec());
		
		studentRepo.save(u);
		return "redirect:/";
	}
	
	
	// Delete Data Controller
	@PostMapping("/deleteStudent")
	public String deleteStudent(@RequestParam int id) {
		studentRepo.deleteById(id);
		return "redirect:/";
	}
	
	
	// Search Data Using Query DSL
	@PostMapping("/nameSearch")
	public String nameSearch(@RequestParam String name, Model model) {
		List<StudentModel> ms = studentRepo.findByName(name);
		model.addAttribute("msearch", ms);
		
		return "StudentsView.html";
	}
	
	
	// Search Data Using @Query Annotation
	@PostMapping("/departSearch")
	public String departSearch(@RequestParam String depart, Model model) {
		List<StudentModel> ms = studentRepo.findDepart(depart);
		model.addAttribute("msearch", ms);
		
		return "StudentsView.html";
	}
}
