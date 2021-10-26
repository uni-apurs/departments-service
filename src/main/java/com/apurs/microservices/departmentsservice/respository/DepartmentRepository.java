package com.apurs.microservices.departmentsservice.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apurs.microservices.departmentsservice.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
	List<Department> findByNameContainingIgnoreCase(String name);
	List<Department> findByFacultyId(Integer id);
}
