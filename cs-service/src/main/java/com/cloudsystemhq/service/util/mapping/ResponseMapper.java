package com.cloudsystemhq.service.util.mapping;

import com.cloudsystemhq.model.domain.Response;
import com.cloudsystemhq.model.dto.request.ResponseRequestDto;
import com.cloudsystemhq.model.dto.response.ResponseResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResponseMapper
        extends EntityMapper<Response, ResponseRequestDto, ResponseResponseDto> {
}
