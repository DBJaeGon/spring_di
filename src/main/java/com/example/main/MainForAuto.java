package com.example.main;

import com.example.autospring.OrderService;
import com.example.autospring.ProductService;
import com.example.conf.ConfigFactoryBean;
import com.example.factoryb.SearchClient;
import com.example.factoryb.SearchClientFactory;
import com.example.factorym.ErpClient;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainForAuto {

    public static void main(String[] args) {
        
        // GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:config-auto-connection.xml");
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigFactoryBean.class);

        System.out.println();
        System.out.println("<<<<<<<<<< Autowired Inject OrderService >>>>>>>>>>");
        OrderService orderService = ctx.getBean("orderService", OrderService.class);
        // SearchClientFactory factory = ctx.getBean("orderSearchClientFactory2", SearchClientFactory.class);

        // System.out.println(orderService);
        ErpClient erpClient = orderService.getErpClientFactory().create();
        SearchClient searchClient = orderService.getSearchClientFactory().create();
        erpClient.connect();
        searchClient.checkLive();

        System.out.println();
        System.out.println("<<<<<<<<<< Resource ProductService >>>>>>>>>>");
        ProductService productService = ctx.getBean("productService", ProductService.class);
        ErpClient erpClient2 = productService.getEcFactory().create();
        SearchClient searchClient2 = productService.getSearchClientFactory().create();
        erpClient2.connect();
        searchClient2.checkLive();

        ctx.close();
    }
    
}