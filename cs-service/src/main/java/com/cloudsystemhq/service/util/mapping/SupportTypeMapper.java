package com.cloudsystemhq.service.util.mapping;

import com.cloudsystemhq.model.domain.order.SupportType;
import com.cloudsystemhq.model.dto.request.SupportTypeRequestDto;
import com.cloudsystemhq.model.dto.response.SupportTypeResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupportTypeMapper extends
    EntityMapper<SupportType, SupportTypeRequestDto, SupportTypeResponseDto> {

}
