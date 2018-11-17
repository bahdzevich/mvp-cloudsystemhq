package com.cloudsystemhq.service.util.mapping;

import com.cloudsystemhq.model.domain.order.Urgency;
import com.cloudsystemhq.model.dto.request.UrgencyRequestDto;
import com.cloudsystemhq.model.dto.response.UrgencyResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UrgencyMapper extends
    EntityMapper<Urgency, UrgencyRequestDto, UrgencyResponseDto> {

}
