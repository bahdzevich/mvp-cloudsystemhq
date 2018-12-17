package com.cloudsystemhq.service.impl;

import com.cloudsystemhq.model.domain.order.InfrastructureProvider;
import com.cloudsystemhq.model.dto.request.InfrastructureProviderRequestDto;
import com.cloudsystemhq.model.dto.response.InfrastructureProviderResponseDto;
import com.cloudsystemhq.repository.InfrastructureProviderRepository;
import com.cloudsystemhq.security.service.EntityAlreadyExistsException;
import com.cloudsystemhq.service.IInfrastructureProviderService;
import com.cloudsystemhq.service.util.mapping.InfrastructureProviderMapper;
import java.util.function.Function;
import org.springframework.stereotype.Service;

@Service
public class InfrastructureProviderServiceImpl extends
    AbstractBaseServiceImpl<InfrastructureProvider, InfrastructureProviderRequestDto, InfrastructureProviderResponseDto, Long> implements
    IInfrastructureProviderService {

  private final InfrastructureProviderRepository infrastructureProviderRepository;

  public InfrastructureProviderServiceImpl(
      InfrastructureProviderRepository repository,
      InfrastructureProviderMapper mapper) {
    super(repository, mapper);
    this.infrastructureProviderRepository = repository;
  }

  @Override
  public InfrastructureProviderResponseDto create(
      InfrastructureProviderRequestDto infrastructureProvider) {
    infrastructureProviderRepository
        .findByProviderName(infrastructureProvider.getProviderName())
        .ifPresent(entity -> {
          throw new EntityAlreadyExistsException(
              String.format("Infrastructure provider with name '%s' already exists",
                  infrastructureProvider.getProviderName()));
        });
    return super.create(infrastructureProvider);
  }

  @Override
  Function<InfrastructureProvider, InfrastructureProvider> updateEntity(
      InfrastructureProvider newEntity) {
    infrastructureProviderRepository
        .findByProviderName(newEntity.getProviderName())
        .ifPresent(entity -> {
          throw new EntityAlreadyExistsException(
              String.format("Infrastructure provider with name '%s' already exists",
                  newEntity.getProviderName()));
        });
    return persistedInfrastructureProvider -> {
      persistedInfrastructureProvider.setProviderName(newEntity.getProviderName());
      return repository.save(persistedInfrastructureProvider);
    };
  }
}
