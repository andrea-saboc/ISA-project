package com.example.isa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.example.isa.security.auth.RestAuthenticationEntryPoint;
import com.example.isa.security.auth.TokenAuthenticationFilter;
import com.example.isa.service.impl.UserCredentialsService;
import com.example.isa.security.TokenUtils;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    
    @Autowired
    private UserCredentialsService userCredentialsService;

    @Autowired
    private TokenUtils tokenUtils;

    public WebSecurityConfig(){}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userCredentialsService).passwordEncoder(passwordEncoder());
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
    	
        web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "/favicon.ico", "/**/*.html",
                "/**/*.css", "/**/*.js", "/images/**", "/resources/**", "/api/entityImage/images/**");
        web.ignoring().antMatchers(HttpMethod.POST,"/login/**");
    }

     
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
                
                .authorizeRequests()
                                    .antMatchers("/register/**").permitAll()
                                    .antMatchers("/checkActivationCode").permitAll()
                                    .antMatchers("/login/**").permitAll()

                                    .antMatchers("/reservations/**").permitAll()
                                    .antMatchers("/getAllAdvertisers/**").permitAll()

                                    .antMatchers("/feedbacks/**").permitAll()

                                    .antMatchers("/boat**").permitAll()
                                    .antMatchers("/boats/**").permitAll()

                                    .antMatchers("/mansion**").permitAll()
                                    .antMatchers("/mansions/**").permitAll()
                                    .antMatchers("/deleteMansion").permitAll()
                                    .antMatchers("/registerBoat").permitAll()
                                    .antMatchers("/subscriptions/checkBoatSubscription").permitAll()
                                    .antMatchers("/ownersBoats").permitAll()
                                    .antMatchers("/getBoatAvailability").permitAll()
                                    .antMatchers("/getBoatDiscountReservations").permitAll()
                .antMatchers("/resources/**").permitAll()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .anyRequest().authenticated().and()
                .cors().and()
                
                .addFilterBefore(new TokenAuthenticationFilter(tokenUtils, userCredentialsService),
                        BasicAuthenticationFilter.class);
        http.csrf().disable();
    }
   
}
