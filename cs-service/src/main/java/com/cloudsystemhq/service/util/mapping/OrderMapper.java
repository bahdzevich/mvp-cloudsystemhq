package com.cloudsystemhq.service.util.mapping;

import com.cloudsystemhq.model.domain.order.Order;
import com.cloudsystemhq.model.dto.request.OrderRequestDto;
import com.cloudsystemhq.model.dto.response.OrderResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper
    extends EntityMapper<Order, OrderRequestDto, OrderResponseDto> {

}
