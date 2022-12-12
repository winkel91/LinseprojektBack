package com.example.linseprojekt.employeeAdministration.repository;

import com.example.linseprojekt.employeeAdministration.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
}
