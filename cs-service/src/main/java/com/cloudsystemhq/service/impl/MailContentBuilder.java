package com.cloudsystemhq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailContentBuilder {

  private TemplateEngine templateEngine;

  @Autowired
  public MailContentBuilder(TemplateEngine templateEngine) {
    this.templateEngine = templateEngine;
  }

  public String buildWelcomeMessage(String username) {
    Context context = new Context();
    context.setVariable("username", username);
    return templateEngine.process("welcome", context);
  }

//  public String buildInvoiceMessage(String message) {
//    Context context = new Context();
//    context.setVariable("message", message);
//    return templateEngine.process("welcome", context);
//  }

}
