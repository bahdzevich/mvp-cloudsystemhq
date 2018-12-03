package com.cloudsystemhq.service.smsApi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ServiceConfiguration {

  @Bean
  public RestTemplate getRestTemplate() {
//    BufferingClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()); // for debugging purposes
    HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
    factory.setConnectTimeout(5000);
    RestTemplate restTemplate = new RestTemplate(factory);
//    restTemplate
//        .setInterceptors(Collections.singletonList(new RequestResponseLoggingInterceptor())); // for debugging purposes
    return restTemplate;
  }
}
