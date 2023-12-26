package com.example.Pr5.controllers;

import com.example.Pr5.model.Employee;
import com.example.Pr5.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public String showAllEmployees(Model model) {
        List<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        return "employee/employeeList";
    }

    @GetMapping("/{id}")
    public String showEmployeeDetails(@PathVariable Long id, Model model) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        model.addAttribute("employee", employee);
        return "employee/employeeDetails";
    }

    @GetMapping("/new")
    public String showEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee/employeeForm";
    }

    @PostMapping
    public String createEmployee(@ModelAttribute Employee employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "employee/employeeForm";
        }

        employeeRepository.save(employee);
        model.addAttribute("employees", employeeRepository.findAll());

        return "employee/employeeList";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        model.addAttribute("employee", employee);
        return "employee/editEmployeeForm";
    }

    @PostMapping("/{id}/edit")
    public String updateEmployee(@PathVariable Long id, @ModelAttribute Employee employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            employee.setId(id);
            return "employee/editEmployeeForm";
        }

        employeeRepository.save(employee);
        model.addAttribute("employees", employeeRepository.findAll());
        return "employee/employeeList";
    }

    @GetMapping("/{id}/delete")
    public String deleteEmployee(@PathVariable Long id, Model model) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        employeeRepository.delete(employee);
        model.addAttribute("employees", employeeRepository.findAll());
        return "employee/employeeList";
    }
}
