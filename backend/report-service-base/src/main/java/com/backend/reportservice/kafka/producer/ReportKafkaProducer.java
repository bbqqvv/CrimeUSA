package com.backend.reportservice.kafka.producer;

import com.backend.reportservice.dto.response.ReportDto;

public interface ReportKafkaProducer {
  void sendReportAccepted(Long reportId, ReportDto reportDto);
}
