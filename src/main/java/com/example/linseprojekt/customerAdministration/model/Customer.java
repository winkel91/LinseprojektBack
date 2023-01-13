package com.example.linseprojekt.customerAdministration.model;

import com.example.linseprojekt.contactAdministration.model.Contacts;
import com.example.linseprojekt.controlAdministration.model.Control;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.Date;

@Entity //definere, at klassen hører til en tabel i databasen
@Getter //laver automatisk getter og setter til klassen
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String customerFullName;
    private String customerCPR;
    private String customerAddress;
    private String customerPhoneNumber;
    private String customerEmail;
    private String lastControl;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private File eyePicture;
    private String eyeComment;
    @OneToOne(cascade = CascadeType.ALL)
    private Contacts contacts;

    private String subscriptionStart;
    private String subscriptionEnd;

    private boolean subscriptionType;

    public Customer(String customerFullName, String customerCPR, String customerAddress, String customerPhoneNumber) {
        this.customerFullName = customerFullName;
        this.customerCPR = customerCPR;
        this.customerAddress = customerAddress;
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public Customer() {
    }

    public Customer(String customerFullName, String customerCPR, String customerAddress, String customerPhoneNumber, String customerEmail, boolean subscriptionType, String lastControl) {
        this.customerFullName = customerFullName;
        this.customerCPR = customerCPR;
        this.customerAddress = customerAddress;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerEmail = customerEmail;
        this.subscriptionType = subscriptionType;
        this.lastControl = lastControl;
    }


}
