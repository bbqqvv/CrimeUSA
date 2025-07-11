/*
 * @ (#) CaseArrestController.java 1.0 7/10/2025
 *
 * Copyright (c) 2025 IUH. All rights reserved
 */

package com.example.caseservicebase.controller;

import com.backend.caseservice.dto.requestDTO.CaseArrestRequestDTO;
import com.backend.caseservice.dto.responseDTO.ResponseData;
import com.backend.caseservice.exception.InvalidRequestException;
import com.backend.caseservice.exception.ResourceNotFoundException;
import com.backend.caseservice.model.CaseArrest;
import com.backend.caseservice.service.CaseArrestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description Controller xử lý các yêu cầu liên quan đến entity CaseArrest.
 * @author Nguyen Truong An
 * @date 7/10/2025
 * @version 1.0
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/case/arrest")
public class CaseArrestController {

    private final CaseArrestService caseArrestService;

    @PostMapping("")
    public ResponseData<Long> createCaseArrest(@RequestBody CaseArrestRequestDTO request) {
        log.info("Request to create case arrest, arrestId={}", request.getArrestId());
        try {
            if (request.getArrestId() == null) {
                throw new InvalidRequestException("Arrest ID cannot be null");
            }
            if (request.getCaseId() != null && request.getCaseId() <= 0) {
                throw new InvalidRequestException("Case ID must be a positive value if provided");
            }
            if (request.getSuspectId() != null && request.getSuspectId() <= 0) {
                throw new InvalidRequestException("Suspect ID must be a positive value if provided");
            }
            Long arrestId = caseArrestService.createCaseArrest(request);
            return new ResponseData<>(HttpStatus.CREATED.value(), "Case arrest created successfully", arrestId);
        } catch (InvalidRequestException e) {
            throw e; // Được xử lý bởi GlobalExceptionHandler
        } catch (Exception e) {
            throw new InvalidRequestException("Failed to create case arrest due to invalid request");
        }
    }

    @PutMapping("/{arrestId}")
    public ResponseData<CaseArrest> updateCaseArrest(@PathVariable Long arrestId, @RequestBody CaseArrestRequestDTO request) {
        log.info("Request to update case arrest, arrestId={}", arrestId);
        try {
            if (arrestId == null) {
                throw new InvalidRequestException("Arrest ID cannot be null");
            }
            if (request.getCaseId() != null && request.getCaseId() <= 0) {
                throw new InvalidRequestException("Case ID must be a positive value if provided");
            }
            if (request.getSuspectId() != null && request.getSuspectId() <= 0) {
                throw new InvalidRequestException("Suspect ID must be a positive value if provided");
            }
            CaseArrest updatedCaseArrest = caseArrestService.updateCaseArrest(arrestId, request);
            return new ResponseData<>(HttpStatus.ACCEPTED.value(), "Case arrest updated successfully", updatedCaseArrest);
        } catch (ResourceNotFoundException e) {
            throw e; // Được xử lý bởi GlobalExceptionHandler
        } catch (InvalidRequestException e) {
            throw e; // Được xử lý bởi GlobalExceptionHandler
        } catch (Exception e) {
            throw new InvalidRequestException("Failed to update case arrest due to invalid request");
        }
    }

    @GetMapping("/{arrestId}")
    public ResponseData<CaseArrest> getCaseArrestById(@PathVariable Long arrestId) {
        log.info("Request to retrieve case arrest, arrestId={}", arrestId);
        try {
            if (arrestId == null) {
                throw new InvalidRequestException("Arrest ID cannot be null");
            }
            CaseArrest caseArrest = caseArrestService.getCaseArrestById(arrestId);
            return new ResponseData<>(HttpStatus.OK.value(), "Case arrest retrieved successfully", caseArrest);
        } catch (ResourceNotFoundException e) {
            throw e; // Được xử lý bởi GlobalExceptionHandler
        } catch (InvalidRequestException e) {
            throw e; // Được xử lý bởi GlobalExceptionHandler
        } catch (Exception e) {
            throw new InvalidRequestException("Failed to retrieve case arrest due to invalid request");
        }
    }

    @GetMapping("")
    public ResponseData<List<CaseArrest>> getAllCaseArrests() {
        log.info("Request to retrieve all case arrests");
        try {
            List<CaseArrest> caseArrests = caseArrestService.getAllCaseArrests();
            return new ResponseData<>(HttpStatus.OK.value(), "Case arrests retrieved successfully", caseArrests);
        } catch (Exception e) {
            throw new InvalidRequestException("Failed to retrieve case arrests due to an error");
        }
    }

    @DeleteMapping("/{arrestId}")
    public ResponseData<Void> softDeleteCaseArrest(@PathVariable Long arrestId) {
        log.info("Request to soft delete case arrest, arrestId={}", arrestId);
        try {
            if (arrestId == null) {
                throw new InvalidRequestException("Arrest ID cannot be null");
            }
            caseArrestService.softDeleteCaseArrest(arrestId);
            return new ResponseData<>(HttpStatus.OK.value(), "Case arrest soft deleted successfully", null);
        } catch (ResourceNotFoundException e) {
            throw e; // Được xử lý bởi GlobalExceptionHandler
        } catch (InvalidRequestException e) {
            throw e; // Được xử lý bởi GlobalExceptionHandler
        } catch (Exception e) {
            throw new InvalidRequestException("Failed to soft delete case arrest due to invalid request");
        }
    }
}