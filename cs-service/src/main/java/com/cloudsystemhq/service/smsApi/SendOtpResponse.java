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
public class SendOtpResponse {

  private boolean success;
  private String textId;
  private Integer quotaRemaining;
  private Integer otp;
  private String message;
}
