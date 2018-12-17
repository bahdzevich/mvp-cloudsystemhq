package com.cloudsystemhq.model.dto.validation;

import java.util.Arrays;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.passay.AlphabeticalSequenceRule;
import org.passay.DigitCharacterRule;
import org.passay.LengthRule;
import org.passay.NumericalSequenceRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.QwertySequenceRule;
import org.passay.RuleResult;
import org.passay.SpecialCharacterRule;
import org.passay.UppercaseCharacterRule;
import org.passay.WhitespaceRule;

public class PasswordConstraintValidator implements
    ConstraintValidator<PasswordConstraint, String> {

  @Override
  public boolean isValid(String password, ConstraintValidatorContext context) {

    PasswordValidator validator = new PasswordValidator(Arrays.asList(
        new LengthRule(8, 30),
        new UppercaseCharacterRule(1),
        new DigitCharacterRule(1),
        new SpecialCharacterRule(1),
        new NumericalSequenceRule(3, false),
        new AlphabeticalSequenceRule(3, false),
        new QwertySequenceRule(3, false),
        new WhitespaceRule()));

    RuleResult result = validator.validate(new PasswordData(password));
    if (result.isValid()) {
      return true;
    }
    context.disableDefaultConstraintViolation();
    context.buildConstraintViolationWithTemplate(
        validator.getMessages(result)
            .stream()
            .reduce((x, y) -> x + " " + y)
            .orElse(""))
        .addConstraintViolation();
    return false;

//    if (Strings.isBlank(password)){
//      return false;
//    }
//    final Pattern hasUppercase = Pattern.compile("[A-Z]");
//    final Pattern hasLowercase = Pattern.compile("[a-z]");
//    final Pattern hasNumber = Pattern.compile("\\d");
//    //final Pattern hasSpecialChar = Pattern.compile("[^a-zA-Z0-9 ]");
//    //String pattern = "\A(?=\S*?[0-9])(?=\S*?[a-z])(?=\S*?[A-Z])(?=\S*?[@#$%^&+=])\S{8,}\z";
//    return hasUppercase.matcher(password).find() &&
//        hasLowercase.matcher(password).find() &&
//        hasNumber.matcher(password).find() &&
//        password.length() >= 8;
  }
}
