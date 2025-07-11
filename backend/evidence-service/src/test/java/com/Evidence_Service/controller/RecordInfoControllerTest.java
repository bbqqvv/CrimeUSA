package com.Evidence_Service.controller;

import com.Evidence_Service.dto.RecordInfoDTO;
import com.Evidence_Service.dto.response.ApiResponse;
import com.Evidence_Service.service.RecordInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RecordInfoController.class)
class RecordInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecordInfoService recordInfoService;

    @Autowired
    private ObjectMapper objectMapper;

    private RecordInfoDTO recordInfoDTO;

    @BeforeEach
    void setUp() {
        recordInfoDTO = RecordInfoDTO.builder()
                .recordInfoId("record1")
                .evidenceId("evidence1")
                .typeName("Image")
                .source("Camera")
                .dateCollected(LocalDateTime.now())
                .summary("Test summary")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .isDeleted(false)
                .build();
    }

    @Test
    @WithMockUser(authorities = "ADD_RECORD_INFO")
    void createRecordInfo_Success() throws Exception {
        when(recordInfoService.createRecordInfo(any(RecordInfoDTO.class))).thenReturn(recordInfoDTO);

        mockMvc.perform(post("/api/v1/record-info")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(recordInfoDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(201))
                .andExpect(jsonPath("$.message").value("Record info created"))
                .andExpect(jsonPath("$.data.recordInfoId").value("record1"));

        verify(recordInfoService).createRecordInfo(any(RecordInfoDTO.class));
    }

    @Test
    @WithMockUser(authorities = "VIEW_RECORD_INFO")
    void getRecordInfoById_Success() throws Exception {
        when(recordInfoService.getRecordInfoByRecordInfoId("record1")).thenReturn(recordInfoDTO);

        mockMvc.perform(get("/api/v1/record-info/record1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("Fetched record info"))
                .andExpect(jsonPath("$.data.recordInfoId").value("record1"));

        verify(recordInfoService).getRecordInfoByRecordInfoId("record1");
    }

    @Test
    @WithMockUser(authorities = "VIEW_RECORD_INFO")
    void getAllRecordInfoByEvidenceId_Success() throws Exception {
        Page<RecordInfoDTO> page = new PageImpl<>(Collections.singletonList(recordInfoDTO));
        when(recordInfoService.getRecordInfoByEvidenceId(eq("evidence1"), any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/api/v1/record-info")
                        .param("evidenceId", "evidence1")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("Fetched all record info"))
                .andExpect(jsonPath("$.data.content[0].recordInfoId").value("record1"));

        verify(recordInfoService).getRecordInfoByEvidenceId(eq("evidence1"), any(Pageable.class));
    }

    @Test
    @WithMockUser(authorities = "EDIT_RECORD_INFO")
    void updateRecordInfo_Success() throws Exception {
        when(recordInfoService.updateRecordInfo(eq("record1"), any(RecordInfoDTO.class))).thenReturn(recordInfoDTO);

        mockMvc.perform(put("/api/v1/record-info/record1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(recordInfoDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("Record info updated"))
                .andExpect(jsonPath("$.data.recordInfoId").value("record1"));

        verify(recordInfoService).updateRecordInfo(eq("record1"), any(RecordInfoDTO.class));
    }

    @Test
    @WithMockUser(authorities = "DELETE_RECORD_INFO")
    void deleteRecordInfo_Success() throws Exception {
        doNothing().when(recordInfoService).deleteRecordInfoByRecordInfoId("record1");

        mockMvc.perform(delete("/api/v1/record-info/record1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("Record info deleted"));

        verify(recordInfoService).deleteRecordInfoByRecordInfoId("record1");
    }
}