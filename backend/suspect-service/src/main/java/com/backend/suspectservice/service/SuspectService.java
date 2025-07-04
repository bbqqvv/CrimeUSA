/*
 * @ (#) SuspectService.java  1.0 7/3/2025
 *
 * Copyright (c) 2025. All rights reserved
 */

package com.backend.suspectservice.service;

import com.backend.suspectservice.model.Suspect;

import java.util.List;
import java.util.Optional;

/*
 * @description Service interface for Suspect operations
 * @author: Khuong Pham
 * @date:   7/3/2025
 * @version:    1.0
 */
public interface SuspectService {

    List<Suspect> getAllSuspects();

    Optional<Suspect> getSuspectById(String suspectId);

    List<Suspect> getSuspectsByCaseId(String caseId);

    Optional<Suspect> getSuspectByIdentification(String identification);

    List<Suspect> searchSuspectsByName(String name);

    List<Suspect> getSuspectsByStatus(String status);

    Suspect createSuspect(Suspect suspect);

    Suspect updateSuspect(String suspectId, Suspect suspect);

    void deleteSuspect(String suspectId);

    boolean existsById(String suspectId);
}
