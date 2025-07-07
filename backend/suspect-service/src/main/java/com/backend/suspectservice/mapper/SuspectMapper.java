/*
 * @ (#) SuspectMapper.java  1.0 7/7/2025
 *
 * Copyright (c) 2025. All rights reserved
 */

package com.backend.suspectservice.mapper;

import com.backend.suspectservice.dto.request.SuspectCreateRequest;
import com.backend.suspectservice.dto.response.SuspectResponse;
import com.backend.suspectservice.model.Suspect;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/*
 * @description
 * @author: Khuong Pham
 * @date:   7/7/2025
 * @version:    1.0
 */
@Mapper(componentModel = "spring")
public interface SuspectMapper {
    SuspectResponse toSuspectResponse(Suspect suspect);
    Suspect createSuspect(SuspectCreateRequest suspect);
    void updateSuspect(@MappingTarget Suspect suspect , SuspectCreateRequest sus);

}
