package com.backend.investigationservice.service.serviceImpl;

import com.backend.investigationservice.dto.request.InvestigationPlanCreationRequest;
import com.backend.investigationservice.dto.response.InvestigationPlanResponse;
import com.backend.investigationservice.model.InvestigationPlan;
import com.backend.investigationservice.repository.InvestigationPlanRepository;
import com.backend.investigationservice.service.InvestigationPlanService;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
@Transactional
public class InvestigationPlanServiceImpl implements InvestigationPlanService {
    private final InvestigationPlanRepository investigationPlanRepository;

    public InvestigationPlanServiceImpl(InvestigationPlanRepository investigationPlanRepository){
        this.investigationPlanRepository = investigationPlanRepository;
    }


    @Override
    public List<InvestigationPlanResponse> findAll() {
        var investigationPlans = investigationPlanRepository.findByIsDeletedFalse();
        var investigationPlanResponses = investigationPlans.stream().map(investigationPlan -> {
           var response = new InvestigationPlanResponse();
            response.setInvestigationPlanId(investigationPlan.getInvestigationPlanId());
                   response.setSummary(investigationPlan.getSummary());
                   response.setCreateAt(investigationPlan.getCreateAt());
                   response.setDeadlineDate(investigationPlan.getDeadlineDate());
                   response.setStatus(investigationPlan.getStatus());
                   response.setPlanContent(investigationPlan.getPlanContent());
                   response.setType(investigationPlan.getType());
                   response.setHolidayConflict(investigationPlan.getHolidayConflict());
                   response.setCreatedOfficerName(investigationPlan.getCreatedOfficerName());
                   response.setAcceptedOfficerName(investigationPlan.getAcceptedOfficerName());
                   response.setCaseId(investigationPlan.getCaseId());
              return response;
        }).toList();
        return investigationPlanResponses;
    }

    @Override
    public Page<InvestigationPlanResponse> findAll(String keyword, Pageable pageable) {
        // Find Investigation by keyword
        Specification<InvestigationPlan> specification = (root, query, criteriaBuilder) -> {
            if (keyword == null || keyword.isEmpty()) {
                return null;
            }
            // WHERE LOWER(status) LIKE %keyword%
            Predicate predicate = criteriaBuilder.like(
                criteriaBuilder.lower(root.get("status")),
                "%" + keyword.toLowerCase() + "%"
            );
            return criteriaBuilder.or(predicate);
        };

        var investigationPlansPage = investigationPlanRepository.findAll(specification, pageable);

        var investigationPlanResponses = investigationPlansPage.map(investigationPlan -> {
            var response = new InvestigationPlanResponse();
            response.setInvestigationPlanId(investigationPlan.getInvestigationPlanId());
            response.setSummary(investigationPlan.getSummary());
            response.setCreateAt(investigationPlan.getCreateAt());
            response.setDeadlineDate(investigationPlan.getDeadlineDate());
            response.setStatus(investigationPlan.getStatus());
            response.setPlanContent(investigationPlan.getPlanContent());
            response.setType(investigationPlan.getType());
            response.setHolidayConflict(investigationPlan.getHolidayConflict());
            response.setCreatedOfficerName(investigationPlan.getCreatedOfficerName());
            response.setAcceptedOfficerName(investigationPlan.getAcceptedOfficerName());
            response.setCaseId(investigationPlan.getCaseId());
            return response;
        });
        return investigationPlanResponses;
    }

    @Override
    public InvestigationPlanResponse createPlan(InvestigationPlanCreationRequest request) {
        //check request null
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        var investigationPlan = new InvestigationPlan();
        investigationPlan.setSummary(request.getSummary());
        investigationPlan.setCreateAt(request.getCreateAt());
        investigationPlan.setDeadlineDate(request.getDeadlineDate());
        investigationPlan.setStatus(request.getStatus());
        investigationPlan.setPlanContent(request.getPlanContent());
        investigationPlan.setType(request.getType());
        investigationPlan.setHolidayConflict(request.getHolidayConflict());
        investigationPlan.setCreatedOfficerName(request.getCreatedOfficerName());
        investigationPlan.setAcceptedOfficerName(request.getAcceptedOfficerName());
        investigationPlan.setCaseId(
                request.getCaseId() != null ? UUID.fromString(String.valueOf(request.getCaseId())) : null
        );
        investigationPlan.setDeleted(false); // Set default value for isDeleted

        //save
        investigationPlan = investigationPlanRepository.save(investigationPlan);

        //convert to response
        var response = new InvestigationPlanResponse();
        response.setInvestigationPlanId(investigationPlan.getInvestigationPlanId());
        response.setSummary(investigationPlan.getSummary());
        response.setCreateAt(investigationPlan.getCreateAt());
        response.setDeadlineDate(investigationPlan.getDeadlineDate());
        response.setStatus(investigationPlan.getStatus());
        response.setPlanContent(investigationPlan.getPlanContent());
        response.setType(investigationPlan.getType());
        response.setHolidayConflict(investigationPlan.getHolidayConflict());
        response.setCreatedOfficerName(investigationPlan.getCreatedOfficerName());
        response.setAcceptedOfficerName(investigationPlan.getAcceptedOfficerName());
        response.setCaseId(investigationPlan.getCaseId());

        return response;

    }

    @Override
    public List<InvestigationPlanResponse> getByCaseId(UUID caseId) {
        var plans = investigationPlanRepository.findByCaseId(UUID.fromString(caseId.toString()));

        return plans.stream().map(plan -> {
            var response = new InvestigationPlanResponse();
            response.setInvestigationPlanId(plan.getInvestigationPlanId());
            response.setSummary(plan.getSummary());
            response.setCreateAt(plan.getCreateAt());
            response.setDeadlineDate(plan.getDeadlineDate());
            response.setStatus(plan.getStatus());
            response.setPlanContent(plan.getPlanContent());
            response.setType(plan.getType());
            response.setHolidayConflict(plan.getHolidayConflict());
            response.setCreatedOfficerName(plan.getCreatedOfficerName());
            response.setAcceptedOfficerName(plan.getAcceptedOfficerName());
            response.setCaseId(plan.getCaseId());
            return response;
        }).toList();
    }

    @Override
    public InvestigationPlanResponse updatePlan(UUID id, InvestigationPlanCreationRequest request) {
        var plan = investigationPlanRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Investigation plan not found"));

        // Cập nhật thông tin
        plan.setSummary(request.getSummary());
        plan.setCreateAt(request.getCreateAt());
        plan.setDeadlineDate(request.getDeadlineDate());
        plan.setStatus(request.getStatus());
        plan.setPlanContent(request.getPlanContent());
        plan.setType(request.getType());
        plan.setHolidayConflict(request.getHolidayConflict());
        plan.setCreatedOfficerName(request.getCreatedOfficerName());
        plan.setAcceptedOfficerName(request.getAcceptedOfficerName());
        plan.setCaseId(UUID.fromString(request.getCaseId()));

        // Lưu lại
        plan = investigationPlanRepository.save(plan);

        // Trả về response
        var response = new InvestigationPlanResponse();
        response.setInvestigationPlanId(plan.getInvestigationPlanId());
        response.setSummary(plan.getSummary());
        response.setCreateAt(plan.getCreateAt());
        response.setDeadlineDate(plan.getDeadlineDate());
        response.setStatus(plan.getStatus());
        response.setPlanContent(plan.getPlanContent());
        response.setType(plan.getType());
        response.setHolidayConflict(plan.getHolidayConflict());
        response.setCreatedOfficerName(plan.getCreatedOfficerName());
        response.setAcceptedOfficerName(plan.getAcceptedOfficerName());
        response.setCaseId(plan.getCaseId());

        return response;
    }

    @Override
    public InvestigationPlanResponse deletePlan(UUID id, InvestigationPlanCreationRequest request) {
        var plan = investigationPlanRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Investigation plan not found"));

        plan.setDeleted(true); // Set isDeleted to true to mark as deleted

        var updatedPlan = investigationPlanRepository.save(plan);

        var response = new InvestigationPlanResponse();
        response.setInvestigationPlanId(updatedPlan.getInvestigationPlanId());
        response.setSummary(updatedPlan.getSummary());
        response.setCreateAt(updatedPlan.getCreateAt());
        response.setDeadlineDate(updatedPlan.getDeadlineDate());
        response.setStatus(updatedPlan.getStatus());
        response.setPlanContent(updatedPlan.getPlanContent());
        response.setType(updatedPlan.getType());
        response.setHolidayConflict(updatedPlan.getHolidayConflict());
        response.setCreatedOfficerName(updatedPlan.getCreatedOfficerName());
        response.setAcceptedOfficerName(updatedPlan.getAcceptedOfficerName());
        response.setCaseId(updatedPlan.getCaseId());

        return response;
    }

}
