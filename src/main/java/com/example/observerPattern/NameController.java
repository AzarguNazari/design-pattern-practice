package com.example.observerPattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@ConditionalOnProperty(name = "observer-pattern-switch", havingValue = "on")
@Service
@EnableScheduling
public class NameController {

    @Autowired
    private EventSource eventSource;

    private NameService mahmood = new NameService(Name.MAHMOOD);
    private NameService ahmad = new NameService(Name.AHMAD);
    private NameService nabi = new NameService(Name.NABI);
    private NameService karim = new NameService(Name.KARIM);

    List<Name> names = List.of(Name.NABI, Name.AHMAD, Name.KARIM, Name.MAHMOOD);
    AtomicInteger counter = new AtomicInteger(0);

    @Scheduled(fixedRate = 1000)
    public void run() {
        sendNames(names.get(counter.getAndIncrement() % names.size()));
    }

    public void sendNames(Name name){
        switch(name){
            case MAHMOOD -> {
                eventSource.registerObserver(mahmood);
                eventSource.notifyObservers(mahmood);
            }
            case NABI -> {
                eventSource.registerObserver(nabi);
                eventSource.notifyObservers(nabi);
            }
            case AHMAD -> {
                eventSource.registerObserver(ahmad);
                eventSource.notifyObservers(ahmad);
            }
            case KARIM -> {
                eventSource.registerObserver(karim);
                eventSource.notifyObservers(karim);
            }
            default -> System.out.println("we don't support service");
        }

    }
}
