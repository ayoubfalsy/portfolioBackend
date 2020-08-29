package com.javatechie.springsecurityjwtexample.services;

import com.javatechie.springsecurityjwtexample.entities.ProjectInfo;
import com.javatechie.springsecurityjwtexample.entities.SkillsInfo;
import com.javatechie.springsecurityjwtexample.repositories.ProjectInfoRepository;
import com.javatechie.springsecurityjwtexample.repositories.SkillsInfoRepository;
import com.javatechie.springsecurityjwtexample.services.logService.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class ProjectInfoService {
    @Autowired
    ProjectInfoRepository projectInfoRepository;

    public ProjectInfo AddUpdateProjectInfo(ProjectInfo projectInfo) {
        ProjectInfo projectInfo1 = null;
        try {
            projectInfo1 = projectInfoRepository.save(projectInfo);
        } catch (Exception e) {
            LogService.log(Level.SEVERE, e.toString(), e);
        }
        return projectInfo1;
    }

    public ProjectInfo findById(long lngId) {
        ProjectInfo projectInfo1 = null;
        try {
            projectInfo1 = projectInfoRepository.findByProjectId(lngId);
        } catch (Exception e) {
            LogService.log(Level.SEVERE, e.toString(), e);
        }
        return projectInfo1;
    }

}
