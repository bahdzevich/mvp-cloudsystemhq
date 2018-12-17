package com.cloudsystemhq.service.impl;

import com.cloudsystemhq.model.domain.order.Urgency;
import com.cloudsystemhq.model.dto.request.UrgencyRequestDto;
import com.cloudsystemhq.model.dto.response.UrgencyResponseDto;
import com.cloudsystemhq.repository.UrgencyRepository;
import com.cloudsystemhq.security.service.EntityAlreadyExistsException;
import com.cloudsystemhq.service.IUrgencyService;
import com.cloudsystemhq.service.util.mapping.UrgencyMapper;
import java.util.function.Function;
import org.springframework.stereotype.Service;

@Service
public class UrgencyServiceImpl extends
    AbstractBaseServiceImpl<Urgency, UrgencyRequestDto, UrgencyResponseDto, Long> implements
    IUrgencyService {

  private final UrgencyRepository urgencyRepository;

  public UrgencyServiceImpl(UrgencyRepository repository, UrgencyMapper mapper) {
    super(repository, mapper);
    this.urgencyRepository = repository;
  }

  @Override
  public UrgencyResponseDto create(UrgencyRequestDto urgencyRequestDto) {
    urgencyRepository
        .findUrgencyByType(urgencyRequestDto.getType())
        .ifPresent(entity -> {
          throw new EntityAlreadyExistsException(
              String.format("Urgency type: '%s' already exists", urgencyRequestDto.getType()));
        });
    return super.create(urgencyRequestDto);
  }

  @Override
  Function<Urgency, Urgency> updateEntity(Urgency newEntity) {
    urgencyRepository
        .findUrgencyByType(newEntity.getType())
        .ifPresent(entity -> {
          throw new EntityAlreadyExistsException(
              String.format("Urgency type: '%s' already exists", newEntity.getType()));
        });
    return persistedUrgency -> {
      persistedUrgency.setType(newEntity.getType());
      persistedUrgency.setHours(newEntity.getHours());
      return repository.save(persistedUrgency);
    };
  }
}
