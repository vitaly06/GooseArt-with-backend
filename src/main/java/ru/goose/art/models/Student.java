package ru.goose.art.models;

public class Student {
    String name, number;
    private String groupe;
    private String special;
    private String status;

    public Student() {
    }

    public Student(String name, String number, String groupe, String special, String status) {
        this.name = name;
        this.number = number;
        this.groupe = groupe;
        this.special = special;
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

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
