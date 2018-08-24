package com.kenzan.controller;

import com.kenzan.model.Employee;
import com.kenzan.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeControllerImpl implements EmployeeController {

  // Static Auth Key to delete employees
  private final String AUTH_KEY = "FyjmRx6sg";

  @Autowired
  private EmployeeService employeeService;

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<?> getEmployee(@PathVariable("id") Long id) {
    Employee emp = employeeService.getEmployee(id);
    if (emp != null) {
      return new ResponseEntity<>(emp, HttpStatus.FOUND);
    }

    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
  }

  @RequestMapping(method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<?> getActiveEmployees() {
    return new ResponseEntity<>(employeeService.getEmployees(), HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> createEmployee(@RequestBody Employee emp) {
    // TODO: Preconditions.checkNotNull(resource);
    Long newEmpId = employeeService.createEmployee(emp);

    if (newEmpId != null) {
      return new ResponseEntity<>(newEmpId, HttpStatus.CREATED);
    }

    return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public ResponseEntity<?> update(@PathVariable( "id" ) Long id, @RequestBody Employee emp) {
    // TODO: Preconditions.checkNotNull(resource);
    Long updatedEmpId = employeeService.updateEmployee(id, emp);

    if (updatedEmpId != null) {
      return new ResponseEntity<>(updatedEmpId, HttpStatus.ACCEPTED);
    }

    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> delete(@PathVariable("id") Long id,
                                  @RequestHeader(value = "Auth-Key", required = true) String authKey) {
    if (!authKey.equals(AUTH_KEY)) {
      return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    Long deletedEmployeeId = employeeService.deleteEmployee(id);

    if (deletedEmployeeId != null) {
      return new ResponseEntity<>(deletedEmployeeId, HttpStatus.ACCEPTED);
    }

    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
  }
}