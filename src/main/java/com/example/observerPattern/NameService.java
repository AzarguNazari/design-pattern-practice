package com.example.observerPattern;

public record NameService (Name name) implements Observer{
    @Override
    public void update() {
        System.out.println("this service is belongs to " + name);
    }
}
