package com.cloudsystemhq.service.util.mapping;

import com.cloudsystemhq.model.domain.InfluenceOnPrice;
import com.cloudsystemhq.model.dto.request.InfluenceOnPriceRequestDto;
import com.cloudsystemhq.model.dto.response.InfluenceOnPriceResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InfluenceOnPriceMapper
        extends EntityMapper<InfluenceOnPrice, InfluenceOnPriceRequestDto, InfluenceOnPriceResponseDto>{
}
