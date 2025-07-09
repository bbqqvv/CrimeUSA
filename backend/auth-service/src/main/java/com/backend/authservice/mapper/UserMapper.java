/*
 * @ (#) UserMapper.java  1.0 7/8/2025
 *
 * Copyright (c) 2025. All rights reserved
 */

package com.backend.authservice.mapper;

import com.backend.authservice.dto.request.UserCreationRequest;
import com.backend.authservice.dto.response.UserResponse;
import com.backend.authservice.entity.User;
import org.mapstruct.Mapper;

/*
 * @description
 * @author: Khuong Pham
 * @date:   7/8/2025
 * @version:    1.0
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toUserRes(User user);
    User toUser(UserCreationRequest userCreationRequest);
    User createUser(UserCreationRequest user);
}
