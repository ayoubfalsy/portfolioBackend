package com.javatechie.springsecurityjwtexample.services;

import com.javatechie.springsecurityjwtexample.entities.Header;
import com.javatechie.springsecurityjwtexample.entities.SocialMedia;
import com.javatechie.springsecurityjwtexample.repositories.HeaderRepository;
import com.javatechie.springsecurityjwtexample.repositories.SocialMediaRepository;
import com.javatechie.springsecurityjwtexample.services.logService.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;

@Service
public class SocialMediaService {
    @Autowired
    SocialMediaRepository socialMediaRepository;

    public SocialMedia AddUpdateMedia(SocialMedia socialMedia) {
        SocialMedia clsSocialMedia1 = null;
        try {
            clsSocialMedia1 = socialMediaRepository.save(socialMedia);
        } catch (Exception e) {
            LogService.log(Level.SEVERE, e.toString(), e);
        }
        return clsSocialMedia1;
    }

    public SocialMedia findByIdMedia(long lngHeaderId) {
        SocialMedia clsSocialMedia1 = null;
        try {
            clsSocialMedia1 = socialMediaRepository.findByMediaId(lngHeaderId);
        } catch (Exception e) {
            LogService.log(Level.SEVERE, e.toString(), e);
        }
        return clsSocialMedia1;
    }

}
