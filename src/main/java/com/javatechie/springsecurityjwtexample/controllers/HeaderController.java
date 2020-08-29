package com.javatechie.springsecurityjwtexample.controllers;

import com.javatechie.springsecurityjwtexample.entities.AppUser;
import com.javatechie.springsecurityjwtexample.entities.Header;
import com.javatechie.springsecurityjwtexample.services.HeaderService;
import com.javatechie.springsecurityjwtexample.services.UserService;
import com.javatechie.springsecurityjwtexample.services.logService.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Level;

@RequestMapping(value = "/header")
@RestController
public class HeaderController {
    @Autowired
    HeaderService headerService;


    @PostMapping("/add")
    public ResponseEntity<?> AddUpdateHeader(@RequestBody Header clsHeader) {
        Header clsHeader1 = new Header();
        List<Header> lstHeader = null;
        try {
            lstHeader = headerService.listAllHeader();
            if (lstHeader != null) {
                clsHeader1 = headerService.AddUpdateHeader(clsHeader);
            }

        } catch (Exception e) {
            LogService.log(Level.SEVERE, e.toString(), e);
        }
        return new ResponseEntity<Header>(clsHeader1, HttpStatus.OK);

    }

    @GetMapping("/getHeader/{lngHeaderId}")
    public ResponseEntity<?> findByIdHeader(@PathVariable long lngHeaderId) {
        Header clsHeader = new Header();
        try {
            clsHeader = headerService.findByIdHeader(lngHeaderId);
            if (clsHeader == null) {
                clsHeader = new Header();
            }
        } catch (Exception e) {
            LogService.log(Level.SEVERE, e.toString(), e);
        }
        return new ResponseEntity<Header>(clsHeader, HttpStatus.OK);

    }
}
