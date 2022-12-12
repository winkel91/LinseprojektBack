package com.example.linseprojekt.strengthAdministration.service;

import com.example.linseprojekt.ICRUD;
import com.example.linseprojekt.strengthAdministration.model.Strength;
import com.example.linseprojekt.strengthAdministration.repository.StrengthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class StrengthService implements ICRUD<Strength, Long> {

    private StrengthRepo strengthRepo;

    @Autowired
    public StrengthService(StrengthRepo strengthRepo) {
        this.strengthRepo = strengthRepo;
    }

    @Override
    public Set<Strength> findAll() {
        Set<Strength> set = new HashSet<>(strengthRepo.findAll());
        return set;
    }

    @Override
    public Strength save(Strength object) {
        return strengthRepo.save(object);
    }

    @Override
    public void delete(Optional<Strength> object) {

    }

    @Override
    public void deleteById(Long Id) {
        strengthRepo.deleteById(Id);
    }

    @Override
    public Optional<Strength> findById(Long Id) {
        return strengthRepo.findById(Id);
    }
}
