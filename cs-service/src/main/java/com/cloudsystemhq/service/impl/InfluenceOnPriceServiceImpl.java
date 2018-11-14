package com.cloudsystemhq.service.impl;

import com.cloudsystemhq.model.domain.InfluenceOnPrice;
import com.cloudsystemhq.model.dto.request.InfluenceOnPriceRequestDto;
import com.cloudsystemhq.model.dto.response.InfluenceOnPriceResponseDto;
import com.cloudsystemhq.repository.InfluenceOnPriceRepository;
import com.cloudsystemhq.repository.ResponseRepository;
import com.cloudsystemhq.service.IInfluenceOnPriceService;
import com.cloudsystemhq.service.util.mapping.InfluenceOnPriceMapper;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class InfluenceOnPriceServiceImpl
        extends AbstractBaseServiceImpl<InfluenceOnPrice, InfluenceOnPriceRequestDto, InfluenceOnPriceResponseDto, Long>
        implements IInfluenceOnPriceService {

    private final static Logger LOGGER = LoggerFactory.getLogger(InfluenceOnPriceServiceImpl.class.getName());
    private final ResponseRepository responseRepository;
    private final InfluenceOnPriceRepository influenceOnPriceRepository;

    @Autowired
    public InfluenceOnPriceServiceImpl(
            InfluenceOnPriceRepository influenceOnInvoiceRepository,
            InfluenceOnPriceMapper mapper,
            ResponseRepository responseRepository){
        super(influenceOnInvoiceRepository, mapper);
        this.responseRepository = responseRepository;
        this.influenceOnPriceRepository = influenceOnInvoiceRepository;
    }

    public Optional<InfluenceOnPriceResponseDto> create(Long responseId, final InfluenceOnPriceRequestDto influenceOnPriceRequestDto) {
        InfluenceOnPrice influenceOnPrice = mapper.convertToEntity(influenceOnPriceRequestDto);
        return responseRepository.findById(responseId).map(response -> {
            response.getInfluenceOnPrice().add(influenceOnPrice);
          influenceOnPrice.setAnswer(response);
            responseRepository.save(response);
            return mapper.convertToDto(influenceOnPrice);  // id == null, because returned object not persisted yet
        });
    }

    @Override
    public Optional<InfluenceOnPriceResponseDto> findOne(final Long influenceId) {
        return super.findOne(influenceId);
    }

    @Override
    public List<InfluenceOnPriceResponseDto> findInfluencesByResponseId(final Long responseId) {
        if (!responseRepository.existsById(responseId)){
          LOGGER.warn("There is no answer with id=" + responseId);
        }
        return influenceOnPriceRepository.findByResponseId(responseId)
                .stream()
                .map(mapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<InfluenceOnPriceResponseDto> update(final Long influenceId,
                                             final InfluenceOnPriceRequestDto influenceOnPrice) {
        return super.update(influenceId, influenceOnPrice);
    }

    @Override
    public Optional<InfluenceOnPriceResponseDto> delete(final Long influenceId) {
        return super.delete(influenceId);
    }

    @Override
    Function<InfluenceOnPrice, InfluenceOnPrice> updateEntity(InfluenceOnPrice influenceOnPrice) {
        return persistedInfluence -> {
            persistedInfluence.setCoefficient(influenceOnPrice.getCoefficient());
            persistedInfluence.setExtraPriceForSection(influenceOnPrice.getExtraPriceForSection());
            persistedInfluence.setInvoiceSection(influenceOnPrice.getInvoiceSection());
            persistedInfluence.setOperationType(influenceOnPrice.getOperationType());
            persistedInfluence.setPricePerUnit(influenceOnPrice.getPricePerUnit());
            persistedInfluence.setQuantity(influenceOnPrice.getQuantity());
            return influenceOnPriceRepository.save(persistedInfluence);
        };
    }
}
