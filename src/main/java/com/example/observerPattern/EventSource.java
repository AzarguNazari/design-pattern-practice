package com.example.observerPattern;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class EventSource {

    private final Set<Observer> observers = new HashSet<>();

    public void notifyObservers(NameService event) {
        observers.forEach(observer -> observer.update(event));
    }

    public void addObserver(Observer observer) {
        if(!exists(observer)){
            observers.add(observer);
        }
    }

    private boolean exists(Observer observer){
        return observers.stream().anyMatch(ob -> ob.equals(observer));
    }
}
