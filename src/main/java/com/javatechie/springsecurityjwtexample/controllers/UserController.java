package com.javatechie.springsecurityjwtexample.controllers;

import com.javatechie.springsecurityjwtexample.entities.AppRole;
import com.javatechie.springsecurityjwtexample.entities.AppUser;
import com.javatechie.springsecurityjwtexample.entities.ProjectInfo;
import com.javatechie.springsecurityjwtexample.entitiesStatus.Pwd;
import com.javatechie.springsecurityjwtexample.services.SendEmailService;
import com.javatechie.springsecurityjwtexample.services.UserService;
import com.javatechie.springsecurityjwtexample.services.logService.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

@RequestMapping(value = "/user")
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    SendEmailService sendEmailService;

    @PostMapping("/register")
    public AppUser register(@RequestBody AppUser appUserRegister) {
        AppUser appUser = userService.findUserByEmail(appUserRegister.getEmail());
        if (appUser != null) {
            throw new RuntimeException("this user already exists");
        }
        AppUser user = new AppUser();

        user.setPassword(appUserRegister.getPassword());

        userService.SaveUser(user);
        return user;
    }

    @GetMapping("/getUser/{strEmail}")
    public AppUser getUserByEmail(@PathVariable String strEmail) {
        AppUser clsAppUser = null;
        clsAppUser = userService.findUserByEmail(strEmail);
        return clsAppUser;
    }

    @PostMapping("/checkPwd")
    public ResponseEntity<?> AddUpdateProject(@RequestBody Pwd pwd) {
        Pwd clsPwd1 = new Pwd();
        AppUser clsAppUser = null;
        boolean isPasswordMatches;
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        try {
            clsAppUser = userService.findUserByEmail(pwd.getEmail());
            isPasswordMatches = passwordEncoder.matches(pwd.getOldPwd(), clsAppUser.getPassword());
            if (isPasswordMatches) {
                clsAppUser.setPassword(pwd.getNewPwd());
                userService.SaveUser(clsAppUser);
                // password Changes
                pwd.setType(1);
            } else {
                // password incorrect
                pwd.setType(2);
            }
        } catch (Exception e) {
            LogService.log(Level.SEVERE, e.toString(), e);
        }
        return new ResponseEntity<Pwd>(pwd, HttpStatus.OK);
    }

    @PostMapping("/forgot/{strEmail}")
    public ResponseEntity<?> Forgot(@PathVariable String strEmail) {
        AppUser clsAppUser = null;
        Boolean blnType = false;
        String strText = "";
        Random r = new java.util.Random();
        String strPwd;
        String subject;
        try {
            clsAppUser = userService.findUserByEmail(strEmail);
            if (clsAppUser != null) {
                strPwd = Long.toString(r.nextLong() & Long.MAX_VALUE, 36);
                strText = "Voila votre mot de passe : " + strPwd;
                subject = "Nouveau Mot de passe";
                clsAppUser.setPassword(strPwd);
                userService.SaveUser(clsAppUser);
                sendEmailService.sendEmail(strEmail, strEmail, subject, strText);
            }

        } catch (Exception e) {
            LogService.log(Level.SEVERE, e.toString(), e);
        }
        return new ResponseEntity<Boolean>(blnType, HttpStatus.OK);

    }

    @PostMapping("/addAdmin/{strEmail}")
    public ResponseEntity<?> AddAdmin(@PathVariable String strEmail) {
        Boolean blnType = false;
        List<AppUser> lst = new ArrayList<>();
        try {
            lst = userService.findAll();
            if (userService.findAll().size() == 0) {
                userService.SaveUser(new AppUser("portfoliosupp@gmail.com", "123", null));
                userService.SaveRole(new AppRole("ADMIN"));
                userService.addRoleToUser("portfoliosupp@gmail.com", "ADMIN");
                blnType = true;
            }
        } catch (Exception e) {
            LogService.log(Level.SEVERE, e.toString(), e);
        }
        return new ResponseEntity<List<AppUser>>(lst, HttpStatus.OK);

    }
}
