package com.example.lab6_gtics_20221_g4.controller;

import com.example.lab6_gtics_20221_g4.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping(value = {"/empleados/lista"})
    public String listaEmpleados(Model model) {
        model.addAttribute("employees", employeeRepository.EmpleadosSueldo());
        return "empleados/lista";
    }
}
