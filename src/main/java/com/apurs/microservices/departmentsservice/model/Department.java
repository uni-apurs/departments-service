package com.apurs.microservices.departmentsservice.model;

import java.time.ZonedDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="\"department\"")
@SequenceGenerator(name = "department_id_seq", initialValue = 1, allocationSize = 1)
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_id_seq")
	@Column(name="\"id\"")
	private int id;
	
	@Column(name="\"name\"")
	private String name;
	
	@Column(name="\"facultyId\"")
	private int facultyId;

	@Column(name="\"createdAt\"")
	private ZonedDateTime createdAt;
	
	@Column(name="\"updatedAt\"")
	private ZonedDateTime updatedAt;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getFacultyId() {
		return facultyId;
	}
	
	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}

	public ZonedDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(ZonedDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public ZonedDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(ZonedDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}
