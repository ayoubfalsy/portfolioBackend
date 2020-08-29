package com.javatechie.springsecurityjwtexample.services;

import com.javatechie.springsecurityjwtexample.entities.Experience;
import com.javatechie.springsecurityjwtexample.entities.Skills;
import com.javatechie.springsecurityjwtexample.repositories.ExperienceRepository;
import com.javatechie.springsecurityjwtexample.repositories.SkillsRepository;
import com.javatechie.springsecurityjwtexample.services.logService.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Level;

@Service
public class ExperienceService {
    @Autowired
    ExperienceRepository experienceRepository;

    public Experience findExperience(long lngExpId) {
        Experience experience = null;
        try {
            experience = experienceRepository.findByExpId(lngExpId);
        } catch (Exception e) {
            LogService.log(Level.SEVERE, e.toString(), e);
        }
        return experience;
    }

}
