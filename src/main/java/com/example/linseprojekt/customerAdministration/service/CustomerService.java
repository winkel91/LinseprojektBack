package com.example.linseprojekt.customerAdministration.service;

import com.example.linseprojekt.customerAdministration.model.Customer;
import com.example.linseprojekt.customerAdministration.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomerService implements ICustomerService{
    private CustomerRepo customerRepo;

    @Autowired
    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }



    @Override
    public Set<Customer> findAll() {
        Set<Customer> set = new HashSet<>(customerRepo.findAll());
        return set;
    }

    @Override
    public Customer save(Customer object) {
        return customerRepo.save(object);
    }

    @Override
    public void delete(Optional<Customer> object) {

    }

    @Override
    public void deleteById(Long Id) {
        customerRepo.deleteById(Id);

    }

    @Override
    public Optional<Customer> findById(Long Id) {
        return customerRepo.findById(Id);
    }
}
