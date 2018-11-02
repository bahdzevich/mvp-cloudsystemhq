package com.cloudsystemhq.service;

import com.cloudsystemhq.model.dto.request.CustomerRequestDto;
import com.cloudsystemhq.model.dto.response.CustomerResponseDto;

public interface ICustomerService extends
    IBaseService<CustomerRequestDto, CustomerResponseDto, Long> {

}
