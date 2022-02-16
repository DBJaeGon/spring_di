package com.example.conf;

import java.util.Arrays;

import com.example.autospring.OrderService;
import com.example.autospring.ProductService;
import com.example.factoryb.SearchClientFactory;
import com.example.factoryb.SearchClientFactoryBean;
import com.example.factoryb.SearchServiceHealthChecker;
import com.example.factorym.ErpClientFactory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigFactoryBean {

    @Bean  
    public OrderService orderService() {
        return new OrderService();
    }

    @Bean
    public ProductService productService() {
        return new ProductService();
    }
    
    @Bean
    public SearchClientFactoryBean orderSearchClientFactory() {
        SearchClientFactoryBean searchClientFactoryBean = new SearchClientFactoryBean();
        searchClientFactoryBean.setServer("google");
        searchClientFactoryBean.setPort(8888);
        searchClientFactoryBean.setContentType("json");
        return searchClientFactoryBean;
    }
    
    @Bean
    @Qualifier("order")
    public SearchClientFactoryBean searchClientFactory2() {
        SearchClientFactoryBean searchClientFactoryBean = new SearchClientFactoryBean();
        searchClientFactoryBean.setServer("TFT");
        searchClientFactoryBean.setPort(12345);
        searchClientFactoryBean.setContentType("exe");
        return searchClientFactoryBean;
    }

    @Bean
    public ErpClientFactory erpClientFactory() {
        return ErpClientFactory.instance();
    }

    // @Bean SearchServiceHealthChecker searchServiceHealthChecker() throws Exception{
    //     SearchServiceHealthChecker searchServiceHealthChecker = new SearchServiceHealthChecker();
    //     searchServiceHealthChecker.setFactories(Arrays.asList(
    //         searchClientFactory().getObject(),
    //         searchClientFactory2().getObject()
    //     ));
    //     return searchServiceHealthChecker;
    // }

    @Bean 
    SearchServiceHealthChecker searchServiceHealthChecker(
        SearchClientFactory orderSearchClientFactory, SearchClientFactory searchClientFactory2
    ) throws Exception{
        SearchServiceHealthChecker searchServiceHealthChecker = new SearchServiceHealthChecker();
        searchServiceHealthChecker.setFactories(Arrays.asList(
            orderSearchClientFactory, searchClientFactory2
        ));
        return searchServiceHealthChecker;
    }

}