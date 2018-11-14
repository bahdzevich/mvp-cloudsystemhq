package com.cloudsystemhq.service.handling.impl;

import com.cloudsystemhq.model.domain.Answer;
import com.cloudsystemhq.service.handling.IAnswerHandlingMethod;
import java.math.BigDecimal;
import java.util.concurrent.Callable;

public class DefaultHandlingMethod implements IAnswerHandlingMethod<BigDecimal> {

  @Override
  public Callable<BigDecimal> apply(Answer answer) {
    return null;
  }
}
