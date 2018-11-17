package com.cloudsystemhq.service.util.mapping;

import com.cloudsystemhq.model.domain.order.InfrastructureProvider;
import com.cloudsystemhq.model.dto.request.InfrastructureProviderRequestDto;
import com.cloudsystemhq.model.dto.response.InfrastructureProviderResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InfrastructureProviderMapper extends
    EntityMapper<InfrastructureProvider, InfrastructureProviderRequestDto, InfrastructureProviderResponseDto> {

}
