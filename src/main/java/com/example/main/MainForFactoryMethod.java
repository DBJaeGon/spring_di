package com.example.main;

import com.example.factorym.ErpClient;
import com.example.factorym.ErpClientFactory;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainForFactoryMethod {
    
    public static void main(String[] args) {
        
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:config-factory-method.xml");

        ErpClientFactory factory = ctx.getBean("factory", ErpClientFactory.class);

        ErpClient client = factory.create();

        client.connect();

        client.close();
        ctx.close();
    }
}
