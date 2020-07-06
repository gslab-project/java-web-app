package com.avaya.acsp.ems_demo.model;

import java.util.Date;

import com.avaya.acsp.ems_demo.enums.EmployeeStatus;

public class EmployeeView {
	private int empId;
	private String firstName;
	private String lastName;
	private String emailId;
	private String address;
	private EmployeeStatus employeeStatus;
	private Date updatedDate;
	private int deptId;

	public EmployeeView() {
	}

	public EmployeeView(int empId, String firstName, String lastName, String emailId, String address,
			EmployeeStatus employeeStatus, Date updatedDate, int deptId) {
		super();
		this.empId = empId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.address = address;
		this.employeeStatus = employeeStatus;
		this.updatedDate = updatedDate;
		this.deptId = deptId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public EmployeeStatus getEmployeeStatus() {
		return employeeStatus;
	}

	public void setEmployeeStatus(EmployeeStatus employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	@Override
	public String toString() {
		return "EmployeeView [empId=" + empId + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId="
				+ emailId + "]";
	}
}
