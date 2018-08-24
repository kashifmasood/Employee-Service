package com.kenzan.service;

import com.kenzan.model.Employee;

import java.util.List;

public interface EmployeeService {
  Employee getEmployee(Long id);
  List<Employee> getEmployees();
  Long createEmployee(Employee emp);
  Long updateEmployee(Long id, Employee emp);
  Long deleteEmployee(Long employeeId);
}
