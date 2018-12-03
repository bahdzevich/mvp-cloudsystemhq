package com.cloudsystemhq.service.smsApi;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@JsonDeserialize(using = SmsListDeserializer.class)
public class SmsList extends BaseResponse {

  private List<Message> messages;

  public SmsList(List<Message> messages, String status, Integer error, String message) {
    super(status, error, message);
    this.messages = messages;
  }

  @Getter
  @Setter
  @ToString
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Message {

    private int id;
    private Integer bulk_id;
    private String sender;
    private String recipient;
    private String text;
    private String send_datetime;
    private String confirm_datetime;
    private String status;
    private String cost;
    private int parts;
  }
}
