package com.cloudsystemhq.service;

import com.cloudsystemhq.model.dto.request.CustomerRequestDto;
import com.cloudsystemhq.model.dto.response.CustomerResponseDto;
import com.cloudsystemhq.service.smsApi.BaseResponse;
import com.cloudsystemhq.service.smsApi.SendOtpResponse;
import com.cloudsystemhq.service.smsApi.SentSms;
import com.cloudsystemhq.service.smsApi.VerifyOtpResponse;

public interface ICustomerService extends
    IBaseService<CustomerRequestDto, CustomerResponseDto, Long> {

  SentSms sendSms(Long id);

  BaseResponse confirmCustomer(Long customerId, String receivedCode);

  SendOtpResponse sendOtp(Long id);

  VerifyOtpResponse verifyOtp(Long id, Integer receivedOtp);
}
