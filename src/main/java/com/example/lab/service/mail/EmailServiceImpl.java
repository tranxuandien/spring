package com.example.lab.service.mail;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl {

	@Autowired
	private JavaMailSender emailSender;

	public void sendRegisterEmail(String to, String subject, String text, String uri) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("noreply@test.com");
		message.setTo(to);
		message.setSubject("Laboratory register user: "+subject);
		message.setText("Click link to confirm create an account with username:"+subject+"\n Link(valid 24h) : "+uri+"account/register/active?token="+text );
		emailSender.send(message);
	}

	public void sendChemicalStatusAlertEmail(String to,String cc, String chemical, String user,BigDecimal remain) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("noreply@test.com");
		message.setTo(to);
		message.setCc(cc);
		message.setSubject("[LAB][Sử dụng hóa chất]["+chemical+"]");
		message.setText("Hóa chất "+chemical+" mới được sử dụng bới người dùng "+user+" \n "
				+ " Trạng thái hóa chất : Hết/Sắp hết \n "
				+ " Lượng hóa chất còn lại: "+remain+" (g/ml)\n"
				+ " Xin kiểm tra lại thông tin hóa chất trên hệ thống! \n\n "
				+ "Lab develop team" );
		emailSender.send(message);
	}
}