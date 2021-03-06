package com.example.isa.mail;

import javax.mail.MessagingException;
import javax.management.Notification;

import com.example.isa.mail.formatter.*;
import com.example.isa.model.*;
import com.example.isa.model.reservations.DiscountReservation;
import com.example.isa.model.reservations.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.isa.model.reservations.BoatReservation;
import com.example.isa.model.reservations.MansionReservation;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Locale;

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
    public void notifyClientAboutCreatedReservation(Reservation newReservation) {
			NotificationAboutCreatedReservationFormatter formater = new NotificationAboutCreatedReservationFormatter();
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("adventurelandisa@gmail.com");
			message.setTo("s.andrea1723@gmail.com");
			message.setText(formater.getText( newReservation));
			message.setSubject(formater.getSubject(newReservation));

			mailSender.send(message);
    }

    @Async
		public void sendNotificationAboutDiscountReservation(Client client, DiscountReservation discountReservation){
			NotificationAboutDiscountReservationIsCreatedFotmatter formater = new NotificationAboutDiscountReservationIsCreatedFotmatter();
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("adventurelandisa@gmail.com");
			message.setTo("s.andrea1723@gmail.com"); //client.mail
			message.setText(formater.getText( discountReservation, client));
			message.setSubject(formater.getSubject(discountReservation));

			mailSender.send(message);
		}

	@Async
	public void sendNotificationAboutDiscountReservation1(Client client, DiscountReservation discountReservation){
		NotificationAboutDiscountReservationIsCreatedFotmatter formater = new NotificationAboutDiscountReservationIsCreatedFotmatter();
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("adventurelandisa@gmail.com");
		message.setTo("alexmirkovic1719@gmail.com"); //client.mail
		message.setText(formater.getText( discountReservation, client));
		message.setSubject(formater.getSubject(discountReservation));
		mailSender.send(message);
	}

	@Async
	public void sendNotificationAbaoutUnapprovedRegistration(User user,String report){
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("adventurelandisa@gmail.com");
		message.setTo("alexmirkovic1719@gmail.com"); //client.mail
		String content="";
		Format formatter = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
		content+= "Hi "+user.getName()+",\n"+"\n";
		content+="Vasa registracija je odbijena!\n";
		content+="Razlog: "+report;
		message.setText(content);
		message.setSubject("ODBIJENA REGISTRACIJA");
		mailSender.send(message);
	}

	@Async
	public void sendNotificationAbaoutApprovedRegistration(User user,String report){
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("adventurelandisa@gmail.com");
		message.setTo("alexmirkovic1719@gmail.com"); //client.mail
		String content="";
		Format formatter = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
		content+= "Hi "+user.getName()+",\n"+"\n";
		content+="Vasa registracija je prihvacena!\n";
		message.setText(content);
		message.setSubject("REGISTRACIJA");
		mailSender.send(message);
	}

	@Async
	public void sendNotificationAbaoutUndeletedAccount(User user,String report){
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("adventurelandisa@gmail.com");
		message.setTo("alexmirkovic1719@gmail.com"); //client.mail
		String content="";
		Format formatter = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
		content+= "Hi "+user.getName()+",\n"+"\n";
		content+="Vas zahtev za brisanje naloga  je odbijena!\n";
		content+="Razlog: "+report;
		message.setText(content);
		message.setSubject("ODBIJENO BRISANJE PROFILA");
		mailSender.send(message);
	}
	@Async
	public void sendNotificationAbaoutDeletedAccount(User user){
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("adventurelandisa@gmail.com");
		message.setTo("alexmirkovic1719@gmail.com"); //client.mail
		String content="";
		Format formatter = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
		content+= "Hi "+user.getName()+",\n"+"\n";
		content+="Vas zahtev za brisanje naloga  je prihvacen!\n";
		message.setText(content);
		message.setSubject("PRIHVACENO BRISANJE PROFILA");
		mailSender.send(message);
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
	@Async
	public void sendNotificationAboutCommonClient(User user){
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("adventurelandisa@gmail.com");
		message.setTo("alexmirkovic1719@gmail.com"); //client.mail
		String content="";
		Format formatter = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
		content+= "Hi "+user.getName()+",\n"+"\n";
		content+="Na zalost vlasnik je zatrazio sankciju! Dobili ste jedan penal!!\n";
		message.setText(content);
		message.setSubject("Sankcija");
		mailSender.send(message);
	}
	@Async
	public void sendAdvertiserCompplain(User user, String reportText){
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("adventurelandisa@gmail.com");
		message.setTo("alexmirkovic1719@gmail.com"); //client.mail
		String content="";
		Format formatter = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
		content+= "Hi "+user.getName()+",\n"+"\n";
		content+="Odgovor administratora na klijentov complain:"+reportText+".\n";
		message.setText(content);
		message.setSubject("Odgovor na complain");
		mailSender.send(message);
	}
	@Async
	public void sendClientCompplain(User user, String reportText){
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("adventurelandisa@gmail.com");
		message.setTo("alexmirkovic1719@gmail.com"); //client.mail
		String content="";
		Format formatter = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
		content+= "Hi "+user.getName()+",\n"+"\n";
		content+="Odgovor administratora na vas complain:"+reportText+".\n";
		message.setText(content);
		message.setSubject("Odgovor na complain");
		mailSender.send(message);
	}
	@Async
	public void sendNotificationAboutCommonUser(User user,User user1){
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("adventurelandisa@gmail.com");
		message.setTo("alexmirkovic1719@gmail.com"); //client.mail
		String content="";
		Format formatter = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
		content+= "Hi "+user.getName()+",\n"+"\n";
		content+="Vas zahtev za sankciju klijenta "+user1.getName()+"je odobren!!\n";
		message.setText(content);
		message.setSubject("Sankcija klijenta");
		mailSender.send(message);
	}
	@Async
	public void sendNotificationAboutNotApproveCommonClient(User user){
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("adventurelandisa@gmail.com");
		message.setTo("alexmirkovic1719@gmail.com"); //client.mail
		String content="";
		Format formatter = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
		content+= "Hi "+user.getName()+",\n"+"\n";
		content+="Na zalost vlasnik je zatrazio sankciju, koja nije odobrena!! Potrudite se da se ovo vi??e ne ponovi!\n";
		message.setText(content);
		message.setSubject("Sankcija");
		mailSender.send(message);
	}
	@Async
	public void sendNotificationAboutNotApproveCommonUser(User user,User user1){
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("adventurelandisa@gmail.com");
		message.setTo("alexmirkovic1719@gmail.com"); //client.mail
		String content="";
		Format formatter = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
		content+= "Hi "+user.getName()+",\n"+"\n";
		content+="Vas zahtev za sankciju klijenta "+user1.getName()+"njeje odobren!!\n";
		message.setText(content);
		message.setSubject("Sankcija klijenta");
		mailSender.send(message);
	}
}
