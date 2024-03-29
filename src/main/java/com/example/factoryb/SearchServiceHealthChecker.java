package com.example.factoryb;

import java.util.List;

public class SearchServiceHealthChecker {

	private List<SearchClientFactory> factories;

	public void setFactories(List<SearchClientFactory> factories) {
		this.factories = factories;
	}

	public void check() {
		for (SearchClientFactory factory : factories) {
			SearchClient searchClient = factory.create();
			searchClient.checkLive();
		}
	}
}