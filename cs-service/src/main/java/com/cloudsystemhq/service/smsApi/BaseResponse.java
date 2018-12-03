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
public class BaseResponse {

  private String status;
  private Integer error;
  private String message;
}
