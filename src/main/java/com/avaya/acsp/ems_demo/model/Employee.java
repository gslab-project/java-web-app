package com.avaya.acsp.ems_demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.avaya.acsp.ems_demo.enums.EmployeeStatus;

@Entity
@Table(name = "employee")
public class Employee {
	private int empId;
	private String firstName;
	private String lastName;
	private String emailId;
	private String address;
	private EmployeeStatus employeeStatus;
	private Date statusUpdatedDate;

	public Employee() {
	}

	public Employee(int empId, String firstName, String lastName, String emailId, String address,
			EmployeeStatus employeeStatus, Date statusUpdatedDate) {
		super();
		this.empId = empId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.address = address;
		this.employeeStatus = employeeStatus;
		this.statusUpdatedDate = statusUpdatedDate;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_id")
	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int id) {
		this.empId = id;
	}

	@Column(name = "first_name")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "email_id")
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	public EmployeeStatus getEmployeeStatus() {
		return employeeStatus;
	}

	public void setEmployeeStatus(EmployeeStatus status) {
		this.employeeStatus = status;
	}

	@Column(name = "status_updated_on")
	public Date getStatusUpdatedDate() {
		return statusUpdatedDate;
	}

	public void setStatusUpdatedDate(Date statusUpdatedDate) {
		this.statusUpdatedDate = statusUpdatedDate;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId="
				+ emailId + "]";
	}
}
