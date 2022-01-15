package com.example.isa.service;

import com.example.isa.dto.LoginDTO;
import com.example.isa.dto.UserTokenState;

public interface LoginService {
	
    public UserTokenState logIn(LoginDTO authenticationRequest);
    
}
