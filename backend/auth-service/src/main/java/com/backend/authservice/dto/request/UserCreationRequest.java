/*
 * @ (#) UserCreationRequest.java  1.0 7/8/2025
 *
 * Copyright (c) 2025. All rights reserved
 */

package com.backend.authservice.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/*
 * @description
 * @author: Khuong Pham
 * @date:   7/8/2025
 * @version:    1.0
 */
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    String username;
    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    String passwordHash;
}
