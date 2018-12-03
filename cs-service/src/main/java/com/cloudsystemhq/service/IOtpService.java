package com.cloudsystemhq.service;

import com.cloudsystemhq.service.smsApi.SendOtpResponse;
import com.cloudsystemhq.service.smsApi.VerifyOtpResponse;

public interface IOtpService {

  SendOtpResponse sendOtp(Long customerId, String customerPhone, String message);

  VerifyOtpResponse verifyOtp(Long customerId, Integer otp);
}
