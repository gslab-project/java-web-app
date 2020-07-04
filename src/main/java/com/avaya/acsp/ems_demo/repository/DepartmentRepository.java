package com.avaya.acsp.ems_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avaya.acsp.ems_demo.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
