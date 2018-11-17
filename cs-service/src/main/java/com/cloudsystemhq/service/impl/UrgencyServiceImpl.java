package com.cloudsystemhq.service.impl;

import com.cloudsystemhq.model.domain.order.Urgency;
import com.cloudsystemhq.model.dto.request.UrgencyRequestDto;
import com.cloudsystemhq.model.dto.response.UrgencyResponseDto;
import com.cloudsystemhq.repository.UrgencyRepository;
import com.cloudsystemhq.service.IUrgencyService;
import com.cloudsystemhq.service.util.mapping.UrgencyMapper;
import java.util.function.Function;
import org.springframework.stereotype.Service;

@Service
public class UrgencyServiceImpl extends
    AbstractBaseServiceImpl<Urgency, UrgencyRequestDto, UrgencyResponseDto, Long> implements
    IUrgencyService {

  public UrgencyServiceImpl(UrgencyRepository repository, UrgencyMapper mapper) {
    super(repository, mapper);
  }

  @Override
  Function<Urgency, Urgency> updateEntity(Urgency newEntity) {
    return persistedUrgency -> {
      persistedUrgency.setHours(newEntity.getHours());
      persistedUrgency.setType(newEntity.getType());
      return repository.save(persistedUrgency);
    };
  }
}
