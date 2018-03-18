package com.example.cucumberDemo.service;

import com.example.cucumberDemo.model.Email;
import com.example.cucumberDemo.service.exceptions.InvalidEmailException;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

public class EmailServiceTest {
    private static final Email EMAIL_1 = new Email("niek.bergans@juvo.be");
    private static final Email EMAIL_2 = new Email("test@gmail.com");
    private static final Email INVALID_EMAIL = new Email("not a valid email address");

    @Test
    public void add_1() throws Exception, InvalidEmailException {
        final EmailService emailService = new EmailService();
        emailService.add(EMAIL_1);

        List<Email> result = emailService.getAll();
        assertThat(result, hasSize(1));
        assertThat(result.get(0), equalTo(EMAIL_1));
    }

    @Test
    public void add_2() throws Exception, InvalidEmailException {
        final EmailService emailService = new EmailService();
        emailService.add(EMAIL_1);
        emailService.add(EMAIL_2);

        List<Email> result = emailService.getAll();
        assertThat(result, hasSize(2));
    }

    @Test
    public void add_invalid() throws Exception, InvalidEmailException {
        final EmailService emailService = new EmailService();
        try {
            emailService.add(INVALID_EMAIL);
        } catch (InvalidEmailException e) {
            assertThat(e.getMessage(), equalTo(INVALID_EMAIL.getValue() + " is not a valid e-mail address."));
        }
    }

}