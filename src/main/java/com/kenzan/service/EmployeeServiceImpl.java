package com.kenzan.service;

import com.kenzan.enums.Status;
import com.kenzan.model.Employee;
import com.kenzan.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired
  private EmployeeRepository employeeRepository;

  @Override
  public Employee getEmployee(Long id) {
    Optional<Employee> emp = employeeRepository.findById(id);

    if(emp.isPresent()) {
      return emp.get();
    }

    // TODO: throw clean exceptions here rather than null
    return null;
  }

  public List<Employee> getEmployees() {
    return employeeRepository.getAll();
  }

  public Long createEmployee(Employee emp) {
    // TODO: validate employee object

    // Test for duplicate
    Optional<Employee> currEmp = employeeRepository.findById(emp.getId());

    if (!currEmp.isPresent()) {
      emp.setStatus(Status.ACTIVE); // Set status to ACTIVE for all employees by default
      Employee newEmp = employeeRepository.save(emp);

      if (newEmp != null) {
        return newEmp.getId();
      }
    }

    // TODO: throw clean exceptions here rather than null
    return null;
  }

  public Long updateEmployee(Long id, Employee updatedEmployee) {
    // TODO: validate employee object
    Optional<Employee> emp = employeeRepository.findById(id);

    emp.ifPresent(employee -> {
      employee.setId(id);
      employee.setFirstName(updatedEmployee.getFirstName());
      employee.setMiddleInitial(updatedEmployee.getMiddleInitial());
      employee.setLastName(updatedEmployee.getLastName());
      employee.setDateOfBirth(updatedEmployee.getDateOfBirth());
      employee.setDateOfEmployment(updatedEmployee.getDateOfEmployment());
      employee.setStatus(Status.ACTIVE); // Set status to ACTIVE for all employees by default
      employeeRepository.save(employee);
    });

    // TODO: throw clean exceptions here rather than null
    return (emp.isPresent() ? id : null);
  }

  public Long deleteEmployee(Long id) {
    Optional<Employee> emp = employeeRepository.findById(id);

    emp.ifPresent(employee -> {
      employee.setStatus(Status.INACTIVE);

      employeeRepository.save(employee);
    });

    // TODO: throw clean exceptions here rather than null
    return (emp.isPresent() ? id : null);
  }
}
