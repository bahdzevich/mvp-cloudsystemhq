package com.cloudsystemhq.service.impl;

import com.cloudsystemhq.model.domain.order.Subscription;
import com.cloudsystemhq.model.dto.request.SubscriptionRequestDto;
import com.cloudsystemhq.model.dto.response.SubscriptionResponseDto;
import com.cloudsystemhq.repository.SubscriptionRepository;
import com.cloudsystemhq.service.ISubscriptionService;
import com.cloudsystemhq.service.util.mapping.SubscriptionMapper;
import java.util.function.Function;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl extends
    AbstractBaseServiceImpl<Subscription, SubscriptionRequestDto, SubscriptionResponseDto, Long> implements
    ISubscriptionService {

  public SubscriptionServiceImpl(SubscriptionRepository repository, SubscriptionMapper mapper) {
    super(repository, mapper);
  }

  @Override
  Function<Subscription, Subscription> updateEntity(Subscription newEntity) {
    return persistedSubscription -> {
      persistedSubscription.setNumberOfMonth(newEntity.getNumberOfMonth());
      return repository.save(persistedSubscription);
    };
  }
}
