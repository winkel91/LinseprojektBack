package com.example.linseprojekt.controlAdministration.repository;

import com.example.linseprojekt.controlAdministration.model.Control;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControlRepo extends JpaRepository<Control, Long> {
}
