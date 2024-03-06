package com.technophaa.employeeManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.technophaa.employeeManagementSystem.model.Employee;

	@Repository
	public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	   
	}



