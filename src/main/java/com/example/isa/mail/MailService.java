package com.example.isa.mail;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.isa.mail.formatter.AccountActivationFormatter;
import com.example.isa.mail.formatter.BoatComplaintFormatter;
import com.example.isa.mail.formatter.BoatReservationConfirmationMailFormatter;
import com.example.isa.mail.formatter.MansionComplaintFormatter;
import com.example.isa.mail.formatter.MansionReservationConfirmationMailFormatter;
import com.example.isa.mail.formatter.OwnerComplaintMailFormatter;
import com.example.isa.model.AdvertiserComplaint;
import com.example.isa.model.BoatComplaint;
import com.example.isa.model.MansionComplaint;
import com.example.isa.model.User;
import com.example.isa.model.reservations.BoatReservation;
import com.example.isa.model.reservations.MansionReservation;

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
	    public void sendClientRegistrationMail(String recipient, String activationCode, AccountActivationFormatter mailFormatter) throws MessagingException {

	    	SimpleMailMessage message = new SimpleMailMessage();
	    	message.setFrom("adventurelandisa@gmail.com");
	    	message.setTo(recipient);
	    	message.setText(mailFormatter.getText(activationCode));
	    	message.setSubject(mailFormatter.getSubject());
	    	
	    	mailSender.send(message);
	    }
	    
	    @Async
	    public void sendBoatReservationConfirmationMail(BoatReservation reservation) throws MessagingException {
	    	 
	    	User user = reservation.getUser();
	    	BoatReservationConfirmationMailFormatter formater = new BoatReservationConfirmationMailFormatter();
	    	SimpleMailMessage message = new SimpleMailMessage();
	    	message.setFrom("adventurelandisa@gmail.com");
	    	message.setTo(user.getEmail());
	    	message.setText(formater.getText(reservation));
	    	message.setSubject(formater.getSubject(reservation.getBoat().getName()));
	    	
	    	mailSender.send(message);
	    }
	    @Async
	    public void sendMansionReservationConfirmationMail(MansionReservation reservation) throws MessagingException {
	    	
	    	User user = reservation.getUser();
	    	MansionReservationConfirmationMailFormatter formater = new MansionReservationConfirmationMailFormatter();
	    	SimpleMailMessage message = new SimpleMailMessage();
	    	message.setFrom("adventurelandisa@gmail.com");
	    	message.setTo(user.getEmail());
	    	message.setText(formater.getText(reservation));
	    	message.setSubject(formater.getSubject(reservation.getMansion().getName()));
	    	
	    	mailSender.send(message);
	    }

		public void sendMansionComplaintEmail(MansionComplaint c) {
			
	    	MansionComplaintFormatter formater = new MansionComplaintFormatter(c);
	    	SimpleMailMessage message = new SimpleMailMessage();
	    	message.setFrom("adventurelandisa@gmail.com");
	    	message.setTo(formater.getOwnerEmail());
	    	message.setText(formater.getText());
	    	message.setSubject(formater.getSubject());
	    	
	    	mailSender.send(message);
		}
		
		
		public void sendBoatComplaintEmail(BoatComplaint c) {
			
	    	BoatComplaintFormatter formater = new BoatComplaintFormatter(c);
	    	SimpleMailMessage message = new SimpleMailMessage();
	    	message.setFrom("adventurelandisa@gmail.com");
	    	message.setTo(formater.getOwnerEmail());
	    	message.setText(formater.getText());
	    	message.setSubject(formater.getSubject());
	    	
	    	mailSender.send(message);
			
		}
		
		public void sendAdvertiserComplaintEmail(AdvertiserComplaint c) {
			
	    	OwnerComplaintMailFormatter formater = new OwnerComplaintMailFormatter(c);
	    	SimpleMailMessage message = new SimpleMailMessage();
	    	message.setFrom("adventurelandisa@gmail.com");
	    	message.setTo(formater.getOwnerEmail());
	    	message.setText(formater.getText());
	    	message.setSubject(formater.getSubject());
	    	
	    	mailSender.send(message);
			
		}
}
