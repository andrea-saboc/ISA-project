package com.example.isa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.isa.dto.LoginDTO;
import com.example.isa.dto.UserTokenState;
import com.example.isa.model.User;
import com.example.isa.repository.UserRepository;
import com.example.isa.security.TokenUtils;

@Service
public class LoginService {

    private TokenUtils tokenUtils;
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    
    public UserTokenState logIn(LoginDTO authenticationRequest) {
        return getUserTokenState(authenticationRequest);
    }

/*
    public UserTokenState firstLogInPasswordChange(LoginDTO authenticationRequest) {
        User user = userRepository.findByEmail(authenticationRequest.getEmail());
        if (user.isEnabled()) {
            throw new UserAlreadyEnabled();
        }

        if (!passwordEncoder.matches(authenticationRequest.getOldPassword(), user.getPassword())) {
            throw new BadPasswordException();
        }
        if (isValidType(user)) {
            user.Enable();
            user.setPassword(passwordEncoder.encode(authenticationRequest.getPassword()));
            userRepository.save(user);
            return getUserTokenState(authenticationRequest);
        } else {
            throw new BadUserInformationException();
        }
    }

    private boolean isValidType(User user) {
        return user.getClass() == SystemAdmin.class || user.getClass() == Supplier.class || user.getClass() == PharmacyAdmin.class ||
                user.getClass() == Pharmacist.class;
    }
*/
    private UserTokenState getUserTokenState(LoginDTO authenticationRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();
        String username = user.getUsername();
        String userType = user.getClass().getSimpleName();
        String accessToken = tokenUtils.generateToken(username);
        int accessExpiresIn = tokenUtils.getExpiredIn();
        return new UserTokenState(userType, accessToken, accessExpiresIn);
    }
}
