package com.Evidence_Service.client;

import com.Evidence_Service.dto.CaseDTO;
import com.Evidence_Service.dto.SuspectDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class SuspectClient {

    private final RestTemplate restTemplate;

    @Value("${suspect-service.url}")
    private String suspectServiceUrl;

    public SuspectClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<SuspectDTO> getSuspectByIds(List<String> ids) {
        return restTemplate.exchange(
                suspectServiceUrl + "/api/suspects/by-ids",
                HttpMethod.POST,
                new HttpEntity<>(ids),
                new ParameterizedTypeReference<List<SuspectDTO>>() {}
        ).getBody();
    }
}
