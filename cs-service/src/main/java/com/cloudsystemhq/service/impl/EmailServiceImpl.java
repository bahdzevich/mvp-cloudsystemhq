package com.cloudsystemhq.service.impl;

import com.cloudsystemhq.service.IEmailService;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements IEmailService {

  private final MailContentBuilder mailContentBuilder;

  @Value("${email.from}")
  private String emailFrom;

  @Value("${email.apikey}")
  private String apikey;

  @Autowired
  public EmailServiceImpl(MailContentBuilder mailContentBuilder) {
    this.mailContentBuilder = mailContentBuilder;
  }

  private void sendMessage(String to, String subject, String htmlBody) {
    Email from = new Email(emailFrom);
    Email mailTo = new Email(to);
    Content content = new Content("text/html", htmlBody);
    Mail mail = new Mail(from, subject, mailTo, content);
    SendGrid sg = new SendGrid(apikey);
    Request request = new Request();
    try {
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());
      Response response = sg.api(request);
//      System.out.println(response.getStatusCode());
//      System.out.println(response.getBody());
//      System.out.println(response.getHeaders());
    } catch (IOException ex) {
      System.err.println(ex);
    }
  }

  @Override
  public void sendWelcomeMessage(String to, String subject, String username) {
    sendMessage(to, subject, mailContentBuilder.buildWelcomeMessage(username));
  }
}
