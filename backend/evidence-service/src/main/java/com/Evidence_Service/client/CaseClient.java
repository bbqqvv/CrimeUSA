package com.Evidence_Service.client;

import com.Evidence_Service.dto.CaseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class CaseClient {

    private final RestTemplate restTemplate;

    @Value("${case-service.url}")
    private String caseServiceUrl;

    public CaseClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<CaseDTO> getCasesByIds(List<String> ids) {
        return restTemplate.exchange(
                caseServiceUrl + "/api/cases/by-ids",
                HttpMethod.POST,
                new HttpEntity<>(ids),
                new ParameterizedTypeReference<List<CaseDTO>>() {}
        ).getBody();
    }
}

