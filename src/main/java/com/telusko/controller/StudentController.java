package com.telusko.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.model.Student;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class StudentController {

	
	private List<Student> students=new ArrayList<>(List.of(
			new Student(1,"Ajay",60),
			new Student(2,"rahul",70)
			));
	
	@GetMapping("/csrf-token")
	public CsrfToken getcsrfToken(HttpServletRequest req)
	{
		CsrfToken res=(CsrfToken) req.getAttribute("-csrf");
		System.out.println(res);
		return (CsrfToken) req.getAttribute("-csrf");
	}
	
	
	@GetMapping("/students")
	public List<Student> getStudents()
	{
		return students;
		
	}
	
	@PostMapping("/students")
	public Student addStudent(@RequestBody Student student)
	{
		students.add(student);
		return student;
	}
}
