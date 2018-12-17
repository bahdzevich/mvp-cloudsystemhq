package com.cloudsystemhq.model.dto.validation;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneConstraintValidator implements ConstraintValidator<PhoneConstraint, String> {

  @Override
  public boolean isValid(String phoneNumber,
      ConstraintValidatorContext constraintValidatorContext) {

    boolean isValid = false;
    // Use the libphonenumber library to validate Number
    PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
    Phonenumber.PhoneNumber numberProto;
    try {
      numberProto = phoneUtil.parse(phoneNumber, "US");
      if (phoneUtil.isValidNumber(numberProto)) {
        isValid = true;
      }
    } catch (NumberParseException e) {
      System.err.println("NumberParseException was thrown: " + e.toString() + "");
    }
    return isValid;
  }
}
