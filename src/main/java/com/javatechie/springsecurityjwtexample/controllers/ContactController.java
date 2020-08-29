package com.javatechie.springsecurityjwtexample.controllers;

import com.javatechie.springsecurityjwtexample.entities.Contact;
import com.javatechie.springsecurityjwtexample.services.ContactService;
import com.javatechie.springsecurityjwtexample.services.ContactService;
import com.javatechie.springsecurityjwtexample.services.logService.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.logging.Level;

@RequestMapping(value = "/contact")
@RestController
public class ContactController {
    @Autowired
    ContactService contactService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<?> AddContact(@RequestParam(value = "imageFile", required = false) MultipartFile file,
                                        @RequestParam(value = "contactId", required = false) long lngContactId,
                                        @RequestParam(value = "title", required = false) String strTitle,
                                        @RequestParam(value = "subTitle", required = false) String strSubTitle,
                                        @RequestParam(value = "svg", required = false) boolean blnSvg,
                                        @RequestParam(value = "svgName", required = false) String svgName,
                                        @RequestParam(value = "number", required = false) String number,
                                        @RequestParam(value = "email", required = false) String email
    ) throws IOException {
        Contact clsContact = null;
        Contact clsContact2 = new Contact();
        try {
            if (file != null) {
                clsContact = new Contact(strTitle, strSubTitle, contactService.compressBytes(file.getBytes()), file.getContentType(), number, email, blnSvg, svgName);
                clsContact.setContactId(lngContactId);
            } else {
                clsContact = contactService.findByContactId(lngContactId);
                clsContact.setTitle(strTitle);
                clsContact.setSubTitle(strSubTitle);
                clsContact.setSvg(blnSvg);
                clsContact.setSvgName(svgName);
            }
            clsContact2 = contactService.AddUpdateContact(clsContact);
            clsContact2.setImg(contactService.decompressBytes(clsContact2.getImg()));
        } catch (Exception e) {
            LogService.log(Level.SEVERE, e.toString(), e);
        }
        return new ResponseEntity<Contact>(clsContact2, HttpStatus.OK);

    }

    @GetMapping("/getContact/{lngContactId}")
    public ResponseEntity<?> findByIdHeader(@PathVariable long lngContactId) {
        Contact greeting = new Contact();
        try {
            greeting = contactService.findByContactId(lngContactId);
            if (greeting == null) {
                greeting = new Contact();
                greeting.setContactId(-1l);
                greeting.setSubTitle("");
                greeting.setSvgName("");
                greeting.setTitle("");
                greeting.setEmail("");
                greeting.setNumber("");
                greeting.setType("");
                greeting.setSvg(false);
            } else {
                greeting.setImg(contactService.decompressBytes(greeting.getImg()));

            }

        } catch (Exception e) {
            LogService.log(Level.SEVERE, e.toString(), e);
        }
        return new ResponseEntity<Contact>(greeting, HttpStatus.OK);

    }
}
