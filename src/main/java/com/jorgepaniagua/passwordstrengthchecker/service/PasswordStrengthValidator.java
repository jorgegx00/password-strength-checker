package com.jorgepaniagua.passwordstrengthchecker.service;

import com.jorgepaniagua.passwordstrengthchecker.model.enums.PasswordStrengthResult;

public interface PasswordStrengthValidator {
    PasswordStrengthResult validate(String password);
}
