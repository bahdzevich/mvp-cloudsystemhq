package com.cloudsystemhq.service;

public interface IEmailService {

  void sendWelcomeMessage(String to, String subject, String username);
}
