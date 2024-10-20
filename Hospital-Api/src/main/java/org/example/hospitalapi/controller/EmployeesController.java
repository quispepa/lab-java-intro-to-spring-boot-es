package org.example.hospitalapi.controller;

import org.example.hospitalapi.enums.EmployeeStatus;
import org.example.hospitalapi.model.Employee;
import org.example.hospitalapi.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/employees")
@RestController
public class EmployeesController {

  @Autowired
  EmployeesRepository employeesRepository;

  @GetMapping("/getAllEmployees")
  public ResponseEntity<List<Employee>> allEmployes(){
    List<Employee> employee = employeesRepository.findAll();
    if(!employee.isEmpty()){
      return ResponseEntity.ok(employee);
    }else{
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/findById/{id}")
  public ResponseEntity<Employee> getEmployesById(
          @PathVariable Long id
  ){
    Optional<Employee> employee = employeesRepository.findAllById(id);
    if(employee.isPresent()){
      return ResponseEntity.ok(employee.get());
    }else{
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/findByStatus/{status}")
  public ResponseEntity<List<Employee>> getEmployesByStatus(
          @PathVariable int status
  ){
    List<Employee> employee;
    if (status == 0){
     employee = employeesRepository.findAllByEmployeeStatus(EmployeeStatus.ON_CALL);
      return somethingToResponse(employee);
    }else if (status == 1){
     employee = employeesRepository.findAllByEmployeeStatus(EmployeeStatus.ON);
      return somethingToResponse(employee);
    }else if (status == 2){
     employee = employeesRepository.findAllByEmployeeStatus(EmployeeStatus.OFF);
     return somethingToResponse(employee);
    }else {
      return ResponseEntity.notFound().build();
    }
  }

  public ResponseEntity<List<Employee>> somethingToResponse(List<Employee> employee){
    if(!employee.isEmpty()){
      return ResponseEntity.ok(employee);
    }else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/findByDepartment/{department}")
  public ResponseEntity<List<Employee>> getEmployesByDepartment(
          @PathVariable String department
  ){
    List<Employee> employee = employeesRepository.findAllByDepartment(department);
    if(!employee.isEmpty()){
      return ResponseEntity.ok(employee);
    }else{
      return ResponseEntity.notFound().build();
    }
  }


}
