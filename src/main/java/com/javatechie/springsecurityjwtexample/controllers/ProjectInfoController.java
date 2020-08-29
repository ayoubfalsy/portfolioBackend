package com.javatechie.springsecurityjwtexample.controllers;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javatechie.springsecurityjwtexample.entities.*;
import com.javatechie.springsecurityjwtexample.services.ProjectInfoService;
import com.javatechie.springsecurityjwtexample.services.ProjectService;
import com.javatechie.springsecurityjwtexample.services.SkillsInfoService;
import com.javatechie.springsecurityjwtexample.services.SkillsService;
import com.javatechie.springsecurityjwtexample.services.logService.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

@RequestMapping(value = "/project")
@RestController
public class ProjectInfoController {
    @Autowired
    ProjectInfoService projectInfoService;
    @Autowired
    ProjectService projectService;


    @PostMapping("/add")
    public ResponseEntity<?> AddUpdateProject(@RequestBody ProjectInfo projectInfo) {
        ProjectInfo projectInfo1 = new ProjectInfo();
        try {

            projectInfo1 = projectInfoService.AddUpdateProjectInfo(projectInfo);


        } catch (Exception e) {
            LogService.log(Level.SEVERE, e.toString(), e);
        }
        return new ResponseEntity<ProjectInfo>(projectInfo1, HttpStatus.OK);

    }

    @GetMapping("/getProjects/{lngId}")
    public ResponseEntity<?> findById(@PathVariable long lngId) {
        ProjectInfo projectInfo1 = new ProjectInfo();
        try {
            projectInfo1 = projectInfoService.findById(lngId);
            if (projectInfo1 == null) {
                projectInfo1 = new ProjectInfo();
                projectInfo1.setProjectId(-1l);
                projectInfo1.setSubTitle("");
                projectInfo1.setTitle("");
            }

        } catch (Exception e) {
            LogService.log(Level.SEVERE, e.toString(), e);
        }
        return new ResponseEntity<ProjectInfo>(projectInfo1, HttpStatus.OK);
    }

    @DeleteMapping("/DeleteProject/{lngProjectInfoId}/{lngProjectId}")
    public ResponseEntity<?> DeleteSkillsInfo(@PathVariable long lngProjectInfoId, @PathVariable long lngProjectId) {
        ProjectInfo clsProjectInfo = new ProjectInfo();
        ProjectInfo clsProjectInfo2 = new ProjectInfo();
        Project project = new Project();
        try {
            project = projectService.findProjet(lngProjectId);
            clsProjectInfo = projectInfoService.findById(lngProjectInfoId);
            clsProjectInfo.getListProjects().remove(project);
            clsProjectInfo2 = projectInfoService.AddUpdateProjectInfo(clsProjectInfo);
        } catch (Exception e) {
            LogService.log(Level.SEVERE, e.toString(), e);
        }
        return new ResponseEntity<ProjectInfo>(clsProjectInfo2, HttpStatus.OK);

    }

}
