package com.example.observerPattern;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class EventSource {

    private final Set<Observer> observers = new HashSet<>();

    public void notifyObservers(NameService event) {
        observers.forEach(Observer::update);
    }

    public void registerObserver(Observer observer) {
        if(!exists(observer)){
            observers.add(observer);
        }
    }

    public void unregisterObserver(Observer observer){
        if(!exists(observer)){
            observers.remove(observer);
        }
    }

    private boolean exists(Observer observer){
        return observers.stream().anyMatch(ob -> ob.equals(observer));
    }
}
