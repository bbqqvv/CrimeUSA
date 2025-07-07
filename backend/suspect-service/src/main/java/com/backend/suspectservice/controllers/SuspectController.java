/*
 * @ (#) SuspectController.java  1.0 7/7/2025
 *
 * Copyright (c) 2025. All rights reserved
 */

package com.backend.suspectservice.controllers;

import com.backend.suspectservice.dto.request.SuspectCreateRequest;
import com.backend.suspectservice.dto.response.SuspectResponse;
import com.backend.suspectservice.service.SuspectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/*
 * @description
 * @author: Khuong Pham
 * @date:   7/7/2025
 * @version:    1.0
 */
@Slf4j
@RestController
@RequestMapping("/v1/suspect")
@Tag(name = "Suspect Query", description = "Suspect API")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SuspectController {
    SuspectService suspectService;

    public SuspectController(SuspectService suspectService) {
        this.suspectService = suspectService;
    }

    /*
     * @method: getAllSuspects
     * @description: Get all suspects
     * @return: List of SuspectResponse
     */
    @Operation(
        summary = "Get all Suspects",
        description = "Retrieve a list of all suspects",
        responses = {@ApiResponse(
                responseCode = "200", description = "Suspects retrieved successfully", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = SuspectResponse.class))),
            @ApiResponse(responseCode = "404", description = "No suspects found")
        })
    @GetMapping
    public ResponseEntity<List<SuspectResponse>> getAllSuspects() {
        return ResponseEntity.status(HttpStatus.OK).body(suspectService.getAllSuspects());
    }

    @Operation(
        summary = "Create a new Suspect",
        description = "Create a new suspect with Results interviews & Information about the apprehension",
        responses = {@ApiResponse(
                responseCode = "201", description = "Suspect created successfully", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = SuspectResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
        })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<SuspectResponse> createSuspect(
            @RequestPart(name = "mugshotUrl") MultipartFile suspectImage,
            @RequestPart @Valid SuspectCreateRequest suspectCreateRequest) {
        log.info("Create Suspect request content: {}", suspectCreateRequest);
        SuspectResponse c = suspectService.createSuspect(suspectCreateRequest,suspectImage);
        return new ResponseEntity<>(c, HttpStatus.CREATED);
    }
}
