package com.backend.reportservice.kafka.producer.impl;

import com.backend.reportservice.dto.response.ReportDto;
import com.backend.reportservice.kafka.producer.ReportKafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RepoprtKafkaProducerImpl implements ReportKafkaProducer {

  private final KafkaTemplate<String, ReportDto> kafkaTemplate;
  private final String reportAcceptedTopic = "report-accepted";

  @Override
  public void sendReportAccepted(Long reportId, ReportDto reportDto) {
    kafkaTemplate.send(reportAcceptedTopic, String.valueOf(reportId), reportDto);
  }
}
