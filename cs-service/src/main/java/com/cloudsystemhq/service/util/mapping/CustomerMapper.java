package com.cloudsystemhq.service.util.mapping;

import com.cloudsystemhq.model.domain.Customer;
import com.cloudsystemhq.model.dto.request.CustomerRequestDto;
import com.cloudsystemhq.model.dto.response.CustomerResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper extends
    EntityMapper<Customer, CustomerRequestDto, CustomerResponseDto> {

}
