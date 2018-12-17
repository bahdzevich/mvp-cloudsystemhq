package com.cloudsystemhq.service.impl;

import com.cloudsystemhq.model.domain.order.Subscription;
import com.cloudsystemhq.model.dto.request.SubscriptionRequestDto;
import com.cloudsystemhq.model.dto.response.SubscriptionResponseDto;
import com.cloudsystemhq.repository.SubscriptionRepository;
import com.cloudsystemhq.security.service.EntityAlreadyExistsException;
import com.cloudsystemhq.service.ISubscriptionService;
import com.cloudsystemhq.service.util.mapping.SubscriptionMapper;
import java.util.function.Function;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl extends
    AbstractBaseServiceImpl<Subscription, SubscriptionRequestDto, SubscriptionResponseDto, Long> implements
    ISubscriptionService {

  private final SubscriptionRepository subscriptionRepository;

  public SubscriptionServiceImpl(SubscriptionRepository repository, SubscriptionMapper mapper) {
    super(repository, mapper);
    this.subscriptionRepository = repository;
  }

  @Override
  public SubscriptionResponseDto create(SubscriptionRequestDto subscriptionRequestDto) {
    subscriptionRepository
        .findByNumberOfMonth(subscriptionRequestDto.getNumberOfMonth())
        .ifPresent(entity -> {
          throw new EntityAlreadyExistsException(
              String.format("Subscription with number of month: '%d' already exists",
                  subscriptionRequestDto.getNumberOfMonth()));
        });
    return super.create(subscriptionRequestDto);
  }

  @Override
  Function<Subscription, Subscription> updateEntity(Subscription newEntity) {
    subscriptionRepository
        .findByNumberOfMonth(newEntity.getNumberOfMonth())
        .ifPresent(entity -> {
          throw new EntityAlreadyExistsException(
              String.format("Subscription with number of month: '%d' already exists",
                  newEntity.getNumberOfMonth()));
        });
    return persistedSubscription -> {
      persistedSubscription.setNumberOfMonth(newEntity.getNumberOfMonth());
      return persistedSubscription;
    };
  }
}
