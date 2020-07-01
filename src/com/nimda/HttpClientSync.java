package com.nimda;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class HttpClientSync {
    /*
    Simple HTTP client Interface to request RESTful service. from https://haveibeenpwned.com/api/v3/
     */

    public HttpResponse<String> HttpClient(String url, String api_key) throws IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(20))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .headers("User-Agent", "Pwn3dChecker V1.0",
                        "hibp-api-key", api_key)
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }

}

