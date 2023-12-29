package com.jorgepaniagua.passwordstrengthchecker.rest;

import com.jorgepaniagua.passwordstrengthchecker.model.enums.PasswordStrengthResult;
import com.jorgepaniagua.passwordstrengthchecker.service.PasswordStrengthValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class PasswordStrengthControllerTest {

    @Autowired
    private PasswordStrengthController passwordStrengthController;

    @MockBean
    private PasswordStrengthValidator passwordStrengthValidator;

    @BeforeEach
    void setUp() {
        when(passwordStrengthValidator.validate(anyString())).thenReturn(PasswordStrengthResult.VALID);
    }

    @Test
    @DisplayName("Should return VALID when password meets all requirements")
    void validateValidPassword() {
        PasswordStrengthResult result = passwordStrengthController.checkStrength("ValidPassword1!");
        assertEquals(PasswordStrengthResult.VALID, result);
    }

    @Test
    @DisplayName("Should return TOO_SHORT when password is less than 8 characters")
    void validateShortPassword() {
        when(passwordStrengthValidator.validate(anyString())).thenReturn(PasswordStrengthResult.TOO_SHORT);
        PasswordStrengthResult result = passwordStrengthController.checkStrength("Short1!");
        assertEquals(PasswordStrengthResult.TOO_SHORT, result);
    }

    @Test
    @DisplayName("Should return TOO_LONG when password is more than 64 characters")
    void validateLongPassword() {
        String longPassword = "A".repeat(65);
        when(passwordStrengthValidator.validate(anyString())).thenReturn(PasswordStrengthResult.TOO_LONG);
        PasswordStrengthResult result = passwordStrengthController.checkStrength(longPassword);
        assertEquals(PasswordStrengthResult.TOO_LONG, result);
    }

    @Test
    @DisplayName("Should return NO_UPPERCASE when password has no uppercase letters")
    void validateNoUppercasePassword() {
        when(passwordStrengthValidator.validate(anyString())).thenReturn(PasswordStrengthResult.NO_UPPERCASE);
        PasswordStrengthResult result = passwordStrengthController.checkStrength("validpassword1!");
        assertEquals(PasswordStrengthResult.NO_UPPERCASE, result);
    }

    @Test
    @DisplayName("Should return NO_LOWERCASE when password has no lowercase letters")
    void validateNoLowercasePassword() {
        when(passwordStrengthValidator.validate(anyString())).thenReturn(PasswordStrengthResult.NO_LOWERCASE);
        PasswordStrengthResult result = passwordStrengthController.checkStrength("VALIDPASSWORD1!");
        assertEquals(PasswordStrengthResult.NO_LOWERCASE, result);
    }

    @Test
    @DisplayName("Should return NO_DIGIT when password has no digits")
    void validateNoDigitPassword() {
        when(passwordStrengthValidator.validate(anyString())).thenReturn(PasswordStrengthResult.NO_DIGIT);
        PasswordStrengthResult result = passwordStrengthController.checkStrength("ValidPassword!");
        assertEquals(PasswordStrengthResult.NO_DIGIT, result);
    }

    @Test
    @DisplayName("Should return NO_SPECIAL_CHARACTER when password has no special characters")
    void validateNoSpecialCharacterPassword() {
        when(passwordStrengthValidator.validate(anyString())).thenReturn(PasswordStrengthResult.NO_SPECIAL_CHARACTER);
        PasswordStrengthResult result = passwordStrengthController.checkStrength("ValidPassword1");
        assertEquals(PasswordStrengthResult.NO_SPECIAL_CHARACTER, result);
    }
}