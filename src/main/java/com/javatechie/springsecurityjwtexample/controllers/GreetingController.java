package com.javatechie.springsecurityjwtexample.controllers;

import com.javatechie.springsecurityjwtexample.entities.Greeting;
import com.javatechie.springsecurityjwtexample.services.GreetingService;
import com.javatechie.springsecurityjwtexample.services.SendEmailService;
import com.javatechie.springsecurityjwtexample.services.logService.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;

@RequestMapping(value = "/greeting")
@RestController
public class GreetingController {
    @Autowired
    GreetingService greetingService;
    @Autowired
    SendEmailService sendEmailService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<?> AddGreeting(@RequestParam(value = "imageFile", required = false) MultipartFile file,
                                         @RequestParam(value = "greetingId", required = false) long lngGreetingId,
                                         @RequestParam(value = "title", required = false) String strTitle,
                                         @RequestParam(value = "subTitle", required = false) String strSubTitle,
                                         @RequestParam(value = "svg", required = false) boolean blnSvg,
                                         @RequestParam(value = "svgName", required = false) String svgName,
                                         @RequestParam(value = "pdf", required = false) MultipartFile filePdf) throws IOException {
        Greeting clsGreeting = null;
        Greeting clsGreeting2 = new Greeting();
        try {
            if (file != null) {
                clsGreeting = new Greeting(strTitle, strSubTitle, greetingService.compressBytes(file.getBytes()), file.getContentType(), blnSvg, svgName);
                clsGreeting.setGreetingId(lngGreetingId);
            } else {
                clsGreeting = greetingService.findByIdGreeting(lngGreetingId);
                clsGreeting.setTitle(strTitle);
                clsGreeting.setSubTitle(strSubTitle);
                clsGreeting.setSvg(blnSvg);
                clsGreeting.setSvgName(svgName);
            }
            if (filePdf != null) {
                clsGreeting.setPdf(greetingService.compressBytes(filePdf.getBytes()));
                clsGreeting.setTypePdf(filePdf.getContentType());
            }
            clsGreeting2 = greetingService.AddUpdateGreeting(clsGreeting);
            clsGreeting2.setImg(greetingService.decompressBytes(clsGreeting2.getImg()));
        } catch (Exception e) {
            LogService.log(Level.SEVERE, e.toString(), e);
        }
        return new ResponseEntity<Greeting>(clsGreeting2, HttpStatus.OK);

    }

    @GetMapping("/getGreeting/{lngGreetingId}")
    public ResponseEntity<?> findByIdHeader(@PathVariable long lngGreetingId) {
        Greeting greeting = new Greeting();
        try {
            greeting = greetingService.findByIdGreeting(lngGreetingId);
            if (greeting == null) {
                greeting = new Greeting();
                greeting.setTypePdf("");
                greeting.setType("");
                greeting.setSvgName("");
                greeting.setSubTitle("");
                greeting.setTitle("");
                greeting.setGreetingId(-1l);
                greeting.setSvg(false);
            } else {
                greeting.setImg(greetingService.decompressBytes(greeting.getImg()));
            }

        } catch (Exception e) {
            LogService.log(Level.SEVERE, e.toString(), e);
        }
        return new ResponseEntity<Greeting>(greeting, HttpStatus.OK);

    }

    @GetMapping(value = "getPdf/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] generateReviewTaskPdf(@PathVariable("id") long id) {
        Greeting greeting = new Greeting();
        byte pdf[] = null;
        try {
            greeting = greetingService.findByIdGreeting(id);
            pdf = greetingService.decompressBytes(greeting.getPdf());

        } catch (Exception e) {
            LogService.log(Level.SEVERE, e.toString(), e);
        }
        return pdf;
    }

    @GetMapping(value = "/sendEmail")
    public void sendIp(HttpServletRequest request) {
        String strEmailTo = "portfoliosupp@gmail.com";
        String strEmailFrom = "portfoliosupp@gmail.com";
        String subject = "download Cv";
        String strText = "";
        String strRemoteAddr = "";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        try {
            if (request != null) {
                strRemoteAddr = request.getHeader("X-FORWARDED-FOR");
                if (strRemoteAddr == null || "".equals(strRemoteAddr))
                    strRemoteAddr = request.getRemoteAddr();
            }
            strText = "someone Download your Cv from iP : " + strRemoteAddr + " Time :" + dateFormat.format(System.currentTimeMillis());
            sendEmailService.sendEmail(strEmailTo, strEmailFrom, subject, strText);

        } catch (Exception e) {
            LogService.log(Level.SEVERE, e.toString(), e);
        }
    }
}
