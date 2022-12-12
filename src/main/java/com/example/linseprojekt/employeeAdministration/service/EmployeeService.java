package com.example.linseprojekt.employeeAdministration.service;

import com.example.linseprojekt.employeeAdministration.model.Employee;
import com.example.linseprojekt.employeeAdministration.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeService implements IEmployeeService{

    private EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo){
        this.employeeRepo = employeeRepo;
    }

    @Override
    public Set<Employee> findAll() {
        Set<Employee> set = new HashSet<>(employeeRepo.findAll());
        return set;
    }

    @Override
    public Employee save(Employee object) {
        return employeeRepo.save(object);
    }

    @Override
    public void delete(Optional<Employee> object) {

    }

    @Override
    public void deleteById(Long Id) {
        employeeRepo.deleteById(Id);

    }

    @Override
    public Optional<Employee> findById(Long Id) {
        return employeeRepo.findById(Id);
    }
}
