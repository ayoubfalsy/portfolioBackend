package com.javatechie.springsecurityjwtexample.repositories;

import com.javatechie.springsecurityjwtexample.entities.Competence;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CompetenceRepository extends JpaRepository<Competence, Long> {
    Competence findByCompetenceId(long lngCompetenceId);
}