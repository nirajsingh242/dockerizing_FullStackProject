package com.emp.managment.crude.mapper;

import com.emp.managment.crude.dto.EmployeeDto;
import com.emp.managment.crude.entity.Employee;

public class EmployeeMapper {
    public static EmployeeDto maptoEmployeeDto(Employee emp)
    {
        return new EmployeeDto(emp.getId(), emp.getFirstName(), emp.getLastName(),emp.getEmail());
    }

    public static Employee maptoEmployee(EmployeeDto emp)
    {
        return new Employee(emp.getId(), emp.getFirstName(), emp.getLastName(),emp.getEmail());
    }

}
