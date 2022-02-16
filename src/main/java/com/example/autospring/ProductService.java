package com.example.autospring;

import javax.annotation.Resource;

import com.example.factoryb.SearchClientFactory;
import com.example.factoryb.SearchDocument;
import com.example.factorym.ErpClientFactory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ProductService {
    
    @Resource
    private ErpClientFactory ecFactory;
    
    private SearchClientFactory searchClientFactory;

    // @Resource
    // @Qualifier("order")
    @Resource(name="orderSearchClientFactory")
    public void setSearchClientFactory(SearchClientFactory searchClientFactory) {
        this.searchClientFactory = searchClientFactory;
    }

    public SearchClientFactory getSearchClientFactory() {
        return this.searchClientFactory;
    }

    public ErpClientFactory getEcFactory() {
        return this.ecFactory;
    }

    public void createProduct(ProductInfo pi) {
		searchClientFactory.create().addDocument(toSearchDocument(pi));
	}

	private SearchDocument toSearchDocument(ProductInfo pi) {
		return new SearchDocument();
	}
    
}
