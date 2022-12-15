package com.example.linseprojekt.customerAdministration.model;

import com.example.linseprojekt.contactAdministration.model.Contacts;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.io.File;
import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;

@Entity //definere, at klassen hører til en tabel i databasen
@Getter //laver automatisk getter og setter til klassen
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String customerName;
    private String customerCPR;
    private String customerAddress;
    private String customerPhoneNumber;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private File eyePicture;
    private String eyeComment;

    @OneToOne(cascade = CascadeType.ALL)
    private Contacts contacts;

    public Customer(String customerName, String customerCPR, String customerAddress, String customerPhoneNumber) {
        this.customerName = customerName;
        this.customerCPR = customerCPR;
        this.customerAddress = customerAddress;
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public Customer() {
    }

}
