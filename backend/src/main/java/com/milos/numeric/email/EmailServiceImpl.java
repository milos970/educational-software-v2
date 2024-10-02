package com.milos.numeric.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class EmailServiceImpl
{
    private final JavaMailSender emailSender;
    private final static String SENDER_NAME = "Numerika";

    @Value("${spring.mail.username}")
    private String sourceEmail;


    private String serverIp;


    private String serverPort;



    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }


    public void sendPassword(String email, String password) throws MessagingException, UnsupportedEncodingException
    {
        String content = "Vaše nové vygenerované heslo: " + password + ". Po prihlásení si ho prosím zmeňte!";
        this.sendEmail(email,"Obnovenie hesla",content);
    }

   


    private void sendEmail(String destinationEmail, String subject, String content) throws MessagingException, UnsupportedEncodingException
    {
        MimeMessage message = this.emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(sourceEmail, subject);
        helper.setTo(destinationEmail);
        helper.setSubject(subject);
        helper.setText(content, true);
        this.emailSender.send(message);
    }



}
