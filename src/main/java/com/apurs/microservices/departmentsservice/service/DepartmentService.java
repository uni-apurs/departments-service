package com.apurs.microservices.departmentsservice.service;

import java.util.List;

import com.apurs.microservices.departmentsservice.dto.DepartmentCreateDTO;
import com.apurs.microservices.departmentsservice.dto.DepartmentDTO;
import com.apurs.microservices.departmentsservice.dto.DepartmentUpdateDTO;

public interface DepartmentService {
	public abstract List<DepartmentDTO> findAll();
	public abstract DepartmentDTO findOne(Integer id);
	public abstract DepartmentDTO insert(DepartmentCreateDTO department) throws Exception;
	public abstract DepartmentDTO update(DepartmentUpdateDTO department) throws Exception;
	public abstract boolean delete(Integer id);
	
	public abstract List<DepartmentDTO> findAllDepartmentsByName(String name);

	public abstract List<DepartmentDTO> findAllDepartmentsWhereFacultyName(String facultyName);
}
