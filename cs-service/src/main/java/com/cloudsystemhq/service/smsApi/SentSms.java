package com.cloudsystemhq.service.smsApi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class SentSms extends BaseResponse {

  public SentSms(String status, Integer error, String message) {
    super(status, error, message);
  }

  private String[] messagesId;
  private int count;
  private int parts;
  private float amount;
  private float balance;
  private boolean test;
  private boolean urgent;
  private String[] customId;
}