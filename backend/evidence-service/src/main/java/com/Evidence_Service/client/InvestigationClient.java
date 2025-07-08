package com.Evidence_Service.client;

import com.Evidence_Service.dto.InvestigationDTO;
import com.Evidence_Service.dto.WarrantDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class InvestigationClient {

    private final RestTemplate restTemplate;

    @Value("${investigation-service.url}")
    private String investigationServiceUrl;

    public InvestigationClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<InvestigationDTO> getInvestigationByIds(List<String> ids) {
        return restTemplate.exchange(
                investigationServiceUrl + "/api/investigations/by-ids",
                HttpMethod.POST,
                new HttpEntity<>(ids),
                new ParameterizedTypeReference<List<InvestigationDTO>>() {}
        ).getBody();
    }
}

