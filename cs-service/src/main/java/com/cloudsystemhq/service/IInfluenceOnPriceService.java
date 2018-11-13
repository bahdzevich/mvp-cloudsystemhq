package com.cloudsystemhq.service;

import com.cloudsystemhq.model.dto.request.InfluenceOnPriceRequestDto;
import com.cloudsystemhq.model.dto.response.InfluenceOnPriceResponseDto;

import java.util.List;
import java.util.Optional;


public interface IInfluenceOnPriceService extends
        IBaseService<InfluenceOnPriceRequestDto, InfluenceOnPriceResponseDto, Long> {
    Optional<InfluenceOnPriceResponseDto> create(Long responseId,
                                                 InfluenceOnPriceRequestDto influenceOnPriceRequestDto);
    List<InfluenceOnPriceResponseDto> findInfluencesByResponseId(Long responseId);
}
