package com.javatechie.springsecurityjwtexample.controllers;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.javatechie.springsecurityjwtexample.entities.Skills;
import com.javatechie.springsecurityjwtexample.entities.SkillsInfo;
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

@RequestMapping(value = "/skillsInfo")
@RestController
public class SkillsInfoController {
    @Autowired
    SkillsInfoService skillsInfoService;
    @Autowired
    SkillsService skillsService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<?> AddSkillsInfo(@RequestParam(value = "imageFile", required = false) MultipartFile img,
                                           @RequestParam(value = "skillsInfoId", required = false) long skillsInfoId,
                                           @RequestParam(value = "title", required = false) String title,
                                           @RequestParam(value = "subTitle", required = false) String subTitle,
                                           @RequestParam(value = "skillsValue", required = false) List skillsValue,
                                           @RequestParam(value = "skillsSkillsId", required = false) List skillsSkillsId,
                                           @RequestParam(value = "svg", required = false) boolean svg,
                                           @RequestParam(value = "svgName", required = false) String svgName) throws IOException {
        SkillsInfo skillsInfoSave = new SkillsInfo();
        SkillsInfo skillsInfo2 = new SkillsInfo();
        List<Skills> lstSkills1 = new ArrayList<>();
        SkillsInfo clsSkillsInfo = null;
        long lngId;
        String strValue;
        String strValue2;
        try {
            ObjectMapper mapper = new ObjectMapper();
            for (int i = 0; i < skillsValue.size(); i++) {
                String SkillsId = (String) skillsSkillsId.get(i);
                String jsonValue = (String) skillsValue.get(i);
                lngId = Integer.valueOf(SkillsId);
                strValue = String.valueOf(jsonValue.toString().replace('"', ' '));
                strValue2 = strValue.trim();
                Skills skills1 = new Skills(strValue2);
                if (lngId != -1) {
                    skills1.setSkillsId(lngId);
                }
                lstSkills1.add(skills1);
            }
            if (img != null) {
                clsSkillsInfo = new SkillsInfo(title, subTitle, skillsInfoService.compressBytes(img.getBytes()), img.getContentType(), svg, svgName, lstSkills1);
                clsSkillsInfo.setSkillsInfoId(skillsInfoId);
                skillsInfoSave = skillsInfoService.AddUpdateSkiilsInfo(clsSkillsInfo);
            } else {
                skillsInfo2 = skillsInfoService.findByIdSkillsId(skillsInfoId);
                skillsInfo2.setTitle(title);
                skillsInfo2.setSubTitle(subTitle);
                skillsInfo2.setSvg(svg);
                skillsInfo2.setSvgName(svgName);
                skillsInfo2.setSkills(lstSkills1);
                skillsInfoSave = skillsInfoService.AddUpdateSkiilsInfo(skillsInfo2);

            }
            skillsInfoSave.setImg(skillsInfoService.decompressBytes(skillsInfoSave.getImg()));
        } catch (Exception e) {
            LogService.log(Level.SEVERE, e.toString(), e);
        }
        return new ResponseEntity<SkillsInfo>(skillsInfoSave, HttpStatus.OK);

    }

    @GetMapping("/getSkillsInfo/{lngSkillsInfoId}")
    public ResponseEntity<?> findByISkillsInfo(@PathVariable long lngSkillsInfoId) {
        SkillsInfo clsSkillsInfo = new SkillsInfo();
        try {
            clsSkillsInfo = skillsInfoService.findByIdSkillsId(lngSkillsInfoId);
            if (clsSkillsInfo == null) {
                clsSkillsInfo = new SkillsInfo();
                clsSkillsInfo.setSkillsInfoId(-1l);
                clsSkillsInfo.setSubTitle("");
                clsSkillsInfo.setSvgName("");
                clsSkillsInfo.setSvgName("");
                clsSkillsInfo.setSvg(false);
                clsSkillsInfo.setTitle("");
                clsSkillsInfo.setType("");
            } else {
                clsSkillsInfo.setImg(skillsInfoService.decompressBytes(clsSkillsInfo.getImg()));
            }

        } catch (Exception e) {
            LogService.log(Level.SEVERE, e.toString(), e);
        }
        return new ResponseEntity<SkillsInfo>(clsSkillsInfo, HttpStatus.OK);

    }

    @DeleteMapping("/DeleteSkillsInfo/{lngSkillsInfoId}/{lngSkillsId}")
    public ResponseEntity<?> DeleteSkillsInfo(@PathVariable long lngSkillsInfoId, @PathVariable long lngSkillsId) {
        SkillsInfo clsSkillsInfo = new SkillsInfo();
        SkillsInfo clsSkillsInfo2 = new SkillsInfo();
        Skills skills = new Skills();
        try {
            skills = skillsService.findSkills(lngSkillsId);
            clsSkillsInfo = skillsInfoService.findByIdSkillsId(lngSkillsInfoId);
            clsSkillsInfo.getSkills().remove(skills);
            clsSkillsInfo2 = skillsInfoService.AddUpdateSkiilsInfo(clsSkillsInfo);
            clsSkillsInfo2.setImg(skillsInfoService.decompressBytes(clsSkillsInfo2.getImg()));
        } catch (Exception e) {
            LogService.log(Level.SEVERE, e.toString(), e);
        }
        return new ResponseEntity<SkillsInfo>(clsSkillsInfo, HttpStatus.OK);

    }

}
