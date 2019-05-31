package br.com.csvreader.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.csvreader.model.School;
import br.com.csvreader.repository.SchoolRepository;;


@RestController
public class SchoolController {
	
	@Autowired
	SchoolRepository repository;
	
	// http://localhost:8080/api/save
	@PostMapping("api/database/save")
	public School save(@RequestBody School school){
		return repository.save(school);
	}
	
	// http://localhost:8080/api/all
	@GetMapping("api/database/all")
	public List<School> all(){
		return (List<School>) repository.findAll();
	}
}