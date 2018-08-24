package com.kenzan.employee;

import com.kenzan.enums.Status;
import com.kenzan.repository.EmployeeRepository;
import com.kenzan.model.Employee;
import com.kenzan.service.EmployeeServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepoTest {

  @Autowired
  private EmployeeRepository employeeRepository;

//  @Autowired
//  private EmployeeServiceImpl employeeService;

  @Test
  public void testTotalCount() {
    long count = employeeRepository.count();
    assert(count == 3);
  }

  @Test
  public void testFindById() {
    Optional<Employee> emp = employeeRepository.findById(new Long(102));
    assert(emp.isPresent());
  }

  @Test
  public void testGetAllActive() {
    List<Employee> emp = employeeRepository.getAll();
    assert(emp.size() == 3);
  }

  @Test
  public void testCreateEmployee() {
    try {
      long originalCount = employeeRepository.count();

      Employee emp = new Employee(
              new Long(200),
              "Michael",
              "R",
              "Jackson",
              new SimpleDateFormat("yyyy-MM-dd").parse("1980-12-15"),
              new SimpleDateFormat("yyyy-MM-dd").parse("2000-1-1"),
              Status.ACTIVE);

      employeeRepository.save(emp);

      assert (employeeRepository.count() == originalCount + 1);
    }
    catch (ParseException p) {
      System.err.println("Error parsing date: " + p.getMessage());
    }
  }

  @Test
  public void testUpdateEmployee() {
    Optional<Employee> emp = employeeRepository.findById(new Long(101));

    if (emp.isPresent()) {
      Employee employee = emp.get();
      employee.setFirstName(employee.getFirstName() + "XX");
      employee.setLastName(employee.getLastName() + "XX");

      employeeRepository.save(employee);

      Optional<Employee> updatedEmp = employeeRepository.findById(new Long(101));
      if (updatedEmp.isPresent()) {
        Employee updatedEmployee = updatedEmp.get();

        assert (updatedEmployee.getFirstName().endsWith("XX"));
        assert (updatedEmployee.getLastName().endsWith("XX"));
      }
    }
  }

//  @Test
//  public void testDeleteEmployee() {
//    employeeService.deleteEmployee(new Long(101));
//
//    Optional<Employee> emp = employeeRepository.findById(new Long(101));
//    assert(!emp.isPresent());
//  }
}
