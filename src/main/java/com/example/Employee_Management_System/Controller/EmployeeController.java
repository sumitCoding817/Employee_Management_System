package com.example.Employee_Management_System.Controller;

import com.example.Employee_Management_System.Entity.Employee;
import com.example.Employee_Management_System.Service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> createEmp(@Valid @RequestBody Employee employee){
        Employee emp=employeeService.createEmployee(employee);
        return  new ResponseEntity<>(
                emp,
                HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmp(){
        List<Employee>allEmp=employeeService.getAllEmployee();
        return new ResponseEntity<>(
                allEmp,
                HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmpById(@PathVariable Long id){
        Employee emp=employeeService.getEmployeeById(id);

        return new ResponseEntity<>(
                emp,
                HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmp(@RequestBody Employee employee,@PathVariable Long id){
        Employee updatedEmp=employeeService.updateEmployee(employee,id);
        return new ResponseEntity<>(
                updatedEmp,
                HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmp(@PathVariable Long id){
         employeeService.deleteEmployee(id);
         return new ResponseEntity<>(
                "Employee deleted successfully",
                HttpStatus.OK);
    }
}
