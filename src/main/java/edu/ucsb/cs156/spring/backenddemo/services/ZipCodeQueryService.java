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

    public static final String ENDPOINT = "http://api.zippopotam.us/us/{zipcode}";

    public String getJSON(String zipcode) throws HttpClientErrorException {
        log.info("zipcode={}", zipcode);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, String> uriVariables = Map.of("zipcode", zipcode);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> re = restTemplate.exchange(ENDPOINT, HttpMethod.GET, entity, String.class,
                uriVariables);
        return re.getBody();
    }
}


/*
 * public String getJSON(String distance, String minMag) throws HttpClientErrorException {
        log.info("distance={}, minMag={}", distance, minMag);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        String ucsbLat = "34.4140"; // hard coded params for Storke Tower
        String ucsbLong = "-119.8489";
        Map<String, String> uriVariables = Map.of("minMag", minMag, "distance", distance, "latitude", ucsbLat,
                "longitude", ucsbLong);

        ResponseEntity<String> re = restTemplate.exchange(ENDPOINT, HttpMethod.GET, entity, String.class,
                uriVariables);
        return re.getBody();
    }
 */