package com.kenzan.model;

import com.kenzan.enums.Status;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {
  @Id
  private Long id;
  private String firstName;
  private String middleInitial;
  private String lastName;
  @Temporal(TemporalType.DATE)
  private Date dateOfBirth;
  @Temporal(TemporalType.DATE)
  private Date dateOfEmployment;
  @Enumerated(EnumType.STRING)
  @Column(columnDefinition = "String default ACTIVE")
  private Status status;

  public Employee(){
    super();
  }

  public Employee(Long id, String fName, String mInitial, String lName, Date dob, Date hireDate, Status status) {
    this.id = id;
    this.firstName = fName;
    this.middleInitial = mInitial;
    this.lastName = lName;
    this.dateOfBirth = dob;
    this.dateOfEmployment = hireDate;
    this.status = status;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleInitial() {
    return middleInitial;
  }

  public void setMiddleInitial(String middleInitial) {
    this.middleInitial = middleInitial;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastname) {
    this.lastName = lastname;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public Date getDateOfEmployment() {
    return dateOfEmployment;
  }

  public void setDateOfEmployment(Date dateOfEmployment) {
    this.dateOfEmployment = dateOfEmployment;
  }

  public String getStatus() {
    return status.toString();
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public String toString() {
    return firstName;
  }
}

