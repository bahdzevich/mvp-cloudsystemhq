package com.cloudsystemhq.service.impl;

import com.cloudsystemhq.model.domain.order.SupportType;
import com.cloudsystemhq.model.dto.request.SupportTypeRequestDto;
import com.cloudsystemhq.model.dto.response.SupportTypeResponseDto;
import com.cloudsystemhq.repository.SupportTypeRepository;
import com.cloudsystemhq.service.ISupportTypeService;
import com.cloudsystemhq.service.util.mapping.SupportTypeMapper;
import java.util.function.Function;
import org.springframework.stereotype.Service;

@Service
public class SupportTypeServiceImpl extends
    AbstractBaseServiceImpl<SupportType, SupportTypeRequestDto, SupportTypeResponseDto, Long> implements
    ISupportTypeService {

  public SupportTypeServiceImpl(SupportTypeRepository repository, SupportTypeMapper mapper) {
    super(repository, mapper);
  }

  @Override
  Function<SupportType, SupportType> updateEntity(SupportType newEntity) {
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
