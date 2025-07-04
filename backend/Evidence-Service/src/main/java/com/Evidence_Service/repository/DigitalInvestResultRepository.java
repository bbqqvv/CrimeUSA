package com.Evidence_Service.repository;

import com.Evidence_Service.model.DigitalInvestResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DigitalInvestResultRepository extends JpaRepository<DigitalInvestResult, String> {
    List<DigitalInvestResult> findByEvidenceId(String evidenceId);
}
