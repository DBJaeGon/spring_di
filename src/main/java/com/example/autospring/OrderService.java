package com.example.autospring;

import javax.inject.Inject;
import javax.inject.Named;

import com.example.factoryb.SearchClientFactory;
import com.example.factoryb.SearchDocument;
import com.example.factorym.ErpClient;
import com.example.factorym.ErpClientFactory;
import com.example.factorym.ErpOrderData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("orderSvc")
public class OrderService {
    
    private ErpClientFactory erpClientFactory;

    @Autowired
    @Qualifier("order")
    private SearchClientFactory scFactory;

    @Autowired(required = false)
    private Monitor monitor;
    
    @Inject
    public void setErpClientFactory(@Named("erpClientFactory") ErpClientFactory erpClientFactory) {
        this.erpClientFactory = erpClientFactory;
    }

    // @Autowired
    // public void setSearchClientFactory(SearchClientFactory searchClientFactory) {
    //     this.searchClientFactory = searchClientFactory;
    // }

    // @Autowired
    // public OrderService(ErpClientFactory erpClientFactory, @Qualifier("order") SearchClientFactory searchClientFactory) {
    //     this.erpClientFactory = erpClientFactory;
    //     this.searchClientFactory = searchClientFactory;
    // }

    // @Autowired
    // public void init(ErpClientFactory erpClientFactory, SearchClientFactory searchClientFactory) {
    //     this.erpClientFactory = erpClientFactory;
    //     this.searchClientFactory = searchClientFactory;
    // }

    public SearchClientFactory getSearchClientFactory() {
        return this.scFactory;
    }

    public ErpClientFactory getErpClientFactory() {
        return this.erpClientFactory;
    }

    public void order(OrderInfo oi) {
		sendOrderInfoToErp(oi);
		addOrderInfoToSearch(oi);
	}

	private void sendOrderInfoToErp(OrderInfo oi) {
		ErpClient erpClient = erpClientFactory.create();
		erpClient.connect();
		erpClient.sendPurchaseInfo(toErpOrderData(oi));
		erpClient.close();
	}

	private ErpOrderData toErpOrderData(OrderInfo oi) {
		return new ErpOrderData();
	}

	private void addOrderInfoToSearch(OrderInfo oi) {
		scFactory.create().addDocument(toSearchDocument(oi));
	}

	private SearchDocument toSearchDocument(OrderInfo oi) {
		return new SearchDocument();
	}
    
}
