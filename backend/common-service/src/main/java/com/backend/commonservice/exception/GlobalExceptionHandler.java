/*
 * @ (#) GlobalExceptionHandler.java  1.0 7/7/2025
 *
 * Copyright (c) 2025. All rights reserved
 */

package com.backend.commonservice.exception;

import com.backend.commonservice.dto.response.ApiResponseDTO;
import com.backend.commonservice.enums.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/*
 * @description
 * @author: Khuong Pham
 * @date:   7/7/2025
 * @version:    1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponseDTO<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ApiResponseDTO<Map<String, String>> response = new ApiResponseDTO<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        response.setCode(ErrorMessage.INVALID_DATA.getCode());
        response.setMessage(ErrorMessage.INVALID_DATA.getMessage());
        response.setErrors(errors);
        return new ResponseEntity<>(response, ErrorMessage.INVALID_DATA.getHttpStatus());
    }
}
