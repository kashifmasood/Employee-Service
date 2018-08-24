package com.kenzan.controller;

import com.kenzan.model.Employee;
import org.springframework.http.ResponseEntity;

public interface EmployeeController {
  ResponseEntity<?> getEmployee(Long id);
  ResponseEntity<?> createEmployee(Employee emp);
  ResponseEntity<?> update(Long id, Employee emp);
  ResponseEntity<?> delete(Long id, String authKey);
  ResponseEntity<?> getActiveEmployees();
}
