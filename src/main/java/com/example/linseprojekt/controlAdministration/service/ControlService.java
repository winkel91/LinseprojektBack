package com.example.linseprojekt.controlAdministration.service;

import com.example.linseprojekt.controlAdministration.model.Control;
import com.example.linseprojekt.controlAdministration.repository.ControlRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ControlService implements IControlService {

    private ControlRepo controlRepo;

    @Autowired
    public ControlService(ControlRepo controlRepo){
        this.controlRepo = controlRepo;
    }

    @Override
    public Set<Control> findAll() {
        Set <Control> set = new HashSet<>(controlRepo.findAll());
        return set;
    }

    @Override
    public Control save(Control object) {
        return controlRepo.save(object);
    }

    @Override
    public void delete(Optional<Control> object) {

    }

    @Override
    public void deleteById(Long Id) {
        controlRepo.deleteById(Id);
    }

    @Override
    public Optional<Control> findById(Long Id) {
        return controlRepo.findById(Id);
    }
}
