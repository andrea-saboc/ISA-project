package com.example.isa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.isa.dto.LoginDto;
import com.example.isa.dto.UserTokenState;
import com.example.isa.model.User;
import com.example.isa.security.TokenUtils;
import com.example.isa.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private AuthenticationManager authenticationManager;

    public UserTokenState logIn(LoginDto authenticationRequest) {
        return getUserTokenState(authenticationRequest);
    }

    private UserTokenState getUserTokenState(LoginDto authenticationRequest) throws BadCredentialsException{
        System.out.println("U get user token state");
        
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()));
        
        System.out.println("izasli iz auth");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();
        
        System.out.println("Ulogovani user");
        System.out.println(user.getEmail());
        
        
        String username = user.getUsername();
        String userType = user.getClass().getSimpleName();
        String accessToken = tokenUtils.generateToken(username);
        int accessExpiresIn = tokenUtils.getExpiredIn();
        return new UserTokenState(userType, accessToken, accessExpiresIn);
    }
    
    

}
