package com.cloudsystemhq.service;

import com.cloudsystemhq.model.dto.request.OrderRequestDto;
import com.cloudsystemhq.model.dto.response.OrderResponseDto;
import java.util.List;
import java.util.Optional;

public interface IOrderService extends
    IBaseService<OrderRequestDto, OrderResponseDto, Long> {

  List<OrderResponseDto> findOrdersByCustomerId(final Long customerId);

  Optional<OrderResponseDto> create(final Long customerId,
      final OrderRequestDto orderRequestDto);

  Optional<OrderResponseDto> update(final Long customerId, final Long orderId,
      final OrderRequestDto influenceOnPrice);
}
