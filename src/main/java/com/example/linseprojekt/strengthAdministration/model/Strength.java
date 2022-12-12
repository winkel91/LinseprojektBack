package com.example.linseprojekt.strengthAdministration.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity //definere, at klassen hører til en tabel i databasen
@Getter //laver automatisk getter og setter til klassen
@Setter
public class Strength {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long strengthId;

    private double radius;
    private double diameter;
    private double sphere;
    private double cylinder;
    private int axe;
    private double addition;

    public Strength(double radius, double diameter, double sphere, double cylinder, int axe, double addition) {
        this.radius = radius;
        this.diameter = diameter;
        this.sphere = sphere;
        this.cylinder = cylinder;
        this.axe = axe;
        this.addition = addition;
    }

    public Strength() {

    }
}
