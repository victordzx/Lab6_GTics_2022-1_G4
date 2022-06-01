package com.example.lab6_gtics_20221_g4.controller;

import com.example.lab6_gtics_20221_g4.dto.EmployeeSueldo;
import com.example.lab6_gtics_20221_g4.entity.Employee;
import com.example.lab6_gtics_20221_g4.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class ProceduresController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/listasueldo")
    public String listarSueldo(Model model, Optional<String> sueldo){
        float sueldof;

        if(sueldo.isEmpty()){
            sueldof = 2500;
        }else{
            try{
                sueldof = Float.parseFloat(sueldo.get());
            }catch (Exception e){
                sueldof = 2500;
            }
        }

        List<EmployeeSueldo> listaEmpleados = employeeRepository.findSueldoMayor(sueldof);
        model.addAttribute("listaEmpleados", listaEmpleados);
        model.addAttribute("sueldo", sueldof);
        return "/listasueldo";
    }

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
