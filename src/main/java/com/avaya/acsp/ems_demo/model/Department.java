package com.avaya.acsp.ems_demo.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "department")
public class Department {
	private int deptId;
	private String deptName;
	private Set<Employee> employees;

	public Department() {
	}

	public Department(int deptId, String deptName) {
		this.deptId = deptId;
		this.deptName = deptName;
	}

	@Id
	@Column(name = "dept_id")
	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int dept_id) {
		this.deptId = dept_id;
	}

	@Column(name = "dept_name")
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@OneToMany
	@JoinColumn(name = "dept_id")
	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "Department [deptId=" + deptId + ", deptName=" + deptName + ", employees=" + employees + "]";
	}
}
