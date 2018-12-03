package com.cloudsystemhq.service.impl;

import com.cloudsystemhq.service.ISmsProviderService;
import com.cloudsystemhq.service.smsApi.SentSms;
import com.cloudsystemhq.service.smsApi.SmsAccount;
import com.cloudsystemhq.service.smsApi.SmsList;
import com.cloudsystemhq.service.smsApi.SmsListDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class SmsProviderServiceImpl implements ISmsProviderService {

  @Value("${smsprovider.providerUri}")
  private String providerUri;
  @Value("${smsprovider.user}")
  private String user;
  @Value("${smsprovider.api.key}")
  private String apiKey;
  @Value("${smsprovider.api.accountInfoUri}")
  private String accountInfoUri;
  @Value("${smsprovider.api.msgSendUri}")
  private String msgSendUri;
  @Value("${smsprovider.senderName}")
  private String senderName;
  @Value("${smsprovider.api.msgListUri}")
  private String msgListUri;

  private final RestTemplate restTemplate;

  public SmsProviderServiceImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public SmsAccount getAccountInfo() {
    return restTemplate.getForObject(
        UriComponentsBuilder
            .fromHttpUrl(providerUri)
            .queryParam("r", accountInfoUri)
            .queryParam("user", user)
            .queryParam("apikey", apiKey)
            .toUriString(),
        SmsAccount.class
    );
  }

  @Override
  public SentSms sendSmsToCustomer(String customerNumber, String message) {
    // ToDO: format customerNumber string
    return restTemplate.getForObject(
        UriComponentsBuilder
            .fromHttpUrl(providerUri)
            .queryParam("r", msgSendUri)
            .queryParam("user", user)
            .queryParam("apikey", apiKey)
            .queryParam("sender", senderName)
            .queryParam("recipients", customerNumber)
            .queryParam("message", message)
            .toUriString(),
        SentSms.class
    );
  }

  @Override
  public SmsList getRecentCustomersSms(String customerNumber) {
    // ToDO: format customerNumber string
    String smsDateFrom = LocalDateTime.now()
        .minusMinutes(15)
        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    HttpHeaders headers = new HttpHeaders();
    headers.set("Content-Type", "application/x-www-form-urlencoded");
    HttpEntity<String> entity = new HttpEntity<>(headers);
    String json = restTemplate.postForObject(
        UriComponentsBuilder
            .fromHttpUrl(providerUri)
            .queryParam("r", msgListUri)
            .queryParam("user", user)
            .queryParam("apikey", apiKey)
            .queryParam("date_from", smsDateFrom)
            .queryParam("recipients", customerNumber)
//            .queryParam("status", "delivered")
            .build()
            .toString(),
        entity,
        String.class
    );
    ObjectMapper mapper = new ObjectMapper();
    SimpleModule module = new SimpleModule();
    module.addDeserializer(SmsList.class, new SmsListDeserializer());
    mapper.registerModule(module);
    SmsList smsList = null;
    try {
      smsList = mapper.readValue(json, SmsList.class);
    } catch (IOException e) {
      System.err.println("SmsList deserialization exception:");
      e.printStackTrace();
    }
    return smsList;
  }
}