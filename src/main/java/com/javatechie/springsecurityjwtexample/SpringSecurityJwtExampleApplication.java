package com.javatechie.springsecurityjwtexample;
//
//import com.javatechie.springsecurityjwtexample.services.UserService;
//import org.springframework.beans.factory.annotation.Autowired;

import com.javatechie.springsecurityjwtexample.entities.AppRole;
import com.javatechie.springsecurityjwtexample.entities.AppUser;
import com.javatechie.springsecurityjwtexample.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.multipart.support.MultipartFilter;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class SpringSecurityJwtExampleApplication extends SpringBootServletInitializer {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {

        SpringApplication.run(SpringSecurityJwtExampleApplication.class, args);

        System.out.println("*************** Welcome *****************");
    }

    //
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringSecurityJwtExampleApplication.class);
    }

    @Bean
    public BCryptPasswordEncoder getBCPA() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        configuration.setExposedHeaders(Arrays.asList("Authorization", "content-type"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "content-type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public MultipartFilter multipartFilter() {

        MultipartFilter multipartFilter = new MultipartFilter();
        multipartFilter.setMultipartResolverBeanName("multipartResolver");
        return multipartFilter;
    }

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(SpringSecurityJwtExampleApplication.class);
//    }

    //    @Override
//    public void run(String... args) throws Exception {
////        userService.SaveUser(new AppUser("portfoliosupp@gmail.com", "123", null));
////        userService.SaveRole(new AppRole("ADMIN"));
////        userService.addRoleToUser("portfoliosupp@gmail.com", "ADMIN");
////        accountService.SaveRole(new AppRole("USER"));
////        accountService.addRoleToUser("admin", "USER");
////        accountService.addRoleToUser("admin", "USER");
////        AppUser user = new AppUser("portfoliosupp@gmail.com", "123", "test", null);
////        user.setId(1l);
////        userService.SaveUser(user);
////        userService.addRoleToUser("portfoliosupp@gmail.com", "ADMIN");
//
//
//    }
}
