package com.example.observerPattern;

public record NameService (Name name) implements Observer{
    @Override
    public void update(NameService event) {
        System.out.println("this service is belongs to " + name);
    }
}
