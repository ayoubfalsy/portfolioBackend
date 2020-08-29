package com.javatechie.springsecurityjwtexample.repositories;

import com.javatechie.springsecurityjwtexample.entities.Contact;
import com.javatechie.springsecurityjwtexample.entities.Greeting;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ContactRepository extends JpaRepository<Contact, Long> {
    Contact findByContactId(long lngId);
}