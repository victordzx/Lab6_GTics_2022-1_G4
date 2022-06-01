package com.example.lab6_gtics_20221_g4.repository;

import com.example.lab6_gtics_20221_g4.dto.EmployeeSueldo;
import com.example.lab6_gtics_20221_g4.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query(value = "CALL lista_sueldo_mayor(:sueldo);", nativeQuery = true)
    List<EmployeeSueldo> findSueldoMayor(float sueldo);

    @Query(nativeQuery = true, value = "select calcular_impuesto(e.salary) from employees e where e.employee_id = ?1")
    float getRentaById(int id);

}
