package com.cloudsystemhq.service.impl;

import com.cloudsystemhq.exception.ResourceNotFoundException;
import com.cloudsystemhq.model.domain.InfluenceOnInvoice;
import com.cloudsystemhq.repository.InfluenceOnInvoiceRepository;
import com.cloudsystemhq.repository.ResponseRepository;
import com.cloudsystemhq.service.IInfluenceOnInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class InfluenceOnInvoiceServiceImpl implements IInfluenceOnInvoiceService{

    private final InfluenceOnInvoiceRepository influenceRepository;
    private final ResponseRepository responseRepository;

    @Autowired
    public InfluenceOnInvoiceServiceImpl(InfluenceOnInvoiceRepository influenceOnInvoiceRepository,
                                         ResponseRepository responseRepository){
        this.influenceRepository = influenceOnInvoiceRepository;
        this.responseRepository = responseRepository;
    }

    @Override
    public InfluenceOnInvoice create(Long responseId, InfluenceOnInvoice influenceOnInvoice) {
        Assert.notNull(influenceOnInvoice, "InfluenceOnInvoice is null.");
        return responseRepository.findById(responseId).map(response -> {
            response.setInfluenceOnInvoice(influenceOnInvoice);
            influenceOnInvoice.setResponse(response);
            responseRepository.save(response);
            return influenceOnInvoice;  // id == null, because returned object not persisted yet
        }).orElseThrow(() -> new ResourceNotFoundException("ResponseId " + responseId + " not found"));
    }

    @Override
    public InfluenceOnInvoice findInfluenceByResponseId(Long responseId) {
        Assert.notNull(responseId, "Response id is null.");
        return influenceRepository.findByResponseId(responseId)
                .orElseThrow(() -> new ResourceNotFoundException("ResponseId " + responseId + " not found"));
    }

    @Override
    public InfluenceOnInvoice update(final Long responseId, final InfluenceOnInvoice influenceOnInvoice) {
        Assert.notNull(responseId, "InfluenceOnInvoice id is null.");
        Assert.notNull(influenceOnInvoice, "InfluenceOnInvoice is null.");
        if(!responseRepository.existsById(responseId)) {
            throw new ResourceNotFoundException("ResponseId " + responseId + " not found");
        }

        return influenceRepository.findById(responseId).map(persistedInfluence -> {
            persistedInfluence.setPrice(influenceOnInvoice.getPrice());
            persistedInfluence.setResponse(influenceOnInvoice.getResponse());
            return influenceRepository.save(persistedInfluence);
        }).orElseThrow(() -> new ResourceNotFoundException("InfluenceId " + responseId + " not found"));
    }

    @Override
    public InfluenceOnInvoice delete(final Long responseId) {
        Assert.notNull(responseId, "Response id is null.");
        if(!responseRepository.existsById(responseId)) {
            throw new ResourceNotFoundException("ResponseId " + responseId + " not found");
        }

        return influenceRepository.findById(responseId).map(influence -> {
            influenceRepository.delete(influence);
            return influence;
        }).orElseThrow(() -> new ResourceNotFoundException("InfluenceId " + responseId + " not found"));
    }
}
