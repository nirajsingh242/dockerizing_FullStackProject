package com.emp.managment.crude.service;

import com.emp.managment.crude.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    public EmployeeDto creatEmployee(EmployeeDto emp);

    public List<EmployeeDto> getAllEmployee();

    public EmployeeDto getEmployeeById(Long id);

    public EmployeeDto updateEmployee(Long id, EmployeeDto updatedEmp);

    public void deleteEmployee(Long id);


}
