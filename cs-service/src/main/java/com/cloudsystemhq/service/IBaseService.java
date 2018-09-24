package com.cloudsystemhq.service;

import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Base interface for all services.
 *
 * @param <IN>  is an input type (example: UserInputDto)
 * @param <OUT> is an output type (example: UserOutputDto)
 * @param <PK>  is an entity primary key type
 */
public interface IBaseService<IN, OUT, PK extends Serializable> {

    OUT create(IN in);

    Optional<OUT> findOne(PK pk);

    List<OUT> findAll();

    Page<OUT> findPage(Integer page, Integer size);

    Optional<OUT> update(PK pk, IN in);

    Optional<OUT> delete(PK pk);
}

