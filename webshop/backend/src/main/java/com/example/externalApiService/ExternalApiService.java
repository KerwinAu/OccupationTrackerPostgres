package com.example.externalApiService;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ExternalApiService {
    @Autowired
    private RestTemplate restTemplate;

    private final String apiUrl = "https://www.jumpers-fitness.com/club-checkin-number/29/Jumpers.JumpersFitnessTld";

    public String fetchDataFromExternalApi() {
        System.out.println("fetchDataFromExternalApi");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.TEXT_HTML)); // Request HTML
        // System.out.println("headers:  " + headers);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        // System.out.println("requestEntity:  " + requestEntity);
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, requestEntity, String.class);
        // System.out.println("response: " + response);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            return null; // Handle the case when the request fails
        }
    }
}
