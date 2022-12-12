package com.example.linseprojekt;



import java.util.Optional;
import java.util.Set;

//definerer de metoder, der bruges til CRUD som bliver implementeret i de forskellige service klasser
public interface ICRUD <T, ID>{

    Set<T> findAll();
    T save(T object);
    void delete(Optional<T> object);
    void deleteById(ID Id);
    Optional<T> findById(ID Id);

}
