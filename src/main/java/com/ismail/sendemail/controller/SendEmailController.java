package com.ismail.sendemail.controller;

import com.ismail.sendemail.dto.EmailDetails;
import com.ismail.sendemail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class SendEmailController {

    @Autowired
    private EmailService emailService;

    @Value("${from-email}")
    private String fromEmail;

    @Value("${to-email}")
    private String toEmail;

    @PostMapping("/email")
    public String sendMessage(@RequestParam String subject, @RequestParam String message, @RequestParam String name,
                              @RequestParam String replyto) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromEmail);
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(getBody(message, name, replyto));
        emailService.sendMessage(simpleMailMessage);

        return "Email sent successfully";
    }

    @PostMapping("/email1")
    public String sendMessage1(@RequestBody EmailDetails emailDetails) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromEmail);
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setSubject(emailDetails.getSubject());
        simpleMailMessage.setText(getBody(emailDetails.getMessage(), emailDetails.getName(), emailDetails.getReplyto()));
        emailService.sendMessage(simpleMailMessage);

        return "Email sent successfully";
    }

    public String getBody(String message, String name, String replyto) {
        return "ismailkouz.com Contact \n " +
                "name : " + name + "\n" +
                "from : " + replyto + "\n" +
                "message : " + message;
    }


}

