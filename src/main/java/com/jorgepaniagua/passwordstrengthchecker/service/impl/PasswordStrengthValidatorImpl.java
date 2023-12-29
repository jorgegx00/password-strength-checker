package com.jorgepaniagua.passwordstrengthchecker.service.impl;

import com.jorgepaniagua.passwordstrengthchecker.model.enums.PasswordStrengthResult;
import com.jorgepaniagua.passwordstrengthchecker.service.PasswordStrengthValidator;
import org.passay.*;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.Map;

import static org.passay.EnglishCharacterData.*;

@Service
public class PasswordStrengthValidatorImpl implements PasswordStrengthValidator {
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MAX_PASSWORD_LENGTH = 64;

    private final Map<PasswordStrengthResult,PasswordValidator> validatorMap;

    public PasswordStrengthValidatorImpl() {
        this.validatorMap = new EnumMap<>(PasswordStrengthResult.class);
        validatorMap.put(PasswordStrengthResult.NO_UPPERCASE, new PasswordValidator(new CharacterRule(UpperCase, 1)));
        validatorMap.put(PasswordStrengthResult.NO_LOWERCASE, new PasswordValidator(new CharacterRule(LowerCase, 1)));
        validatorMap.put(PasswordStrengthResult.NO_DIGIT, new PasswordValidator(new CharacterRule(Digit, 1)));
        validatorMap.put(PasswordStrengthResult.NO_SPECIAL_CHARACTER, new PasswordValidator(new CharacterRule(Special, 1)));
    }

    /**
     * Validates the password strength.
     *
     * @param password the password to validate
     * @return the result of the password strength validation
     */
    @Override
    public PasswordStrengthResult validate(final String password) {
        if (password.length() < MIN_PASSWORD_LENGTH)
            return PasswordStrengthResult.TOO_SHORT;

        if (password.length() > MAX_PASSWORD_LENGTH)
            return PasswordStrengthResult.TOO_LONG;

        for (PasswordStrengthResult result : validatorMap.keySet()) {
            RuleResult ruleResult = validatorMap.get(result).validate(new PasswordData(password));
            if (!ruleResult.isValid()) {
                return result;
            }
        }
        return PasswordStrengthResult.VALID;
    }
}