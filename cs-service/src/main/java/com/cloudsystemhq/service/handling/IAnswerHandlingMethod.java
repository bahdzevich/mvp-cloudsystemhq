package com.cloudsystemhq.service.handling;

import com.cloudsystemhq.model.domain.Answer;
import java.util.concurrent.Callable;
import java.util.function.Function;

public interface IAnswerHandlingMethod<V> extends Function<Answer, Callable<V>> {
  //ToDo: choose another contract.
}
