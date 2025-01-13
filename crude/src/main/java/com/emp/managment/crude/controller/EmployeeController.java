package com.emp.managment.crude.controller;

import com.emp.managment.crude.dto.EmployeeDto;
import com.emp.managment.crude.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService empService;

    @PostMapping(value="/create")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto empDto)
    {
        EmployeeDto result=empService.creatEmployee(empDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<EmployeeDto>> getAllEmployees()
    {
        List<EmployeeDto> result=empService.getAllEmployee();
        return ResponseEntity.ok(result);
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployees(@PathVariable("id") Long empId)
    {
        EmployeeDto result=empService.getEmployeeById(empId);
        return ResponseEntity.ok(result);
    }

    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long empId, @RequestBody EmployeeDto updatedempDto)
    {
        EmployeeDto result=empService.updateEmployee(empId,updatedempDto);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }
//put is full update
 //patch is partial update
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long empId)
    {
        empService.deleteEmployee(empId);
        return ResponseEntity.ok("Employee with id "+empId+" deleted successfully");
    }

}
