package com.example.cucumberDemo.interfaces.rest;

import com.example.cucumberDemo.model.Email;
import com.example.cucumberDemo.service.EmailService;
import com.example.cucumberDemo.service.exceptions.InvalidEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/email")
public class EmailController {
    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{value}")
    public ResponseEntity<Email> create(@PathVariable String value) {
        final Email email = new Email(value);
        try {
            emailService.add(email);
        } catch (InvalidEmailException e) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(email);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Email>> get() {
        return ResponseEntity.ok(emailService.getAll());
    }
}
