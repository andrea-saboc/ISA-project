package com.example.isa.service;

import com.example.isa.dto.LoginDto;
import com.example.isa.dto.UserTokenState;

public interface LoginService {
	
    public UserTokenState logIn(LoginDto authenticationRequest);
    
}
