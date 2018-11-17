package com.cloudsystemhq.service.impl;

import com.cloudsystemhq.model.domain.order.InfrastructureProvider;
import com.cloudsystemhq.model.dto.request.InfrastructureProviderRequestDto;
import com.cloudsystemhq.model.dto.response.InfrastructureProviderResponseDto;
import com.cloudsystemhq.repository.InfrastructureProviderRepository;
import com.cloudsystemhq.service.IInfrastructureProviderService;
import com.cloudsystemhq.service.util.mapping.InfrastructureProviderMapper;
import java.util.function.Function;
import org.springframework.stereotype.Service;

@Service
public class InfrastructureProviderServiceImpl extends
    AbstractBaseServiceImpl<InfrastructureProvider, InfrastructureProviderRequestDto, InfrastructureProviderResponseDto, Long> implements
    IInfrastructureProviderService {

  public InfrastructureProviderServiceImpl(
      InfrastructureProviderRepository repository,
      InfrastructureProviderMapper mapper) {
    super(repository, mapper);
  }

  @Override
  Function<InfrastructureProvider, InfrastructureProvider> updateEntity(
      InfrastructureProvider newEntity) {
    return persistedInfrastructureProvider -> {
      persistedInfrastructureProvider.setProviderName(newEntity.getProviderName());
      return repository.save(persistedInfrastructureProvider);
    };
  }
}
