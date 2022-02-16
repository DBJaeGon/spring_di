package com.example.conf;

import java.util.Arrays;
import java.util.Properties;

import com.example.factoryb.SearchClientFactory;
import com.example.factoryb.SearchClientFactoryBean;
import com.example.factoryb.SearchServiceHealthChecker;
import com.example.factorym.ErpClientFactory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.autospring")
public class ConfigScan {
    
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
        Properties props = new Properties();
		props.setProperty("server", "componentScan");
        return ErpClientFactory.instance(props);
        // return ErpClientFactory.instance();
    }

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
