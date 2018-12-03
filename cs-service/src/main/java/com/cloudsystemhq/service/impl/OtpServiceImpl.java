package com.cloudsystemhq.service.impl;

import com.cloudsystemhq.service.IOtpService;
import com.cloudsystemhq.service.smsApi.SendOtpResponse;
import com.cloudsystemhq.service.smsApi.VerifyOtpResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class OtpServiceImpl implements IOtpService {

  @Value("${otpprovider.providerUri}")
  private String providerUri;
  @Value("${otpprovider.otpLifetime}")
  private Integer otpLifetime;
  @Value("${otpprovider.api.key}")
  private String apiKey;
  @Value("${otpprovider.api.sendOtpUri}")
  private String sendOtpUri;
  @Value("${otpprovider.api.verifyOtpUri}")
  private String verifyOtpUri;

  private final RestTemplate restTemplate;

  public OtpServiceImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public SendOtpResponse sendOtp(Long customerId, String customerPhone, String message) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
    JSONObject json = new JSONObject();
    try {
      json.put("phone", customerPhone);
      json.put("userid", customerId.toString());
      json.put("key", apiKey);
      json.put("message", message);
      json.put("lifetime", otpLifetime.toString());
    } catch (JSONException e) {
      e.printStackTrace();
    }
    HttpEntity<String> entity = new HttpEntity<>(json.toString(), headers);
    return restTemplate.postForObject(
        providerUri + sendOtpUri,
        entity,
        SendOtpResponse.class
    );
  }

  @Override
  public VerifyOtpResponse verifyOtp(Long customerId, Integer otp) {
    ResponseEntity<String> responseEntity = restTemplate.getForEntity(
        UriComponentsBuilder
            .fromHttpUrl(providerUri + verifyOtpUri)
            .queryParam("otp", otp)
            .queryParam("userid", customerId)
            .queryParam("key", apiKey)
            .build()
            .toUri(),
        String.class
    );
    try {
      JSONObject jsonObject = new JSONObject(responseEntity.getBody());
      return new VerifyOtpResponse(jsonObject.optBoolean("success"),
          jsonObject.optBoolean("isValidOtp"), null);
    } catch (JSONException e) {
      e.printStackTrace();
      return new VerifyOtpResponse(false, false, "JSON exception");
    }
  }
}