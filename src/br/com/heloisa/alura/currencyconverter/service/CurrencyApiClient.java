package br.com.heloisa.alura.currencyconverter.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyApiClient {

    private final HttpClient client;

    public CurrencyApiClient() {
        this.client = HttpClient.newHttpClient();
    }

    public HttpRequest criarRequisicao(String url) {
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
    }

    public String enviarRequisicao(HttpRequest request) {
        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            System.out.println("Erro ao enviar requisição: " + e.getMessage());
            return null;
        }
    }
}