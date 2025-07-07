package com.Evidence_Service.client;

import com.Evidence_Service.dto.ReportDTO;
import com.Evidence_Service.dto.SuspectDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ReportClient {

    private final RestTemplate restTemplate;

    @Value("${report-service.url}")
    private String reportServiceUrl;

    public ReportClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<ReportDTO> getReportsByIds(List<String> ids) {
        return restTemplate.exchange(
                reportServiceUrl + "/api/reports/by-ids",
                HttpMethod.POST,
                new HttpEntity<>(ids),
                new ParameterizedTypeReference<List<ReportDTO>>() {}
        ).getBody();
    }
}
