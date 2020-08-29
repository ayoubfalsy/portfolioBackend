package com.javatechie.springsecurityjwtexample.services;

import com.javatechie.springsecurityjwtexample.entities.AppRole;
import com.javatechie.springsecurityjwtexample.entities.AppUser;
import com.javatechie.springsecurityjwtexample.repositories.RoleRepository;
import com.javatechie.springsecurityjwtexample.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public AppUser SaveUser(AppUser user) {
        String hashPw = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashPw);
        return userRepository.save(user);
    }

    public AppRole SaveRole(AppRole role) {
        return roleRepository.save(role);
    }

    public void addRoleToUser(String strUserEmail, String strRoleName) {
        List<AppRole> lstRole = new ArrayList<>();
        AppRole appRole = roleRepository.findAppRoleByRoleName(strRoleName);
        AppUser user = userRepository.findByEmail(strUserEmail);
        lstRole.add(appRole);
//        user.getRoles().add(appRole);
        user.setRoles(lstRole);
        userRepository.save(user);
    }

    public List<AppUser> findAll() {
        return userRepository.findAll();
    }

//    public AppUser findUserByUserName(String strUserName) {
//        return userRepository.findByUsername(strUserName);
//    }

    public AppUser findUserByEmail(String strUserName) {
        return userRepository.findByEmail(strUserName);
    }
}
