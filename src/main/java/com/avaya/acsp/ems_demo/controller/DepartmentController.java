package com.avaya.acsp.ems_demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.avaya.acsp.ems_demo.model.Employee;
import com.avaya.acsp.ems_demo.repository.DepartmentRepository;
import com.avaya.acsp.ems_demo.repository.EmployeeRepository;
import com.avaya.acsp.ems_demo.service.EmployeeService;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {

	Logger log = LoggerFactory.getLogger(DepartmentController.class);

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping("/all")
	public List<Department> getAllDepartments() {
		return departmentRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Department> getDepartmentById(@PathVariable(value = "id") int departmentId)
			throws ResourceNotFoundException {

		log.info("Fetching department id {} details",departmentId);
		Department department = departmentRepository.findById(departmentId).orElseThrow(
				() -> new ResourceNotFoundException("Department not found for this id :: " + departmentId));
		return ResponseEntity.ok().body(department);
	}

	@PostMapping
	public Department createDepartment(@Validated @RequestBody Department department, HttpServletResponse response) {
		response.setStatus(201);
		return departmentRepository.save(department);
	}

	@PostMapping("/all")
	public List<Department> createAllDepartments(@Validated @RequestBody List<Department> departments,
			HttpServletResponse response) {
		response.setStatus(201);
		return departmentRepository.saveAll(departments);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Department> updateDepartment(@PathVariable(value = "id") int departmentId,
			@Validated @RequestBody Department departmentDetails) throws ResourceNotFoundException {
		Department department = departmentRepository.findById(departmentId).orElseThrow(
				() -> new ResourceNotFoundException("Department not found for this id :: " + departmentId));

		department.setDeptName(departmentDetails.getDeptName());
		final Department updatedDepartment = departmentRepository.save(department);

		log.info("department id {} updated successfully",departmentId);

		return ResponseEntity.ok(updatedDepartment);
	}

	@DeleteMapping("/{dept_id}")
	public Map<String, Boolean> deleteDepartment(@PathVariable(value = "dept_id") int departmentId)
			throws ResourceNotFoundException {
		Department department = departmentRepository.findById(departmentId).orElseThrow(
				() -> new ResourceNotFoundException("Department not found for this id :: " + departmentId));

		List<Employee> employees = employeeService.getAllEmployeesByDeptName(department.getDeptName());
		for (Employee employee : employees) {
			employee.setDepartment(null);
		}

		employeeRepository.saveAll(employees);
		departmentRepository.delete(department);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);

		log.info("department id {} deleted",departmentId);
		return response;
	}

	@DeleteMapping("/name/{name}")
	public Map<String, Boolean> deleteDepartmentByName(@PathVariable(value = "name") String departmentName)
			throws ResourceNotFoundException {
		Department department = departmentRepository.findByDeptName(departmentName);
		if (department == null) {
			new ResourceNotFoundException("Department not found for this id :: " + departmentName);
		}

		List<Employee> employees = employeeService.getAllEmployeesByDeptName(department.getDeptName());
		for (Employee employee : employees) {
			employee.setDepartment(null);
		}

		employeeRepository.saveAll(employees);
		departmentRepository.delete(department);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);

		log.info("department {} deleted",departmentName);

		return response;
	}
}
