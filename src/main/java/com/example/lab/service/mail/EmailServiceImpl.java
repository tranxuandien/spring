package com.example.lab.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl {

	@Autowired
	private JavaMailSender emailSender;

	public void sendSimpleMessage(String to, String subject, String text, String uri) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("noreply@test.com");
		message.setTo(to);
		message.setSubject("Laboratory register user: "+subject);
		message.setText("Click link to confirm create an account with username:"+subject+"\n Link(valid 24h) : "+uri+"account/register/active?token="+text );
		emailSender.send(message);
	}
}