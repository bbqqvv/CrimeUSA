/*
 * @ (#) SuspectRepository.java  1.0 7/3/2025
 *
 * Copyright (c) 2025. All rights reserved
 */

package com.backend.suspectservice.repository;

import com.backend.suspectservice.model.Suspect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/*
 * @description Repository for Suspect entity
 * @author: Khuong Pham
 * @date:   7/3/2025
 * @version:    1.0
 */
@Repository
public interface SuspectRepository extends JpaRepository<Suspect, String> {

    @Query("SELECT s FROM Suspect s WHERE s.isDeleted = false")
    List<Suspect> findAllActive();

    @Query("SELECT s FROM Suspect s WHERE s.suspectId = :id AND s.isDeleted = false")
    Optional<Suspect> findByIdActive(@Param("id") String id);

    @Query("SELECT s FROM Suspect s WHERE s.caseId = :caseId AND s.isDeleted = false")
    List<Suspect> findByCaseIdActive(@Param("caseId") String caseId);

    @Query("SELECT s FROM Suspect s WHERE s.identification = :identification AND s.isDeleted = false")
    Optional<Suspect> findByIdentificationActive(@Param("identification") String identification);

    @Query("SELECT s FROM Suspect s WHERE s.fullName LIKE %:name% AND s.isDeleted = false")
    List<Suspect> findByFullNameContainingActive(@Param("name") String name);

    @Query("SELECT s FROM Suspect s WHERE s.status = :status AND s.isDeleted = false")
    List<Suspect> findByStatusActive(@Param("status") String status);
}
