package com.example.conf;

import java.util.Arrays;

import com.example.AuthFailLogger;
import com.example.AuthenticationService;
import com.example.PasswordChangeService;
import com.example.User;
import com.example.UserRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    
    @Bean
    public User user1() {
        return new User("jgKim", "1234");
    }

    @Bean(name="user2")
    public User user() {
        return new User("testUser", "qwer");
    }

    @Bean
    public UserRepository userRepository() {
        UserRepository userRepo =  new UserRepository();
        userRepo.setUserMap(Arrays.asList(user1(), user()));
        return userRepo;
    }

    @Bean
    public PasswordChangeService pwChangeSvc() {
        return new PasswordChangeService(userRepository());
    }

    @Bean
    public AuthFailLogger authFailLogger() {
        AuthFailLogger logger = new AuthFailLogger();
        logger.setThreshold(2);
        return logger;
    }

    @Bean
    public AuthenticationService authenticationService() {
        AuthenticationService authSvc = new AuthenticationService();
        authSvc.setUserRepository(userRepository());
        authSvc.setFailLogger(authFailLogger());
        return authSvc;
    }
    
}
