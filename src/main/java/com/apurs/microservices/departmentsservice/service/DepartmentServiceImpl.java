package com.apurs.microservices.departmentsservice.service;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.apurs.microservices.departmentsservice.respository.DepartmentRepository;
import com.apurs.microservices.facultiesservice.dto.FacultyDTO;
import com.apurs.microservices.departmentsservice.dto.DepartmentCreateDTO;
import com.apurs.microservices.departmentsservice.dto.DepartmentDTO;
import com.apurs.microservices.departmentsservice.dto.DepartmentUpdateDTO;
import com.apurs.microservices.departmentsservice.model.Department;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	private DepartmentRepository departmentRepository;
	
	private RestTemplate restTemplate = new RestTemplate();
	private ModelMapper modelMapper = new ModelMapper();
	
	@Autowired
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();
	
	@Value("${app.facultiesEndpoint}")
	private String facultiesEndpoint;
	
	public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}
	
	@Override
	public List<DepartmentDTO> findAll() {
		List<Department> departments = departmentRepository.findAll();
		List<DepartmentDTO> dtos = new ArrayList<DepartmentDTO>();
		
		for (Department department : departments)
			dtos.add(modelMapper.map(department, DepartmentDTO.class));
				
		return dtos;
	}

	@Override
	public DepartmentDTO findOne(Integer id) {
		Department department = departmentRepository.getById(id);
		return modelMapper.map(department, DepartmentDTO.class);
	}

	@Override
	public DepartmentDTO insert(DepartmentCreateDTO department) throws Exception {
		ResponseEntity<String> res = restTemplate.getForEntity(facultiesEndpoint + department.getFacultyId(), String.class);
		
		if (!res.getStatusCode().equals(HttpStatus.OK))
			throw new Exception("Invalid facultyId.");
		
		Department createDepartment = modelMapper.map(department, Department.class);
		createDepartment = departmentRepository.save(createDepartment);
		return modelMapper.map(createDepartment, DepartmentDTO.class);
	}

	@Override
	public DepartmentDTO update(DepartmentUpdateDTO department) throws Exception {
		if (!departmentRepository.existsById(department.getId()))
			return null;
		
		ResponseEntity<String> res = restTemplate.getForEntity(facultiesEndpoint + department.getFacultyId(), String.class);
	
		if (!res.getStatusCode().equals(HttpStatus.OK))
			throw new Exception("Invalid facultyId.");
		
		Department tempDepartment = departmentRepository.getById(department.getId());
		Department updatedDepartment = modelMapper.map(department, Department.class);
		updatedDepartment.setCreatedAt(tempDepartment.getCreatedAt());
		updatedDepartment = departmentRepository.save(updatedDepartment);
		return modelMapper.map(updatedDepartment, DepartmentDTO.class);
	}

	@Override
	public boolean delete(Integer id) {
		if (!departmentRepository.existsById(id))
			return false;
		
		departmentRepository.deleteById(id);
		return true;
	}

	@Override
	public List<DepartmentDTO> findAllDepartmentsWhereFacultyName(String facultyName) {
		ResponseEntity<List<FacultyDTO>> res = restTemplate.exchange(facultiesEndpoint + "?name=" + facultyName, HttpMethod.GET, null, new ParameterizedTypeReference<List<FacultyDTO>>() {});
		List<FacultyDTO> matches = res.getBody();
		
		String inSql = "SELECT * FROM department WHERE \"facultyId\" IN (";
		for (FacultyDTO match : matches) {
			inSql += match.getId() + ", ";
		}
		inSql = inSql.substring(0, inSql.length() - 2) + ")";
		
		List<DepartmentDTO> departments = new ArrayList<DepartmentDTO>();
		
		departments = jdbcTemplate.query(inSql, (rs, rowNum) ->
				new DepartmentDTO(
					rs.getInt("id"),
					rs.getInt("facultyId"),
					rs.getString("name")
				));
		
		return departments;
	}

}
