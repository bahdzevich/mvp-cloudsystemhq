package com.cloudsystemhq.controller.rest;

import com.cloudsystemhq.service.IBaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * Abstract REST controller with implemented CRUD operations.
 *
 * @param <REQUEST>
 * @param <RESPONSE>
 * @param <PK>
 * @param <SERVICE>
 */
public abstract class AbstractCrudRestController<
        REQUEST,
        RESPONSE,
        PK extends Serializable,
        SERVICE extends IBaseService<REQUEST, RESPONSE, PK>> {

    protected SERVICE service;

    public AbstractCrudRestController(SERVICE service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RESPONSE> create(@RequestBody REQUEST request) {
        RESPONSE response = service.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id:[0-9]+}")
    public ResponseEntity<RESPONSE> findOne(@PathVariable PK id) {
        return service
                .findOne(id)
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<RESPONSE>> findAll() {
        List<RESPONSE> responseList = service.findAll();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    //  @GetMapping
    //  public ResponseEntity<List<RESPONSE>> foundPage(
    //      @RequestParam(value = "page") Integer pageNumber,
    //      @RequestParam(value = "size") Integer pageSize) {
    //    List<RESPONSE> list = service.findPage(pageNumber, pageSize).getContent();
    //    return new ResponseEntity<>(list, HttpStatus.OK);
    //  }

    @PutMapping(value = "/{id:[0-9]+}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RESPONSE> update(@PathVariable PK id, @RequestBody REQUEST request) {
        return service
                .update(id, request)
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/{id:[0-9]+}")
    public ResponseEntity<RESPONSE> delete(@PathVariable PK id) {
        return service
                .delete(id)
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
