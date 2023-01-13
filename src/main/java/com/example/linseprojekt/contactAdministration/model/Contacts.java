package com.example.linseprojekt.contactAdministration.model;

import com.example.linseprojekt.customerAdministration.model.Customer;
import com.example.linseprojekt.strengthAdministration.model.Strength;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity //definere, at klassen hører til en tabel i databasen
@Getter //laver automatisk getter og setter til klassen
@Setter
public class Contacts {

    //generer automatisk id'et til klassen
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contactId;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonBackReference // stopper backreference, når man kalder på et objekt, der har et andet objekt som attribut, så får man ikke hele det andet objekt med
    private Strength strength;
    private String frequency;
    private String packetType;
    private String supplier;
    private int quantity;
    private double price;




    public Contacts(String packetType, String supplier){
        this.packetType = packetType;
        this.supplier = supplier;
    }

    public Contacts(String packetType, String supplier, Strength strength) {
    this.packetType = packetType;
    this.supplier = supplier;
    this.strength = strength;
    }

    public Contacts() {
    }

    public Contacts(Strength strength, String frequency, String packetType, String supplier, int quantity, double price) {
        this.strength = strength;
        this.frequency = frequency;
        this.packetType = packetType;
        this.supplier = supplier;
        this.quantity = quantity;
        this.price = price;
    }

    public Contacts(String frequency, String packetType, String supplier, Strength strength) {
    this.frequency = frequency;
    this.packetType = packetType;
    this.supplier = supplier;
    this.strength = strength;
    }
}

