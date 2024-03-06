package com.technophaa.employeeManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.technophaa.employeeManagementSystem.model.Employee;
import com.technophaa.employeeManagementSystem.service.EmployeeService;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

 // Display list of all employees
    @GetMapping("/employee")
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "employee";
    }
    
 // Display form to add a new employee
    @GetMapping("/employee/new")
    public String createEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "create_employee";
    }

 // Display form to edit an existing employee
    @GetMapping("/employee/edit/{id}")
    public String editEmployeeForm(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        return "edit_employee";
    }

 // Handler to process creation of a new employee
    @PostMapping("/employee")
    public String createEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/employee";
    }

 // Handler to process updating an employee
    @PostMapping("/employee/{id}")
    public String updateEmployee(@PathVariable Long id, @ModelAttribute("employee") Employee employee, Model model) {
        Employee existingEmployee = employeeService.getEmployeeById(id);
        existingEmployee.setId(id);
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        employeeService.updateEmployee(existingEmployee);
        return "redirect:/employee";
    }
    
 // Handler to process deletion of an employee
    @GetMapping("/employee/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
        return "redirect:/employee";
    }
}
