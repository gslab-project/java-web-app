package com.avaya.acsp.ems_demo.controller;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

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

import com.avaya.acsp.ems_demo.enums.EmployeeStatus;
import com.avaya.acsp.ems_demo.exception.ResourceNotFoundException;
import com.avaya.acsp.ems_demo.model.Department;
import com.avaya.acsp.ems_demo.model.Employee;
import com.avaya.acsp.ems_demo.model.EmployeeView;
import com.avaya.acsp.ems_demo.repository.DepartmentRepository;
import com.avaya.acsp.ems_demo.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1/emsservice/employees")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private DepartmentRepository departmentRepository;

	@GetMapping("/all")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@GetMapping
	public List<Employee> getAllEmployeesUpdatedToday() {
		List<Employee> employeesUpdatedToday = new ArrayList<>();
		List<Employee> employees = employeeRepository.findAll();

		Date today = new Date();
		// Getting the default zone id
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Instant instant = today.toInstant();
		// Converting the Date to LocalDate
		LocalDate todaysDate = instant.atZone(defaultZoneId).toLocalDate();

		for (Employee employee : employees) {
			if (todaysDate.equals(employee.getUpdatedDate().toInstant().atZone(defaultZoneId).toLocalDate())) {
				employeesUpdatedToday.add(employee);
			}
		}
		return employeesUpdatedToday;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") int employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		return ResponseEntity.ok().body(employee);
	}

	@PostMapping
	public Employee createEmployee(@Validated @RequestBody EmployeeView employeeView, HttpServletResponse response)
			throws ResourceNotFoundException {
		Optional<Department> departmentOpt = departmentRepository.findById(employeeView.getDeptId());
		if (!departmentOpt.isPresent()) {
			throw new ResourceNotFoundException("Department not found");
		}
		Employee employee = new Employee(employeeView.getFirstName(), employeeView.getLastName(),
				employeeView.getEmailId(), employeeView.getAddress(), employeeView.getEmployeeStatus(),
				employeeView.getUpdatedDate());
		Department department = departmentOpt.get();
		employee.setDepartment(department);
		response.setStatus(201);
		return employeeRepository.save(employee);
	}

	@PostMapping("/all")
	public List<Employee> createAllEmployees(@Validated @RequestBody List<EmployeeView> employeeViews,
			HttpServletResponse response) throws ResourceNotFoundException {
		List<Employee> employees = new LinkedList<>();

		for (EmployeeView employeeView : employeeViews) {
			Optional<Department> departmentOpt = departmentRepository.findById(employeeView.getDeptId());
			if (!departmentOpt.isPresent()) {
				throw new ResourceNotFoundException("Department not found");
			}
			Employee employee = new Employee(employeeView.getFirstName(), employeeView.getLastName(),
					employeeView.getEmailId(), employeeView.getAddress(), employeeView.getEmployeeStatus(),
					employeeView.getUpdatedDate());
			Department department = departmentOpt.get();
			employee.setDepartment(department);

			employees.add(employee);
		}

		response.setStatus(201);
		return employeeRepository.saveAll(employees);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") int employeeId,
			@Validated @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employee.setEmailId(employeeDetails.getEmailId());
		employee.setLastName(employeeDetails.getLastName());
		employee.setFirstName(employeeDetails.getFirstName());
		final Employee updatedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") int employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employee.setEmployeeStatus(EmployeeStatus.INACTIVE);
		employee.setUpdatedDate(new Date());
		employeeRepository.save(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
