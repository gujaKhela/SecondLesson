package com.example;


import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)  // Single table strategy
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)  // Column to differentiate between Admin and Client
@Table(name = "app_user")  // The table for all users
public abstract class UserBase implements User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    // Constructors, Getters, Setters

    public UserBase() {}

    public UserBase(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }


}
