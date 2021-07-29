package com.apurs.microservices.departmentsservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apurs.microservices.departmentsservice.dto.DepartmentCreateDTO;
import com.apurs.microservices.departmentsservice.dto.DepartmentDTO;
import com.apurs.microservices.departmentsservice.dto.DepartmentUpdateDTO;
import com.apurs.microservices.departmentsservice.service.DepartmentService;

@RestController
@RequestMapping("/departments")
public class DepartmentRestController {

	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping("")
	public List<DepartmentDTO> getDepartments(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String facultyName) {
		if (name != null)
			return departmentService.findAllDepartmentsByName(name);
		
		if (facultyName != null)
			return departmentService.findAllDepartmentsWhereFacultyName(facultyName);
			
		return departmentService.findAll();
	}
	
	@GetMapping("/{id}")
	public DepartmentDTO getDepartment(@PathVariable("id") Integer id) {
		return departmentService.findOne(id);
	}
	
	@PostMapping("")
	public ResponseEntity<DepartmentDTO> insertDepartment(@RequestBody DepartmentCreateDTO department) throws Exception {
		if (departmentService.insert(department) != null)
			return new ResponseEntity<>(HttpStatus.OK);
		
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@PutMapping("")
	public ResponseEntity<DepartmentDTO> updateDepartment(@RequestBody DepartmentUpdateDTO department) throws Exception {
		if (departmentService.update(department) != null)
			return new ResponseEntity<>(HttpStatus.OK);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<DepartmentDTO> deleteDepartment(@PathVariable ("id") Integer id) {
		if (departmentService.delete(id))
			return new ResponseEntity<>(HttpStatus.OK);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
