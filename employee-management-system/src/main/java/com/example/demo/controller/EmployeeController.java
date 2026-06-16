package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository repo;

   
    @PostMapping("/save")
    public Employee saveEmployee(@RequestBody Employee employee) {

        return repo.save(employee);
    }

    @GetMapping("/all")
    public List<Employee> getAllEmployees() {

        return repo.findAll();
    }

    
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable long id) {

        Optional<Employee> emp = repo.findById(id);

        if (emp.isPresent()) {
            return emp.get();
        }

        return null;
    }

    
    @PutMapping("/update/{id}")
    public Employee updateEmployee(@PathVariable long id,
                                   @RequestBody Employee employee) {

        Optional<Employee> emp = repo.findById(id);

        if (emp.isPresent()) {

            Employee existingEmployee = emp.get();

            existingEmployee.setName(employee.getName());
            existingEmployee.setSalary(employee.getSalary());

            return repo.save(existingEmployee);
        }

        return null;
    }

   
    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable long id) {

        repo.deleteById(id);

        return "Employee Deleted Successfully";
    }
}
