package com.example.cucumberDemo.service.exceptions;

import com.example.cucumberDemo.model.Email;

public class InvalidEmailException extends Throwable {
    public InvalidEmailException(Email email) {
        super(email.getValue() + " is not a valid e-mail address.");
    }
}
