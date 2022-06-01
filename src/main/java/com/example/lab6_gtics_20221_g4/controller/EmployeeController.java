package com.example.lab6_gtics_20221_g4.controller;

import com.example.lab6_gtics_20221_g4.entity.Employee;
import com.example.lab6_gtics_20221_g4.repository.EmployeeRepository;
import com.example.lab6_gtics_20221_g4.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    JobRepository jobRepository;

    @GetMapping("/empleados/lista")
    public String listarTodos(Model model){
        List<Employee> listaEmpleados = employeeRepository.findAll();
        model.addAttribute("listaEmpleados", listaEmpleados);
        return "empleados/lista";
    }

    @PostMapping("/calcularrenta")
    public String calcularRenta(RedirectAttributes attr, int id) {
        Optional<Employee> optEmpleado = employeeRepository.findById(id);
        Employee e = optEmpleado.get();
        if (optEmpleado.isEmpty()) {
            return "redirect:/empleados/lista";
        }
        attr.addFlashAttribute("renta", employeeRepository.getRentaById(id));
        attr.addFlashAttribute("nombres", e.getFirstName() + " " + e.getLastName());
        return "redirect:/empleados/lista";
    }


    @GetMapping("/empleados/nuevo")
    public String nuevoEmpleado(@ModelAttribute("empleado")Employee empleado,Model model){
         model.addAttribute("trabajos",jobRepository.findAll()) ;
        return "empleados/form";
    }

    @PostMapping("/empleados/guardar")
    public String guardarEmpleado(@ModelAttribute("empleado") @Valid Employee empleado, BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("trabajos",jobRepository.findAll());
            System.out.println(empleado.getSalary());
            return "empleados/form";
        }
        employeeRepository.save(empleado);
        redirectAttributes.addFlashAttribute("msg","Se creo el usuario exitosamente");
        return "redirect:/empleados/lista";
    }

}
