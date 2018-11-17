package com.cloudsystemhq.service.util.mapping;

import com.cloudsystemhq.model.domain.order.Subscription;
import com.cloudsystemhq.model.dto.request.SubscriptionRequestDto;
import com.cloudsystemhq.model.dto.response.SubscriptionResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper extends
    EntityMapper<Subscription, SubscriptionRequestDto, SubscriptionResponseDto> {

}
