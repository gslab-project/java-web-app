package com.avaya.acsp.ems_demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avaya.acsp.ems_demo.model.Employee;
import com.avaya.acsp.ems_demo.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	EmployeeRepository employeeRepository;

	public List<Employee> getAllEmployeesByDeptName(String deptName) {
		List<Employee> employeesByDeptName = new ArrayList<>();
		List<Employee> employees = employeeRepository.findAll();
		for (Employee employee : employees) {
			if (employee.getDepartment() != null && employee.getDepartment().getDeptName().equals(deptName)) {
				employeesByDeptName.add(employee);
			}
		}
		return employeesByDeptName;
	}

}
