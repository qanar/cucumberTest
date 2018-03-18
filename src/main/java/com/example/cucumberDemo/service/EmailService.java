package com.example.cucumberDemo.service;

import com.example.cucumberDemo.model.Email;
import com.example.cucumberDemo.service.exceptions.InvalidEmailException;
import org.springframework.stereotype.Service;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EmailService {
    private List<Email> emails = new ArrayList<>();
    private EmailValidator emailValidator = EmailValidator.getInstance();

    public List<Email> getAll() {
        return Collections.unmodifiableList(emails);
    }

    public void add(Email email) throws InvalidEmailException {
        if(!emailValidator.isValid(email.getValue())) {
            throw new InvalidEmailException(email);
        }
        emails.add(email);
    }

    public void removeAll() {
        emails = new ArrayList<>();
    }
}
