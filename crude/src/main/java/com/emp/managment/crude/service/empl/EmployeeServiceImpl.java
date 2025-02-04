package com.emp.managment.crude.service.empl;

import com.emp.managment.crude.dto.EmployeeDto;
import com.emp.managment.crude.entity.Employee;
import com.emp.managment.crude.mapper.EmployeeMapper;
import com.emp.managment.crude.repository.EmployeeRespository;
import com.emp.managment.crude.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRespository empRepo;

    @Override
    public EmployeeDto creatEmployee(EmployeeDto emp)
    {
        Employee employee= EmployeeMapper.maptoEmployee(emp);
        Employee result=empRepo.save(employee);
        return EmployeeMapper.maptoEmployeeDto(result);

    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        List<Employee> result=empRepo.findAll();
        return result.stream().map(r->EmployeeMapper.maptoEmployeeDto(r)).sorted().collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "employeeCache", key = "#id")//if wnat refer argument key = "#root.args[0]"
    public EmployeeDto getEmployeeById(Long id) {
        System.out.println("Fetching employee from database: " + id);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Employee emp=empRepo.getById(id);
        return EmployeeMapper.maptoEmployeeDto(emp!=null?emp:new Employee());
    }

    @Override
    @CacheEvict(value = "employeeCache", key = "#id") // Clears cache when employee is updated
    public EmployeeDto updateEmployee(Long id, EmployeeDto updatedEmp) {
    Employee result=empRepo.findById(id).orElseThrow(()->new RuntimeException("resource not found"));

    result.setEmail(updatedEmp.getEmail());
    result.setFirstName(updatedEmp.getFirstName());
    result.setLastName(updatedEmp.getLastName());

    return EmployeeMapper.maptoEmployeeDto(empRepo.save(result));

    }

    @Override
    public void deleteEmployee(Long id) {

        Employee result=empRepo.findById(id).orElseThrow(()->new RuntimeException("resource not found"));
       empRepo.deleteById(id);
    }
}
