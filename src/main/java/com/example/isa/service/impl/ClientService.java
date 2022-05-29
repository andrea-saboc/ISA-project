package com.example.isa.service.impl;


import java.nio.file.AccessDeniedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.isa.dto.ChangingPasswordDto;
import com.example.isa.model.AccountDeletionRequest;
import com.example.isa.model.Client;
import com.example.isa.model.User;
import com.example.isa.repository.ClientRepository;
import com.example.isa.repository.DeletionRequestRepository;
import com.example.isa.repository.UserRepository;
import com.example.isa.service.AuthenticationService;

@Service
public class ClientService {

	@Autowired
    private UserRepository userRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
    private DeletionRequestRepository deletionRequestRepository;
	@Autowired
	AuthenticationService authenticationService;
	@Autowired
	private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
	
	
	public Iterable<User> findAll()  throws AccessDeniedException{
		return userRepository.findAll();
	}

    public User updateUser(User user) {
    	User c = userRepository.findByEmail(user.getEmail());
        c.setName(user.getName());
        c.setSurname(user.getSurname());
        c.setCity(user.getCity());
        c.setCountry(user.getCountry());
        c.setAddress(user.getAddress()); 
        c.setPhoneNumber(user.getPhoneNumber());
        return userRepository.save(c);
    }
    
    public Client getLoggedClient() {
    	return clientRepository.findByEmail(authenticationService.getLoggedUser().getEmail());
   			
    }
    
    public void ChangePassword(ChangingPasswordDto passwords)  throws BadCredentialsException{
    	User client = userRepository.findByEmail(authenticationService.getLoggedUser().getEmail());
       authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(client.getEmail(),
                        passwords.getOldPassword()));
        
		client.setPassword(passwordEncoder.encode(passwords.getNewPassword()));
		userRepository.save(client);
    }
    
	public User createDeletionRequest(String reason) {
		deletionRequestRepository.save(new AccountDeletionRequest(authenticationService.getLoggedUser().getId(),reason));
		return authenticationService.getLoggedUser();
	}
	
	
    public boolean checkIfClientEmailExists(String email) {
		boolean exsists = false;
		try {
			Client client = clientRepository.findByEmail(email);
			if (client != null) {
				exsists = true;
			}
		} catch (Exception e){
			exsists = false;
		}
		return exsists;
    }
    
    public boolean checkActivationCode(String code) {
    	
    	
    	System.out.println("akt kod koji se trazi "+ code);
    	Client c = clientRepository.findByActivationCode(code);

    	
    	if(c!= null) {
    		c.setBlocked(false);
    		clientRepository.save(c);
    		return true;  		
    	}else
    		return false;
    }
    
    public boolean userAlreadyActivated(String code) {   	
    	return clientRepository.findByActivationCodeAndBlockedFalse(code) != null;
    }
}
