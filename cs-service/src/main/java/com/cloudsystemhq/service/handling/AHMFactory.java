package com.cloudsystemhq.service.handling;

import com.cloudsystemhq.model.domain.AnswerHandlingType;
import com.cloudsystemhq.service.handling.impl.DefaultHandlingMethod;
import java.util.EnumMap;
import java.util.Optional;

/**
 * Need a single executor service per invoice section: it makes price computing for one section
 * independent from another. In this instance of execution service need to be passed to {@code
 * IAnswerHandlingMethod} instance.
 */
public class AHMFactory {

  private final EnumMap<AnswerHandlingType, IAnswerHandlingMethod> handlingMethodEnumMap = new EnumMap<>(
      AnswerHandlingType.class);

  {
    handlingMethodEnumMap.put(AnswerHandlingType.DEFAULT, new DefaultHandlingMethod());
  }

  /**
   * Get {@link IAnswerHandlingMethod} provided by {@link AnswerHandlingType} in {@code
   * handlingMethodEnumMap}.
   *
   * @param type {@code AnswerHandlingType} instance;
   * @return {@code Optional<IAnswerHandlingMethod>}.
   */
  public Optional<IAnswerHandlingMethod> getHandlingMethod(AnswerHandlingType type) {
    return Optional.of(handlingMethodEnumMap.get(type));
  }

  public static AHMFactory getInstance() {
    return FactoryStaticHolder.FACTORY_INSTANCE;
  }

  private static class FactoryStaticHolder {

    private static final AHMFactory FACTORY_INSTANCE = new AHMFactory();
  }
}
