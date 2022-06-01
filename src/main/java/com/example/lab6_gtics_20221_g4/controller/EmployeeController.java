package com.example.lab6_gtics_20221_g4.controller;

import com.example.lab6_gtics_20221_g4.entity.Employee;
import com.example.lab6_gtics_20221_g4.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/lista")
    public String listarTodos(Model model){
        List<Employee> listaEmpleados = employeeRepository.findAll();
        model.addAttribute("listaEmpleados", listaEmpleados);
        return "/lista";
    }

    @PostMapping("/calcularrenta")
    public String calcularRenta(RedirectAttributes attr, int id){
        Optional<Employee> optEmpleado = employeeRepository.findById(id);
        Employee e = optEmpleado.get();
        if(optEmpleado.isEmpty()){return "redirect:/lista";}
        attr.addFlashAttribute("renta", employeeRepository.getRentaById(id));
        attr.addFlashAttribute("nombres", e.getFirstName()+" "+e.getLastName());
        return "redirect:/lista";
    }
}
