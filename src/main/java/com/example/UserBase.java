package com.example;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
@Table(name = "app_user")
public abstract class UserBase implements User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

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
