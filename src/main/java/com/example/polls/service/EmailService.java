package com.example.polls.service;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

@Service
public class EmailService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	@Qualifier("gmailPropsSSL")
	Properties gmailProps;
	
	@Autowired
	SendGrid sg;
	
	public void sendVerificationCode(String fromEmailAddress, String toEmailAddress,
			String content, String subject) throws MessagingException {
		Session session = Session.getDefaultInstance(gmailProps, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(gmailProps.getProperty("username"),
						gmailProps.getProperty("password"));
			}
		});
		
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(fromEmailAddress));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmailAddress));
		message.setSubject(subject);
		message.setText(content);
		
		Transport.send(message);
	}
	
	public void sendVerificationCodeUsingSendGrid(String fromEmail, String toEmail,
			String subject, String contentText) throws IOException {
		Email from = new Email(fromEmail);
	    Email to = new Email(toEmail);
	    Content content = new Content("text/plain", contentText);
	    Mail mail = new Mail(from, subject, to, content);
	    
	    Request request = new Request();
	    
		request.setMethod(Method.POST);
		request.setEndpoint("mail/send");
		request.setBody(mail.build());
		Response response = sg.api(request);
		logger.info("Code: " + response.getStatusCode());
		logger.info("Body:" + response.getBody());
		logger.info("Headers: " + response.getHeaders());
	}
	
}
