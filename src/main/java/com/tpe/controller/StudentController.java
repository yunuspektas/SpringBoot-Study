package com.tpe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tpe.service.StudentService;


@RestController
@RequestMapping("/students")  
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	
	@GetMapping("/welcome")  
	public String welcome() {
		
		return "Welcome Student Controller";
		
	}
	
	
	

}
