package com.cloudsystemhq.service.impl;

import com.cloudsystemhq.service.IBaseService;
import com.cloudsystemhq.service.util.mapping.EntityMapper;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

public abstract class AbstractBaseServiceImpl<E, IN, OUT, PK extends Serializable> implements
    IBaseService<IN, OUT, PK> {

  protected final JpaRepository<E, PK> repository;
  protected final EntityMapper<E, IN, OUT> mapper;

  protected AbstractBaseServiceImpl(JpaRepository<E, PK> repository,
      EntityMapper<E, IN, OUT> mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public OUT create(IN in) {
    Assert.notNull(in, String.format("%s is null.", in.getClass().getName()));
    E entity = mapper.convertToEntity(in);
    E savedEntity = repository.save(entity);
    return mapper.convertToDto(savedEntity);
  }

  @Override
  public Optional<OUT> findOne(PK pk) {
    Assert.notNull(pk, "Id is null.");
    return repository.findById(pk)
        .map(mapper::convertToDto);
  }

  @Override
  public List<OUT> findAll() {
    return repository.findAll()
        .parallelStream()
        .map(mapper::convertToDto)
        .collect(Collectors.toList());
  }

  @Override
  public Page<OUT> findPage(Integer page, Integer size) {
    Assert.notNull(page, "Page number is null.");
    Assert.notNull(size, "Page size is null.");
    return repository.findAll(PageRequest.of(page, size))
        .map(mapper::convertToDto);
  }

  @Override
  @Transactional
  public Optional<OUT> update(PK pk, IN in) {
    Assert.notNull(pk, "Id is null.");
    Assert.notNull(in, String.format("%s is null.", in.getClass().getName()));
    return repository
        .findById(pk)
        .map(updateEntity(mapper.convertToEntity(in)))
        .map(mapper::convertToDto);
  }

  @Override
  @Transactional
  public Optional<OUT> delete(PK pk) {
    Assert.notNull(pk, "Id is null.");
    Optional<E> entityOptional = repository.findById(pk);
    entityOptional.ifPresent(repository::delete);
    return entityOptional.map(mapper::convertToDto);
  }

  abstract Function<E, E> updateEntity(E newEntity);
}
