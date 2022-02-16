package com.example.factoryb;

public class HttpSearchClient implements SearchClient {

    private String server;

    public HttpSearchClient(String server) {
        this.server = server;
    }

	@Override
	public void addDocument(SearchDocument searchDocument) {
		System.out.println("문서 추가함");
	}

	@Override
	public void checkLive() {
		System.out.println(this.server +" : 상태 확인");
	}

}