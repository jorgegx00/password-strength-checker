package com.jorgepaniagua.passwordstrengthchecker.rest;

import com.jorgepaniagua.passwordstrengthchecker.model.enums.PasswordStrengthResult;
import com.jorgepaniagua.passwordstrengthchecker.service.PasswordStrengthValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PasswordStrengthController {
    private final PasswordStrengthValidator passwordStrengthValidator;

    @Autowired
    public PasswordStrengthController(PasswordStrengthValidator passwordStrengthValidator) {
        this.passwordStrengthValidator = passwordStrengthValidator;
    }

    @GetMapping("/checkStrength")
    public PasswordStrengthResult checkStrength(@RequestParam String password) {
        return passwordStrengthValidator.validate(password);
    }
}
