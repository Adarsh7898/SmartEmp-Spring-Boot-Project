package com.adarsh.employee.employeecrud.controllers;

import com.adarsh.employee.employeecrud.entity.Employee;
import com.adarsh.employee.employeecrud.repositories.EmpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/emp")
@CrossOrigin
public class EmpController {

    @Autowired
    private EmpRepo empRepo;

    @GetMapping("/getAll")
    public List<Employee> getAll(){
        return empRepo.findAll();
    }

    @PostMapping("/add")
    public Employee add(@RequestBody Employee employee){
        return empRepo.save(employee);
    }



    @PutMapping("update/{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id, @RequestBody Employee emp){
        Employee employee=empRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Not Found"));
        employee.setName(emp.getName());
        employee.setAddress(emp.getAddress());
        employee.setRole(emp.getRole());
        Employee updatedEmployee=empRepo.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

}
