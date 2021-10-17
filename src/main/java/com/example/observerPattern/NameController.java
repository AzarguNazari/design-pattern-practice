package com.example.observerPattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
                eventSource.addObserver(mahmood);
                eventSource.notifyObservers(mahmood);
            }
            case NABI -> {
                eventSource.addObserver(nabi);
                eventSource.notifyObservers(nabi);
            }
            case AHMAD -> {
                eventSource.addObserver(ahmad);
                eventSource.notifyObservers(ahmad);
            }
            case KARIM -> {
                eventSource.addObserver(karim);
                eventSource.notifyObservers(karim);
            }
            default -> System.out.println("we don't support service");
        }

    }
}
