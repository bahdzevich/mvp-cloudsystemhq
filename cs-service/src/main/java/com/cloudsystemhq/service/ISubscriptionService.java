package com.cloudsystemhq.service;

import com.cloudsystemhq.model.dto.request.SubscriptionRequestDto;
import com.cloudsystemhq.model.dto.response.SubscriptionResponseDto;

public interface ISubscriptionService extends
    IBaseService<SubscriptionRequestDto, SubscriptionResponseDto, Long> {

}
