package com.Main.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class SendEmail {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String from;
	
	public void sendEmail(String recipent,String subject,String body) {
		SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
		simpleMailMessage.setFrom(from);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setTo(recipent);
		simpleMailMessage.setText(body);
		
		javaMailSender.send(simpleMailMessage);
	}
	
}
