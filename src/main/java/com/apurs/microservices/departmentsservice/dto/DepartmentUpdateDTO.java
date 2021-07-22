package com.apurs.microservices.departmentsservice.dto;

import java.time.ZonedDateTime;

public class DepartmentUpdateDTO {
	private int id;
	private int facultyId;
	private String name;
	private ZonedDateTime updatedAt;
	
	public DepartmentUpdateDTO(int id, int facultyId, String name) {
		super();
		this.id = id;
		this.facultyId = facultyId;
		this.name = name;
		this.setUpdatedAt(ZonedDateTime.now());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ZonedDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(ZonedDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}
