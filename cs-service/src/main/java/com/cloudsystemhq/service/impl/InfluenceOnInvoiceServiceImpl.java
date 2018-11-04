package com.cloudsystemhq.service.impl;

import com.cloudsystemhq.model.domain.InfluenceOnPrice;
import com.cloudsystemhq.repository.InfluenceOnInvoiceRepository;
import com.cloudsystemhq.repository.ResponseRepository;
import com.cloudsystemhq.service.IInfluenceOnInvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.Optional;


@Service
public class InfluenceOnInvoiceServiceImpl implements IInfluenceOnInvoiceService{

    private final static Logger LOGGER = LoggerFactory.getLogger(InfluenceOnInvoiceServiceImpl.class.getName());
    private final InfluenceOnInvoiceRepository influenceRepository;
    private final ResponseRepository responseRepository;

    @Autowired
    public InfluenceOnInvoiceServiceImpl(InfluenceOnInvoiceRepository influenceOnInvoiceRepository,
                                         ResponseRepository responseRepository){
        this.influenceRepository = influenceOnInvoiceRepository;
        this.responseRepository = responseRepository;
    }

    @Override
    public Optional<InfluenceOnPrice> create(Long responseId, InfluenceOnPrice influenceOnInvoice) {
        return responseRepository.findById(responseId).map(response -> {
            response.setInfluenceOnPrice(Collections.singleton(influenceOnInvoice));
            influenceOnInvoice.setResponse(response);
            responseRepository.save(response);
            return influenceOnInvoice;  // id == null, because returned object not persisted yet
        });
    }

    @Override
    public Optional<InfluenceOnPrice> findInfluenceByResponseId(Long responseId) {
        if (!responseRepository.existsById(responseId)){
            LOGGER.warn("There is no response with id=" + responseId);
        }
        return influenceRepository.findByResponseId(responseId);
    }

    @Override
    public Optional<InfluenceOnPrice> update(final Long responseId, final InfluenceOnPrice influenceOnInvoice) {
        if(!responseRepository.existsById(responseId)) {
            LOGGER.warn("There is no response with id=" + responseId);
        }
        return influenceRepository.findById(responseId).map(persistedInfluence -> {
//            persistedInfluence.setInvoiceParameters(influenceOnInvoice.getInvoiceParameters());
//            persistedInfluence.setPriceDependencies(influenceOnInvoice.getPriceDependencies());
            persistedInfluence.setResponse(influenceOnInvoice.getResponse());
            return influenceRepository.save(persistedInfluence);
        });
    }

    @Override
    public Optional<InfluenceOnPrice> delete(final Long responseId) {
        Assert.notNull(responseId, "Response id is null.");
        if(!responseRepository.existsById(responseId)) {
            LOGGER.warn("There is no response with id=" + responseId);
        }
        return influenceRepository.findById(responseId).map(influence -> {
            influenceRepository.delete(influence);
            return influence;
        });
    }
}
