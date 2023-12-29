package com.jorgepaniagua.passwordstrengthchecker.service.impl;

import com.jorgepaniagua.passwordstrengthchecker.model.enums.PasswordStrengthResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PasswordStrengthValidatorImplTest {

    private PasswordStrengthValidatorImpl passwordStrengthValidator;

    @BeforeEach
    void setUp() {
        passwordStrengthValidator = new PasswordStrengthValidatorImpl();
    }

    @Test
    @DisplayName("Should return VALID when password meets all requirements")
    void validateValidPassword() {
        PasswordStrengthResult result = passwordStrengthValidator.validate("ValidPassword1!");
        assertEquals(PasswordStrengthResult.VALID, result);
    }

    @Test
    @DisplayName("Should return TOO_SHORT when password is less than 8 characters")
    void validateShortPassword() {
        PasswordStrengthResult result = passwordStrengthValidator.validate("Short1!");
        assertEquals(PasswordStrengthResult.TOO_SHORT, result);
    }

    @Test
    @DisplayName("Should return TOO_LONG when password is more than 64 characters")
    void validateLongPassword() {
        String longPassword = "A".repeat(65);
        PasswordStrengthResult result = passwordStrengthValidator.validate(longPassword);
        assertEquals(PasswordStrengthResult.TOO_LONG, result);
    }

    @Test
    @DisplayName("Should return NO_UPPERCASE when password has no uppercase letters")
    void validateNoUppercasePassword() {
        PasswordStrengthResult result = passwordStrengthValidator.validate("validpassword1!");
        assertEquals(PasswordStrengthResult.NO_UPPERCASE, result);
    }

    @Test
    @DisplayName("Should return NO_LOWERCASE when password has no lowercase letters")
    void validateNoLowercasePassword() {
        PasswordStrengthResult result = passwordStrengthValidator.validate("VALIDPASSWORD1!");
        assertEquals(PasswordStrengthResult.NO_LOWERCASE, result);
    }

    @Test
    @DisplayName("Should return NO_DIGIT when password has no digits")
    void validateNoDigitPassword() {
        PasswordStrengthResult result = passwordStrengthValidator.validate("ValidPassword!");
        assertEquals(PasswordStrengthResult.NO_DIGIT, result);
    }

    @Test
    @DisplayName("Should return NO_SPECIAL_CHARACTER when password has no special characters")
    void validateNoSpecialCharacterPassword() {
        PasswordStrengthResult result = passwordStrengthValidator.validate("ValidPassword1");
        assertEquals(PasswordStrengthResult.NO_SPECIAL_CHARACTER, result);
    }
}