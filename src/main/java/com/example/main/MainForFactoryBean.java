package com.example.main;

import com.example.conf.ConfigFactoryBean;
import com.example.factoryb.SearchClient;
import com.example.factoryb.SearchClientFactory;
import com.example.factoryb.SearchServiceHealthChecker;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainForFactoryBean {
    
    public static void main(String[] args) {
        
        // GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:config-factory-bean.xml");
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigFactoryBean.class);

        SearchClientFactory factory = ctx.getBean("searchClientFactory", SearchClientFactory.class);
        System.out.println(factory);
        
        SearchClientFactory factory2 = ctx.getBean("searchClientFactory", SearchClientFactory.class);
        System.out.println(factory == factory2);

        SearchClient client = factory.create();
        client.checkLive();

        System.out.println("==================================");
        System.out.println("           Health Check           ");
        System.out.println("==================================");

        SearchServiceHealthChecker healthChecker = ctx.getBean("searchServiceHealthChecker", SearchServiceHealthChecker.class);
        healthChecker.check();
        ctx.close();
    }
}
