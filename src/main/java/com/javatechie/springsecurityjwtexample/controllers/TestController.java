package com.javatechie.springsecurityjwtexample.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javatechie.springsecurityjwtexample.entities.Competence;
import com.javatechie.springsecurityjwtexample.entities.Experience;
import com.javatechie.springsecurityjwtexample.services.CompetenceService;
import com.javatechie.springsecurityjwtexample.services.ExperienceService;
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

@RequestMapping(value = "/")
@RestController
public class TestController {


}
