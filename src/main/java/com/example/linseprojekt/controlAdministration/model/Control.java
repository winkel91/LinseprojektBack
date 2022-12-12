package com.example.linseprojekt.controlAdministration.model;

import com.example.linseprojekt.customerAdministration.model.Customer;
import com.example.linseprojekt.employeeAdministration.model.Employee;
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

    private Date date;
    private String time;

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private Customer customer;


    public Control(Customer customer, Employee employee, Date date, String time) {
        this.customer = customer;
        this.employee = employee;
        this.date = date;
        this.time = time;
    }

    public Control() {

    }
}
