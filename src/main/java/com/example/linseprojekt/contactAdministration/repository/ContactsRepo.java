package com.example.linseprojekt.contactAdministration.repository;

import com.example.linseprojekt.contactAdministration.model.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//repository klassen, henter metoder fra JpaRepository, som er et interface indbygget i programmet der bruges til at lave databasen automatisk
@Repository
public interface ContactsRepo extends JpaRepository<Contacts, Long> {

}
