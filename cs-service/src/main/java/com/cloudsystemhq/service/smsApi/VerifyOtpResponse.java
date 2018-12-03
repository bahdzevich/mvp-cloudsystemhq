package com.cloudsystemhq.service.smsApi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VerifyOtpResponse {

  private boolean success;
  private boolean validOtp;
  private String message;
}