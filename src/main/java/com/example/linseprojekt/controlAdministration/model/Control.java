package com.example.linseprojekt.controlAdministration.model;

import com.example.linseprojekt.customerAdministration.model.Customer;
import com.example.linseprojekt.employeeAdministration.model.Employee;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity //definere, at klassen hører til en tabel i databasen
@Getter //laver automatisk getter og setter til klassen
@Setter
public class Control {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long controlId;


    @JsonFormat(pattern = "dd-MM-yyyy")
    private String date;
    private String time;

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private Customer customer;


    public Control(Customer customer, Employee employee, String date, String time) {
        this.customer = customer;
        this.employee = employee;
        this.date = date;
        this.time = time;
    }

    public Control() {

    }

    public Control(Customer customer, Employee employee) {
        this.customer = customer;
        this.employee = employee;
    }
}
