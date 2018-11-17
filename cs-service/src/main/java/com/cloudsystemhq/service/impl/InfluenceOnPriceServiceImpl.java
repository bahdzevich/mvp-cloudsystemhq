package com.cloudsystemhq.service.impl;

import com.cloudsystemhq.model.domain.InfluenceOnPrice;
import com.cloudsystemhq.model.dto.request.InfluenceOnPriceRequestDto;
import com.cloudsystemhq.model.dto.response.InfluenceOnPriceResponseDto;
import com.cloudsystemhq.repository.AnswerRepository;
import com.cloudsystemhq.repository.InfluenceOnPriceRepository;
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
    private final AnswerRepository answerRepository;
    private final InfluenceOnPriceRepository influenceOnPriceRepository;

    @Autowired
    public InfluenceOnPriceServiceImpl(
            InfluenceOnPriceRepository influenceOnInvoiceRepository,
            InfluenceOnPriceMapper mapper,
            AnswerRepository answerRepository){
        super(influenceOnInvoiceRepository, mapper);
        this.answerRepository = answerRepository;
        this.influenceOnPriceRepository = influenceOnInvoiceRepository;
    }

  public Optional<InfluenceOnPriceResponseDto> create(final Long answerId,
      final InfluenceOnPriceRequestDto influenceOnPriceRequestDto) {
        InfluenceOnPrice influenceOnPrice = mapper.convertToEntity(influenceOnPriceRequestDto);
        return answerRepository.findById(answerId).map(response -> {
            response.getInfluenceOnPrice().add(influenceOnPrice);
          influenceOnPrice.setAnswer(response);
            answerRepository.save(response);
            return mapper.convertToDto(influenceOnPrice);  // id == null, because returned object not persisted yet
        });
    }

    @Override
    public List<InfluenceOnPriceResponseDto> findInfluencesByAnswerId(final Long answerId) {
        if (!answerRepository.existsById(answerId)){
          LOGGER.warn("There is no answer with id=" + answerId);
        }
        return influenceOnPriceRepository.findByAnswerId(answerId)
                .stream()
                .map(mapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<InfluenceOnPriceResponseDto> update(final Long answerId, final Long influenceId,
                                             final InfluenceOnPriceRequestDto influenceOnPrice) {
        if (!answerRepository.existsById(answerId)){
            LOGGER.warn("There is no answer with id=" + answerId);
        }
        return super.update(influenceId, influenceOnPrice);
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
