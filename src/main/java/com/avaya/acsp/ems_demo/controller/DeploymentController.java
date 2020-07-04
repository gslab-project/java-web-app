package com.avaya.acsp.ems_demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avaya.acsp.ems_demo.exception.ResourceNotFoundException;
import com.avaya.acsp.ems_demo.model.Department;
import com.avaya.acsp.ems_demo.repository.DepartmentRepository;

@RestController
@RequestMapping("/api/v1/departments")
public class DeploymentController {
	@Autowired
	private DepartmentRepository departmentRepository;

	@GetMapping
	public List<Department> getAllDepartments() {
		return departmentRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Department> getDepartmentById(@PathVariable(value = "id") int departmentId)
			throws ResourceNotFoundException {
		Department department = departmentRepository.findById(departmentId).orElseThrow(
				() -> new ResourceNotFoundException("Department not found for this id :: " + departmentId));
		return ResponseEntity.ok().body(department);
	}

	@PostMapping
	public Department createDepartment(@Validated @RequestBody Department department) {
		return departmentRepository.save(department);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Department> updateDepartment(@PathVariable(value = "id") int departmentId,
			@Validated @RequestBody Department departmentDetails) throws ResourceNotFoundException {
		Department department = departmentRepository.findById(departmentId).orElseThrow(
				() -> new ResourceNotFoundException("Department not found for this id :: " + departmentId));

		department.setDeptName(departmentDetails.getDeptName());
		final Department updatedDepartment = departmentRepository.save(department);
		return ResponseEntity.ok(updatedDepartment);
	}

	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteDepartment(@PathVariable(value = "id") int departmentId)
			throws ResourceNotFoundException {
		Department department = departmentRepository.findById(departmentId).orElseThrow(
				() -> new ResourceNotFoundException("Department not found for this id :: " + departmentId));

		departmentRepository.delete(department);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}