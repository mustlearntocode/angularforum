package net.mv.forum.mail.service;

public interface MailService {
	
	public void sendMail(String to, String message, String subject);

}
