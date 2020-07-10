package com.avaya.acsp.ems_demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "department")
public class Department {
	private int deptId;
	private String deptName;

	public Department() {
	}

	public Department(String deptName) {
		this.deptName = deptName;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dept_id")
	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int dept_id) {
		this.deptId = dept_id;
	}

	@Column(name = "dept_name", unique = true)
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Override
	public String toString() {
		return "Department [deptId=" + deptId + ", deptName=" + deptName + "]";
	}
}
