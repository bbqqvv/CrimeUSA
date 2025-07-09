/*
 * @ (#) CaseRepository.java 1.0 7/9/2025
 *
 * Copyright (c) 2025 IUH.All rights reserved
 */
package com.example.caseservicebase.repository;

import com.example.caseservicebase.model.CaseResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * @description
 * @author : Nguyen Truong An
 * @date : 7/9/2025
 * @version 1.0
 */
@Repository
public interface CaseResultRepository extends JpaRepository<CaseResult, Long> {
}
