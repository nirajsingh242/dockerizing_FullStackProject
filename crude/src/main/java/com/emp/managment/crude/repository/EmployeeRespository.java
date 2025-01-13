package com.emp.managment.crude.repository;

import com.emp.managment.crude.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRespository extends JpaRepository<Employee,Long> {
}
