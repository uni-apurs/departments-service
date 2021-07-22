package com.apurs.microservices.departmentsservice.dto;

//import java.time.ZonedDateTime;

public class DepartmentDTO {
	private int id;
	private int facultyId;
	private String name;
//	private ZonedDateTime createdAt;
//	private ZonedDateTime updatedAt;

	public DepartmentDTO() {
		super();
	}

	public DepartmentDTO(int id, int facultyId, String name) {
		super();
		this.id = id;
		this.facultyId = facultyId;
		this.name = name;
//		this.createdAt = createdAt;
//		this.updatedAt = updatedAt;
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
	
//	public ZonedDateTime getCreatedAt() {
//		return createdAt;
//	}
//
//	public void setCreatedAt(ZonedDateTime createdAt) {
//		this.createdAt = createdAt;
//	}
//
//	public ZonedDateTime getUpdatedAt() {
//		return updatedAt;
//	}
//
//	public void setUpdatedAt(ZonedDateTime updatedAt) {
//		this.updatedAt = updatedAt;
//	}
}
