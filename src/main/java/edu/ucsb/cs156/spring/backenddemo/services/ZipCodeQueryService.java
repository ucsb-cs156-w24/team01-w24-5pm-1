package edu.ucsb.cs156.spring.backenddemo.services;

import org.springframework.web.client.RestTemplate;

import org.springframework.boot.web.client.RestTemplateBuilder;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class ZipCodeQueryService {

    private final RestTemplate restTemplate;

    public ZipCodeQueryService(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public static final String ENDPOINT = "ZipCodeQueryService.java";

    public String getJSON(String zipcode) throws HttpClientErrorException {
       return "";
    }
}
