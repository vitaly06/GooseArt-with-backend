package ru.goose.art.models;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;

public class Person {
    @NotEmpty
    private String name;
    @NotEmpty
    private String number;
    private String status;
    public Person(){

    }
    public Person(String name, String number, String status){
        this.name = name;
        this.number = number;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
