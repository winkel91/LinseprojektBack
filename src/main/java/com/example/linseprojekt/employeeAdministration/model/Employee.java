package com.example.linseprojekt.employeeAdministration.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity //definere, at klassen hører til en tabel i databasen
@Getter //laver automatisk getter og setter til klassen
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    private String employeeName;


    public Employee(String employeeName){
        this.employeeName = employeeName;
    }

    public Employee() {

    }

}
