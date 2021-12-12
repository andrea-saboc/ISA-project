package com.example.isa.service.implemented;

import javax.mail.MessagingException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.isa.dto.ClientRegistrationDTO;
import com.example.isa.mail.AccountActivationFormatter;
import com.example.isa.mail.MailService;
import com.example.isa.model.Client;
import com.example.isa.repository.UserRepository;

import net.bytebuddy.utility.RandomString;

@Service
public class ClientRegistrationService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private MailService<String> mailService;
	

	public Client registerClient(ClientRegistrationDTO clientDto, String siteUrl) throws MessagingException {
		
		Client client = clientDto.createClient(clientDto);
		client.setLoyaltyPoints(0);
		client.setPenaltyPoints(0);
		client.setPassword(passwordEncoder.encode(clientDto.getPassword()));
		String activationCode = RandomString.make(10);
	    client.setActivationCode(activationCode);
	    client.setBlocked(false);
	    sendActivationLink(client,siteUrl);
		return userRepository.save(client);
	}
	
    private void sendActivationLink(Client client, String siteUrl) throws MessagingException {
        String verifyURL = siteUrl + "/activation?code=" + client.getActivationCode() + "&email=" + client.getEmail();
        
        System.out.println("Url je ");
        System.out.println(verifyURL);
        mailService.sendMail(client.getEmail(), verifyURL, new AccountActivationFormatter());
    }

	public boolean clientExists(String email) {
		
		if (userRepository.findByEmail(email) != null)
			return true;
		return false;
	}
}
