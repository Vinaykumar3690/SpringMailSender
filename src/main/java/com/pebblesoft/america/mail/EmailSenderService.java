package com.pebblesoft.america.mail;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.File;

@Service
public class EmailSenderService {
	
	private static final Logger logger = LogManager.getLogger(EmailSenderService.class);


    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String toEmail,
                                String body,
                                String subject) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("vinaykumarg3690@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);
        System.out.println("Mail Send...");
    }

    public void sendEmailWithAttachment(String toEmail,
                                        String body,
                                        String subject,
                                        String attachment)  {

    	
    	logger.debug("Enter main method");
        try {
        	
        	
        	
			MimeMessage mimeMessage = mailSender.createMimeMessage();

			MimeMessageHelper mimeMessageHelper
			        = new MimeMessageHelper(mimeMessage, true);

			mimeMessageHelper.setFrom("vinaykumarg3690@gmail.com");
			mimeMessageHelper.setTo(toEmail);
			mimeMessageHelper.setText(body);
			mimeMessageHelper.setSubject(subject);

			FileSystemResource fileSystem
			        = new FileSystemResource(new File(attachment));

			mimeMessageHelper.addAttachment(fileSystem.getFilename(),
			        fileSystem);

			mailSender.send(mimeMessage);
			System.out.println("Mail Send...");
		} catch (MailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug("Exit main method");

    }
}