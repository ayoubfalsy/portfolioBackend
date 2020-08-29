package com.javatechie.springsecurityjwtexample.controllers;

import com.javatechie.springsecurityjwtexample.entities.Header;
import com.javatechie.springsecurityjwtexample.entities.SocialMedia;
import com.javatechie.springsecurityjwtexample.services.HeaderService;
import com.javatechie.springsecurityjwtexample.services.SocialMediaService;
import com.javatechie.springsecurityjwtexample.services.logService.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;

@RequestMapping(value = "/socialMedia")
@RestController
public class SocialMediaController {
    @Autowired
    SocialMediaService socialMediaService;

    @PostMapping("/add")
    public ResponseEntity<?> AddUpdateMedia(@RequestBody SocialMedia socialMedia) {
        SocialMedia clsSocialMedia = new SocialMedia();
        try {
            clsSocialMedia = socialMediaService.AddUpdateMedia(socialMedia);

        } catch (Exception e) {
            LogService.log(Level.SEVERE, e.toString(), e);
        }
        return new ResponseEntity<SocialMedia>(clsSocialMedia, HttpStatus.OK);

    }

    @GetMapping("/getMedia/{lngHeaderId}")
    public ResponseEntity<?> findByIdHeader(@PathVariable long lngHeaderId) {
        SocialMedia clsSocialMedia = new SocialMedia();
        try {
            clsSocialMedia = socialMediaService.findByIdMedia(lngHeaderId);
            if (clsSocialMedia == null) {
                clsSocialMedia = new SocialMedia();
                clsSocialMedia.setFacebook("");
                clsSocialMedia.setGoogle("");
                clsSocialMedia.setInstagram("");
                clsSocialMedia.setLinkedin("");
                clsSocialMedia.setIsFacebook("false");
                clsSocialMedia.setIsGoogle("false");
                clsSocialMedia.setIsInstagram("false");
                clsSocialMedia.setIsLinkedin("false");
            }

        } catch (Exception e) {
            LogService.log(Level.SEVERE, e.toString(), e);
        }
        return new ResponseEntity<SocialMedia>(clsSocialMedia, HttpStatus.OK);

    }
}
