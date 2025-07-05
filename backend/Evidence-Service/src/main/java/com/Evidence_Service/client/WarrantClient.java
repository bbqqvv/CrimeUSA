package com.Evidence_Service.client;

import com.Evidence_Service.dto.WarrantDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class WarrantClient {

    private final RestTemplate restTemplate;

    @Value("${warrant-service.url}")
    private String warrantServiceUrl;

    public WarrantClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<WarrantDTO> getWarrantByIds(List<String> ids) {
        return restTemplate.exchange(
                warrantServiceUrl + "/api/warrants/by-ids",
                HttpMethod.POST,
                new HttpEntity<>(ids),
                new ParameterizedTypeReference<List<WarrantDTO>>() {}
        ).getBody();
    }
}

