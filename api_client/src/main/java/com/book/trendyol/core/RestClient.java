package com.book.trendyol.core;

import com.book.trendyol.config.properties.ApiConfigs;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.time.Duration.ofSeconds;

public class RestClient {

    private final HttpClient client;

    public RestClient() {
        this.client = HttpClient.newBuilder()
                .connectTimeout(ofSeconds(new ApiConfigs().connectionTimeout()))
                .version(HttpClient.Version.HTTP_1_1)
                .build();
    }

    public HttpResponse<String> get(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .setHeader("Accept", "application/json")
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return response;
    }

    public HttpResponse<String> put(String url, String body) {
        HttpRequest request = HttpRequest.newBuilder()
                .PUT(HttpRequest.BodyPublishers.ofString(body))
                .setHeader("Content-Type", "application/json")
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return response;
    }

    public HttpResponse<String> delete(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .DELETE()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return response;
    }
}
