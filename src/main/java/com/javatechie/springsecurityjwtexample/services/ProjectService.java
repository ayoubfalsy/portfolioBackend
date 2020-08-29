package com.javatechie.springsecurityjwtexample.services;

import com.javatechie.springsecurityjwtexample.entities.Project;
import com.javatechie.springsecurityjwtexample.entities.Skills;
import com.javatechie.springsecurityjwtexample.repositories.ProjectInfoRepository;
import com.javatechie.springsecurityjwtexample.repositories.ProjectRepository;
import com.javatechie.springsecurityjwtexample.repositories.SkillsRepository;
import com.javatechie.springsecurityjwtexample.services.logService.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Level;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;

    public Project findProjet(long lngPrgId) {
        Project project = null;
        try {
            project = projectRepository.findByPrjId(lngPrgId);
        } catch (Exception e) {
            LogService.log(Level.SEVERE, e.toString(), e);
        }
        return project;
    }

}
