package com.example.linseprojekt.contactAdministration.service;

import com.example.linseprojekt.contactAdministration.model.Contacts;
import com.example.linseprojekt.contactAdministration.repository.ContactsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

//service klasserne, henter metoder fra CRUD-klassen og repository klassen
@Service
public class ContactsService implements IContactsService {

    private ContactsRepo contactsRepo;

    @Autowired
    public ContactsService(ContactsRepo contactsRepo) {
        this.contactsRepo = contactsRepo;
    }

    @Override
    public Set<Contacts> findAll() {
        Set<Contacts> set = new HashSet<>(contactsRepo.findAll());
        return set;
    }

    @Override
    public Contacts save(Contacts object) {
        return contactsRepo.save(object);
    }

    @Override
    public void delete(Optional<Contacts> object) {

    }

    @Override
    public void deleteById(Long Id) {
        contactsRepo.deleteById(Id);
    }

    @Override
    public Optional<Contacts> findById(Long Id) {
        return contactsRepo.findById(Id);
    }
}
