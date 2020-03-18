package com.okta.developer.jugtours;

import com.okta.developer.jugtours.model.Event;
import com.okta.developer.jugtours.model.Group;
import com.okta.developer.jugtours.model.GroupRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

@Component
class Initializer implements CommandLineRunner {

    private final GroupRepository repository;

    public Initializer(final GroupRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(final String... strings) {
        Stream.of("Denver JUG", "Utah JUG", "Seattle JUG", "Richmond JUG")
                .forEach(name -> repository.save(new Group(name)));

        final Group djug = repository.findByName("Denver JUG");
        final Event e = ((Object) Event
                .builder())
                .title("Full Stack Reactive")
                .description("Reactive with Spring Boot + React")
                .date(Instant.parse("2018-12-12T18:00:00.000Z"))
                .build();
        djug.setEvents(Collections.singleton(e));
        repository.save(djug);

        repository.findAll().forEach(System.out::println);
    }
}