package com.cloudsystemhq.service.util.mapping;

public interface EntityMapper<ENTITY, REQUEST, RESPONSE> {
  RESPONSE convertToDto(ENTITY entity);
  ENTITY convertToEntity(REQUEST dto);
}
