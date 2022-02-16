package com.example.main;

import com.example.autospring.OrderInfo;
import com.example.autospring.OrderService;
import com.example.autospring.ProductInfo;
import com.example.autospring.ProductService;
import com.example.conf.ConfigScan;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainByScan {
    
    public static void main(String[] args) {
        // useXmlWithScan();
        useJavaWithScan();
    }

    private static void useXmlWithScan() {
		GenericXmlApplicationContext ctx =
				new GenericXmlApplicationContext("classpath:config-with-scan.xml");
		useContext(ctx);
		ctx.close();
	}

	private static void useJavaWithScan() {
		AnnotationConfigApplicationContext ctx =
				new AnnotationConfigApplicationContext(ConfigScan.class);
		useContext(ctx);
		ctx.close();

	}

	private static void useContext(ApplicationContext ctx) {
		System.out.println();
        System.out.println("==================================");
        System.out.println("          ProductService");
        System.out.println("==================================");
        ProductService productService = ctx.getBean("productService", ProductService.class);
		productService.createProduct(new ProductInfo());

        System.out.println();
        System.out.println("==================================");
        System.out.println("           OrderService");
        System.out.println("==================================");
		OrderService orderService = ctx.getBean("orderSvc", OrderService.class);
		orderService.order(new OrderInfo());
	}
}
