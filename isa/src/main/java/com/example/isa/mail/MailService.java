package com.example.isa.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class MailService<T> {
	 private Environment env;
	    private JavaMailSender mailSender;

	    @Autowired
	    public MailService(Environment env, JavaMailSender mailSender) {
	        this.env = env;
	        this.mailSender = mailSender;
	    }

	    @Async
	    public void sendMail(String recipient, String activationCode, AccountActivationFormatter mailFormatter) throws MessagingException {

	    	SimpleMailMessage message = new SimpleMailMessage();
	    	message.setFrom("littlevamp999@gmail.com");
	    	message.setTo(recipient);
	    	message.setText(mailFormatter.getText(activationCode));
	    	message.setSubject(mailFormatter.getSubject());
	    	
	    	mailSender.send(message);
	    }
}
