package com.javatechie.springsecurityjwtexample.controllers;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
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

@RequestMapping(value = "/competence")
@RestController
public class CompetenceController {
    @Autowired
    CompetenceService competenceService;
    @Autowired
    ExperienceService experienceService;


    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<?> AddSkillsInfo(@RequestParam(value = "imageFile", required = false) MultipartFile img,
                                           @RequestParam(value = "competenceId", required = false) long competenceId,
                                           @RequestParam(value = "title", required = false) String title,
                                           @RequestParam(value = "experienceId", required = false) List experienceId,
                                           @RequestParam(value = "experienceProgress", required = false) List experienceProgress,
                                           @RequestParam(value = "experienceStack", required = false) List experienceStack,
                                           @RequestParam(value = "svg", required = false) boolean svg,
                                           @RequestParam(value = "svgName", required = false) String svgName) throws IOException {
        Competence CompetenceSave = new Competence();
        Competence Competence2 = new Competence();
        List<Experience> lstExp = new ArrayList<>();
        Competence clsCompetence = new Competence();
        long lngId;
        String strValue;
        String strValue2;
        long lngProgres;
        try {
            ObjectMapper mapper = new ObjectMapper();
            for (int i = 0; i < experienceId.size(); i++) {
                String id = (String) experienceId.get(i);
                lngId = Integer.valueOf(id);
                String progress = (String) experienceProgress.get(i);
                lngProgres = Integer.valueOf(progress);
                String stack = (String) experienceStack.get(i);
//                JsonFactory factory = mapper.getFactory();
//                com.fasterxml.jackson.core.JsonParser jsonParser = factory.createParser(json);
//                JsonNode node = mapper.readTree(jsonParser);
                strValue = String.valueOf(stack.replace('"', ' '));
                strValue2 = strValue.trim();
//                strProg2 = Integer.valueOf(node.get("progressPercentage").toString());
                Experience skills1 = new Experience(strValue2.trim(), lngProgres);
                if (lngId != -1) {
                    skills1.setExpId(lngId);
                }
                lstExp.add(skills1);
            }
            if (img != null) {
                clsCompetence = new Competence(title, competenceService.compressBytes(img.getBytes()), img.getContentType(), svg, svgName, lstExp);
                clsCompetence.setCompetenceId(competenceId);
                CompetenceSave = competenceService.AddUpdateCompetence(clsCompetence);
            } else {
                Competence2 = competenceService.findByCompetenceId(competenceId);
                Competence2.setTitle(title);
                Competence2.setSvg(svg);
                Competence2.setSvgName(svgName);
                Competence2.setExperience(lstExp);
                CompetenceSave = competenceService.AddUpdateCompetence(Competence2);

            }
            CompetenceSave.setImg(competenceService.decompressBytes(CompetenceSave.getImg()));
        } catch (Exception e) {
            LogService.log(Level.SEVERE, e.toString(), e);
        }
        return new ResponseEntity<Competence>(CompetenceSave, HttpStatus.OK);

    }

    @GetMapping("/getCompetence/{lngCompetenceId}")
    public ResponseEntity<?> findByCompetenceId(@PathVariable long lngCompetenceId) {
        Competence clsCompetence = new Competence();
        List<Experience> lst = new ArrayList<>();
        try {
            clsCompetence = competenceService.findByCompetenceId(lngCompetenceId);
            if (clsCompetence == null) {
                clsCompetence = new Competence();
                clsCompetence.setCompetenceId(-1l);
                clsCompetence.setSvg(false);
                clsCompetence.setSvgName("");
                clsCompetence.setTitle("");
                clsCompetence.setType("");
            } else {
                clsCompetence.setImg(competenceService.decompressBytes(clsCompetence.getImg()));

            }

        } catch (Exception e) {
            LogService.log(Level.SEVERE, e.toString(), e);
        }
        return new ResponseEntity<Competence>(clsCompetence, HttpStatus.OK);

    }

    //
    @DeleteMapping("/DeleteCompetence/{lngCompetenceId}/{lngExpId}")
    public ResponseEntity<?> DeleteSkillsInfo(@PathVariable long lngCompetenceId, @PathVariable long lngExpId) {
        Competence clsCompetence = new Competence();
        Competence clsCompetence2Save = new Competence();
        Experience skills = new Experience();
        try {
            skills = experienceService.findExperience(lngExpId);
            clsCompetence = competenceService.findByCompetenceId(lngCompetenceId);
            clsCompetence.getExperience().remove(skills);
            clsCompetence2Save = competenceService.AddUpdateCompetence(clsCompetence);
            clsCompetence2Save.setImg(competenceService.decompressBytes(clsCompetence2Save.getImg()));
        } catch (Exception e) {
            LogService.log(Level.SEVERE, e.toString(), e);
        }
        return new ResponseEntity<Competence>(clsCompetence2Save, HttpStatus.OK);

    }

}
