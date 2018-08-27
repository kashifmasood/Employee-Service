package com.kenzan.employee;

import com.kenzan.EmployeeApplication;
import com.kenzan.enums.Status;
import com.kenzan.model.Employee;
import com.kenzan.repository.EmployeeRepository;
import com.kenzan.service.EmployeeServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {EmployeeApplication.class, EmployeeServiceImpl.class})
public class EmployeeSvcTest {

  @Autowired
  private EmployeeRepository employeeRepository;

  @Autowired
  private EmployeeServiceImpl employeeService;

  @Test
  public void testTotalCount() {
    long count = employeeService.getEmployees().size();
    assert(count == 3);
  }

  @Test
  public void testFindById() {
    Employee emp = employeeService.getEmployee(new Long(102));
    assert(emp != null);
  }

  @Test
  public void testFindByIdFail() {
    Employee emp = employeeService.getEmployee(new Long(300));
    assert(emp == null);
  }

  @Test
  public void testCreateEmployee() {
    try {
      long originalCount = employeeService.getEmployees().size();

      Employee emp = new Employee(
              new Long(200),
              "Michael",
              "R",
              "Jackson",
              new SimpleDateFormat("yyyy-MM-dd").parse("1980-12-15"),
              new SimpleDateFormat("yyyy-MM-dd").parse("2000-1-1"),
              Status.ACTIVE);

      employeeService.createEmployee(emp);

      assert (employeeService.getEmployees().size() == originalCount + 1);
    }
    catch (ParseException p) {
      System.err.println("Error parsing date: " + p.getMessage());
    }
  }

  // Try and create an employee with a duplicate ID
  @Test
  public void testCreateEmployeeFail() {
    try {
      long originalCount = employeeService.getEmployees().size();

      Employee emp = new Employee(
              new Long(103),
              "Michael",
              "R",
              "Jackson",
              new SimpleDateFormat("yyyy-MM-dd").parse("1980-12-15"),
              new SimpleDateFormat("yyyy-MM-dd").parse("2000-1-1"),
              Status.ACTIVE);

      Long id = employeeService.createEmployee(emp);

      assert (id == null && employeeService.getEmployees().size() == originalCount);
    }
    catch (ParseException p) {
      System.err.println("Error parsing date: " + p.getMessage());
    }
  }

  @Test
  public void testUpdateEmployee() {
    Employee employee = employeeService.getEmployee(new Long(101));

    if (employee != null) {
      employee.setFirstName(employee.getFirstName() + "XX");
      employee.setLastName(employee.getLastName() + "XX");

      employeeService.updateEmployee(employee.getId(), employee);

      Employee updatedEmployee = employeeService.getEmployee(new Long(101));
      if (updatedEmployee != null) {
        assert (updatedEmployee.getFirstName().endsWith("XX"));
        assert (updatedEmployee.getLastName().endsWith("XX"));
      }
    }
  }

  @Test
  public void testDeleteEmployee() {
    Long empId = new Long (101);
    employeeService.deleteEmployee(empId);

    Optional<Employee> emp = employeeRepository.findById(empId);
    assert(!emp.isPresent());
  }

  @Test
  public void testDeleteEmployeeFail() {
    Long empId = new Long (1090);
    Long deletedEmpId = employeeService.deleteEmployee(empId);

    assert(deletedEmpId == null);
  }
}
