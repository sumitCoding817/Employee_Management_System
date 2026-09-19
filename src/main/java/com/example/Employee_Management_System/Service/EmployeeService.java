package com.example.Employee_Management_System.Service;

import com.example.Employee_Management_System.Entity.Employee;
import com.example.Employee_Management_System.Exception.EmailAlreadyExistsException;
import com.example.Employee_Management_System.Exception.EmployeeNotFoundException;
import com.example.Employee_Management_System.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee){
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new EmailAlreadyExistsException(
                    "Employee with email already exists: "
                            + employee.getEmail()
            );
        }

        return employeeRepository.save(employee);

    }

    public List<Employee> getAllEmployee(){
        List<Employee>allEmp=employeeRepository.findAll();
        if (allEmp.isEmpty()){
            throw new EmployeeNotFoundException("No employees found");
        }
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id){
        return employeeRepository.findById(id).orElseThrow(()->
                new EmployeeNotFoundException("Employee not found with id: "+id));
    }

    public Employee updateEmployee(Employee employee,Long id){
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(()->
                new EmployeeNotFoundException("Employee not found with id: "+id));

        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setDepartment(employee.getDepartment());
        existingEmployee.setSalary(employee.getSalary());

        return employeeRepository.save(existingEmployee);
    }

    public void deleteEmployee(Long id){
        Employee empID=employeeRepository.findById(id).orElseThrow(()->
                new EmployeeNotFoundException("Employee not found with id: "+id));
        employeeRepository.deleteById(empID.getId());
//        return "Employee deleted successfully";
    }
}
