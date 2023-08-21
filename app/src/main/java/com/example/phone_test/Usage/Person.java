package com.example.phone_test.Usage;

public class Person {
    private String name;
    private String emai;

    public String getEmai() {
        return emai;
    }

    public void setEmai(String emai) {
        this.emai = emai;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Person(){

    }
    public Person(String name, String emai) {
        this.name = name;
        this.emai = emai;
    }
}
