package com.example.main;

import com.example.User;
import com.example.conf.Config;
import com.example.conf.Config1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainByJava {
    
    public static void main(String[] args) {
        
        // AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config1.class);
        User user1 = ctx.getBean("user1", User.class);
        User user2 = ctx.getBean("user2", User.class);
        System.out.println(user1.getId());
        System.out.println(user2.getId());
        ctx.close();

    }

}
