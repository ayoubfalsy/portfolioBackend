package com.javatechie.springsecurityjwtexample.services;

import com.javatechie.springsecurityjwtexample.entities.Header;
import com.javatechie.springsecurityjwtexample.repositories.HeaderRepository;
import com.javatechie.springsecurityjwtexample.services.logService.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;

@Service
public class HeaderService {
    @Autowired
    HeaderRepository headerRepository;

    public List<Header> listAllHeader() {
        List<Header> lstHeader = null;
        try {
            lstHeader = headerRepository.findAll();
        } catch (Exception e) {
            LogService.log(Level.SEVERE, e.toString(), e);
        }
        return lstHeader;
    }

    public Header AddUpdateHeader(Header header) {
        Header clsHeader = null;
        try {
            clsHeader = headerRepository.save(header);
        } catch (Exception e) {
            LogService.log(Level.SEVERE, e.toString(), e);
        }
        return clsHeader;
    }

    public Header findByIdHeader(long lngHeaderId) {
        Header clsHeader = null;
        try {
            clsHeader = headerRepository.findByIdHeader(lngHeaderId);
        } catch (Exception e) {
            LogService.log(Level.SEVERE, e.toString(), e);
        }
        return clsHeader;
    }

}
