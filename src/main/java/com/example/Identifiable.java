package com.example;
import java.lang.reflect.Field;

public abstract class Identifiable {
    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id != null && !id.isBlank() && id.length() <= 4) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("ID should not be empty and must be 4 digits or fewer.");
        }
    }

    public void print() {
        System.out.println(this.toString());
    }

}
