package com.company.controller;

import com.company.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestParam String email,
                                            @RequestParam String subject,
                                            @RequestParam String message) {
        emailService.sendEmail(email, subject, message);
        return new ResponseEntity<>("Email sent successfully", HttpStatus.OK);
    }
}