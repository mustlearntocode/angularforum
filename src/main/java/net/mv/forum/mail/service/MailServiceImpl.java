package net.mv.forum.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService{
	
	@Autowired
	private MailSender mailSender;

	@Override
	public void sendMail(String to, String message, String subject) {

		SimpleMailMessage smm = new SimpleMailMessage();
		
		smm.setFrom("angularforumnoreply@gmail.com");
		smm.setTo(to);
		smm.setSubject(subject);
		smm.setText(message);
		
		mailSender.send(smm);
		
	}

}
