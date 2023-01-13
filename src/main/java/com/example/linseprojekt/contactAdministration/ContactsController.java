package com.example.linseprojekt.contactAdministration;

import com.example.linseprojekt.contactAdministration.model.Contacts;
import com.example.linseprojekt.contactAdministration.service.ContactsService;
import com.example.linseprojekt.customerAdministration.model.Customer;
import com.example.linseprojekt.customerAdministration.service.CustomerService;
import com.example.linseprojekt.strengthAdministration.model.Strength;
import com.example.linseprojekt.strengthAdministration.service.StrengthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ContactsController {
    private ContactsService contactsService;
    private CustomerService customerService;
    private StrengthService strengthService;

    @Autowired
    public ContactsController(ContactsService contactsService, CustomerService customerService, StrengthService strengthService) {
        this.contactsService = contactsService;
        this.customerService = customerService;
        this.strengthService = strengthService;
    }

    @PostMapping("/addContacts")
    public ResponseEntity<Contacts> addContacts(@RequestParam("customerIdNumber") Long customerIdNumber,
                                                @RequestParam("frequency") String frequency,
                                                @RequestParam("packetType") String packetType,
                                                @RequestParam("supplier") String supplier,
                                                @RequestParam("radius") double radius,
                                                @RequestParam("diameter") double diameter,
                                                @RequestParam("sphere") double sphere,
                                                @RequestParam("cylinder") double cylinder,
                                                @RequestParam("axe") int axe,
                                                @RequestParam("addition") double addition) {
        Strength strength = new Strength(radius, diameter, sphere, cylinder, axe, addition); //opretter et nyt strength objekt, ud fra de parameter der sendes fra html
        Contacts contacts = new Contacts(frequency, packetType, supplier, strength); //opretter et nyt kontaktlinse object, ud fra parameter + det nye styrke objekt
        Customer customer = customerService.findById(customerIdNumber).get(); //henter den kunde, der er connected til det id der sendes i paramenteren
        customer.setContacts(contacts); //setter det nye kontaktlinse objekt, til kundens kontaktlinse attribut
            contactsService.save(contacts); //gemmer kontaktlinse objektet i databasen
            if (contactsService.save(contacts) != null) { //hvis ikke objektet allerede er gemt, så sender den en HttpStatus.OK
                return new ResponseEntity<>(contacts, HttpStatus.OK);
            } else { //hvis objektet allerede er gemt, sender den en bad request.
                return new ResponseEntity<>(contacts, HttpStatus.BAD_REQUEST);
            }
        }


        @PostMapping("/editContacts")
        public ResponseEntity<Contacts> editContacts (@RequestParam("contactsId") Long contactsId,
                @RequestParam("packetType") String packetType,
                @RequestParam("supplier") String supplier){
            Contacts newContacts = new Contacts(packetType, supplier); //laver et nyt kontaktlinse objekt, med de sendte parameter
            Optional<Contacts> oldContacts = contactsService.findById(contactsId); //finder det kontaktlinse objekt, som ønskes ændret
            if (oldContacts.isPresent()) { //hvis objektet er gemt i databasen, går den videre her
                newContacts.setContactId(contactsId); //gemmer det nye objekt, under det ID den gamle havde, så de nye informationer overrider de gamle
                contactsService.save(newContacts); //gemmer det nye objekt
                return new ResponseEntity<>(newContacts, HttpStatus.OK);
            } else { //hvis objektet ikke findes i databasen, sendes en bad request
                return new ResponseEntity<>(newContacts, HttpStatus.BAD_REQUEST);
            }
        }

        @DeleteMapping("/deleteContacts")
        public ResponseEntity<String> deleteContacts (@RequestParam("contactsId") Long contactsId){
            String msg = ""; //opretter en string, der skal defineres senere
            Optional<Contacts> contacts = contactsService.findById(contactsId); //finder det objekt, der søges efter med det ID der er sendt i parameter
            if (contacts.isPresent()) { //hvis objektet findes i databasen går den videre her
                contactsService.deleteById(contactsId); //sletter det objekt i databasen, der er gemt under ID'et
                msg = "Linser med ID: " + contactsId + " er slettet."; //definere den string, der blev oprettet tidligere
                return new ResponseEntity<>(msg, HttpStatus.OK); //returnerer stringen til interfaces
            } else { //hvis ikke objektet findes i går den videre her
                msg = "Linser med ID " + contactsId + " er kunne ikke findes.";
                return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
            }
        }

        //denne metode fungerer ligesom addContacts, hvor der oprettes et nyt styrke objekt som gemmes i databasen
        @PostMapping("/addStrength")
        public ResponseEntity<Strength> addStrength ( @RequestParam("radius") double radius,
        @RequestParam("diameter") double diameter,
        @RequestParam("sphere") double sphere,
        @RequestParam("cylinder") double cylinder,
        @RequestParam("axe") int axe,
        @RequestParam("addition") double addition){
            Strength strength = new Strength(radius, diameter, sphere, cylinder, axe, addition);
            strengthService.save(strength);
            if (strengthService.save(strength) != null) {
                return new ResponseEntity<>(strength, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(strength, HttpStatus.BAD_REQUEST);
            }
        }

        //fungerer ligesom editContacts
        @PostMapping("/editStrength")
        public ResponseEntity<Strength> editStrength (@RequestParam("strengthId") Long strengthId,
        @RequestParam("radius") double radius,
        @RequestParam("diameter") double diameter,
        @RequestParam("sphere") double sphere,
        @RequestParam("cylinder") double cylinder,
        @RequestParam("axe") int axe,
        @RequestParam("addition") double addition){
            Strength newStrength = new Strength(radius, diameter, sphere, cylinder, axe, addition);
            Optional<Strength> oldStrength = strengthService.findById(strengthId);
            if (oldStrength.isPresent()) {
                newStrength.setStrengthId(strengthId);
                strengthService.save(newStrength);
                return new ResponseEntity<>(newStrength, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(newStrength, HttpStatus.BAD_REQUEST);
            }
        }

        //fungerer som deleteContacts
        @DeleteMapping("/deleteStrength")
        public ResponseEntity<String> deleteStrength (@RequestParam("strengthId") Long strengthId){
            String msg = "";
            Optional<Strength> strength = strengthService.findById(strengthId);
            if (strength.isPresent()) {
                strengthService.deleteById(strengthId);
                msg = "Styrke med ID: " + strengthId + " er slettet.";
                return new ResponseEntity<>(msg, HttpStatus.OK);
            } else {
                msg = "Styrke med ID " + strengthId + " er kunne ikke findes.";
                return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
            }
        }
    }
