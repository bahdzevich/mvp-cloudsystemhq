package com.cloudsystemhq.service.impl;

import com.cloudsystemhq.model.domain.order.SupportType;
import com.cloudsystemhq.model.dto.request.SupportTypeRequestDto;
import com.cloudsystemhq.model.dto.response.SupportTypeResponseDto;
import com.cloudsystemhq.repository.SupportTypeRepository;
import com.cloudsystemhq.security.service.EntityAlreadyExistsException;
import com.cloudsystemhq.service.ISupportTypeService;
import com.cloudsystemhq.service.util.mapping.SupportTypeMapper;
import java.util.function.Function;
import org.springframework.stereotype.Service;

@Service
public class SupportTypeServiceImpl extends
    AbstractBaseServiceImpl<SupportType, SupportTypeRequestDto, SupportTypeResponseDto, Long> implements
    ISupportTypeService {

  private SupportTypeRepository supportTypeRepository;

  public SupportTypeServiceImpl(SupportTypeRepository repository, SupportTypeMapper mapper) {
    super(repository, mapper);
    this.supportTypeRepository = repository;
  }

  @Override
  public SupportTypeResponseDto create(SupportTypeRequestDto supportTypeRequestDto) {
    supportTypeRepository
        .findByType(supportTypeRequestDto.getType())
        .ifPresent(entity -> {
          throw new EntityAlreadyExistsException(
              String.format("Support type: '%s' already exists", supportTypeRequestDto.getType()));
        });
    return super.create(supportTypeRequestDto);
  }

  @Override
  Function<SupportType, SupportType> updateEntity(SupportType newEntity) {
    supportTypeRepository
        .findByType(newEntity.getType())
        .ifPresent(entity -> {
          throw new EntityAlreadyExistsException(
              String.format("Support type: '%s' already exists", newEntity.getType()));
        });
    return persistedSupportType -> {
      persistedSupportType.setType(newEntity.getType());
      persistedSupportType.setChat(newEntity.getChat());
      persistedSupportType.setEmail(newEntity.getEmail());
      persistedSupportType.setOnlyWorkingHours(newEntity.getOnlyWorkingHours());
      persistedSupportType.setPhone(newEntity.getPhone());
      persistedSupportType.setRoundTheClock(newEntity.getRoundTheClock());
      return repository.save(persistedSupportType);
    };
  }
}
