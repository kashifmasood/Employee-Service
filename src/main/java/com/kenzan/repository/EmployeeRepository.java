package com.kenzan.repository;

import java.util.List;
import java.util.Optional;

import com.kenzan.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    @Query("SELECT e FROM Employee e WHERE e.id = :id AND e.status = 'ACTIVE'")
    Optional<Employee> findById(@Param("id") Long id);

    @Query("SELECT e FROM Employee e WHERE e.status = 'ACTIVE'")
    List<Employee> getAll();
}