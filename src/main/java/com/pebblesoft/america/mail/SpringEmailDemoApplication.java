package com.pebblesoft.america.mail;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;

@SpringBootApplication
public class SpringEmailDemoApplication {

	private static final Logger logger = LogManager.getLogger(SpringEmailDemoApplication.class);

	@Autowired
	private EmailSenderService senderService;

	@Autowired
	private EmailSenderService service;

	public static void main(String[] args) {

		logger.debug("Enter main method");

		SpringApplication.run(SpringEmailDemoApplication.class, args);

		logger.debug("Exit main method");

	}

	@EventListener(ApplicationReadyEvent.class)
	public void triggerMail() {

		logger.debug("Enter triggerMail()");

		try {

			service.sendEmailWithAttachment("niyajmohd@gmail.com", "This is Email Body with Attachment...",
					"This email from pebbleSoft America", "C:\\Users\\GVK Yadav\\Downloads\\pds.jpg");

		} catch (MessagingException e) {

			// TODO Auto-generated catch block
			logger.error("Exception Occurred" + e.getMessage(), e);
		}

		logger.debug("Exit triggerMail()");
	}

}
