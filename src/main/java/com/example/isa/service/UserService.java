package com.example.isa.service;


import java.nio.file.AccessDeniedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.isa.model.AccountDeletionRequest;
import com.example.isa.model.Client;
import com.example.isa.model.User;
import com.example.isa.repository.ClientRepository;
import com.example.isa.repository.DeletionRequestRepository;
import com.example.isa.repository.UserRepository;

@Service
public class UserService {

	@Autowired
    private UserRepository userRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
    private DeletionRequestRepository deletionRequestRepository;

	
	public Iterable<User> findAll()  throws AccessDeniedException{
		return userRepository.findAll();
	}

    public User updateUserInfo(User user) {
    	
    	//moze samo swith za user type i svi koriste funkciju 
    	//moze da se ovo setovanje stavi u odvojenu funkciju
        Client c = clientRepository.findByEmail(user.getEmail());
        c.setName(user.getName());
        c.setSurname(user.getSurname());

        clientRepository.save(c);
        return user;
   
    }
    
	public User createDeletionRequest(String reason) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(user.getName());
		deletionRequestRepository.save(new AccountDeletionRequest(user.getId(),reason));
		
		return user;
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
