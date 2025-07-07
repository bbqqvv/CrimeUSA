/*
 * @ (#) SuspectServiceImpl.java  1.0 7/3/2025
 *
 * Copyright (c) 2025. All rights reserved
 */

package com.backend.suspectservice.service.serviceImpl;

import com.backend.suspectservice.dto.request.SuspectCreateRequest;
import com.backend.suspectservice.dto.response.SuspectResponse;
import com.backend.suspectservice.mapper.SuspectMapper;
import com.backend.suspectservice.model.Suspect;
import com.backend.suspectservice.repository.SuspectRepository;
import com.backend.suspectservice.service.CloudinaryService;
import com.backend.suspectservice.service.SuspectService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*
 * @description
 * @author: Khuong Pham
 * @date:   7/3/2025
 * @version:    1.0
 */
@Service
@RequiredArgsConstructor // Only create Contructor for final fields
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class SuspectServiceImpl implements SuspectService {
    SuspectRepository suspectRep;
    SuspectMapper suspectMapper;
    CloudinaryService cloudinaryService;

    @Override
    public List<SuspectResponse> getAllSuspects() {
        log.info("Getting all suspects");
        return suspectRep.getAllByIsDeletedFalse().stream().map(
                suspectMapper::toSuspectResponse
        ).collect(Collectors.toList());
    }

    @Override
    public Optional<Suspect> getSuspectById(String suspectId) {
        return Optional.empty();
    }

    @Override
    public List<Suspect> getSuspectsByCaseId(String caseId) {
        return List.of();
    }

    @Override
    public List<Suspect> searchSuspectsByName(String name) {
        return List.of();
    }

    @Override
    public List<Suspect> getSuspectsByStatus(String status) {
        return List.of();
    }

    @Transactional
    @Override
    public SuspectResponse createSuspect(SuspectCreateRequest suspect, MultipartFile suspectImage) {
        Suspect s = suspectMapper.createSuspect(suspect);
        String imageUrl = cloudinaryService.uploadImage(suspectImage);
        s.setMugshotUrl(imageUrl);
        return suspectMapper.toSuspectResponse(
                suspectRep.save(s)
        );
    }

    @Override
    public SuspectResponse updateSuspect(String suspectId, SuspectCreateRequest suspect) {
       return suspectRep.findById(suspectId)
                .map(existingSuspect -> {
                    suspectMapper.updateSuspect(existingSuspect, suspect);
                    return suspectMapper.toSuspectResponse(suspectRep.save(existingSuspect));
                })
                .orElseThrow(() -> new RuntimeException("Suspect not found with id: " + suspectId));
    }

    @Override
    public void deleteSuspect(String suspectId) {

    }

    @Override
    public boolean existsById(String suspectId) {
        return false;
    }
}
