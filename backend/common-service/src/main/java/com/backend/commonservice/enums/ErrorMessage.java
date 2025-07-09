/*
 * @ (#) ErrorMessage.java  1.0 7/7/2025
 *
 * Copyright (c) 2025. All rights reserved
 */

package com.backend.commonservice.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

/*
 * @description
 * @author: Khuong Pham
 * @date:   7/7/2025
 * @version:    1.0
 */
@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorMessage {
    INVALID_DATA("Validation failed", 400, HttpStatus.BAD_REQUEST),
    SUSPECT_STATUS_NOT_FOUND("Suspect status not found", 404, HttpStatus.NOT_FOUND),
    USER_NOT_FOUND("User not found", 404, HttpStatus.NOT_FOUND),
    INVALID_CREDENTIALS("username or password invalid", 401, HttpStatus.UNAUTHORIZED),
    ;
    String message;
    int code;
    HttpStatus httpStatus;
}
