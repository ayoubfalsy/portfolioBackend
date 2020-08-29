package com.javatechie.springsecurityjwtexample.services;

import com.javatechie.springsecurityjwtexample.entities.Skills;
import com.javatechie.springsecurityjwtexample.entities.SocialMedia;
import com.javatechie.springsecurityjwtexample.repositories.SkillsRepository;
import com.javatechie.springsecurityjwtexample.repositories.SocialMediaRepository;
import com.javatechie.springsecurityjwtexample.services.logService.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Level;

@Service
public class SkillsService {
    @Autowired
    SkillsRepository skillsRepository;

    public Skills findSkills(long lngSkillsId) {
        Skills skills1 = null;
        try {
            skills1 = skillsRepository.findBySkillsId(lngSkillsId);
        } catch (Exception e) {
            LogService.log(Level.SEVERE, e.toString(), e);
        }
        return skills1;
    }

}
