package com.javatechie.springsecurityjwtexample.repositories;

import com.javatechie.springsecurityjwtexample.entities.Greeting;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GreetingRepository extends JpaRepository<Greeting, Long> {
    Greeting findByGreetingId(long lngGreetingId);
}