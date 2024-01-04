package ru.goose.art.models;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;

public class Person {
    @NotEmpty
    private String name;
    @NotEmpty
    private String number;
    public Person(){

    }
    public Person(String name, String number){
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
