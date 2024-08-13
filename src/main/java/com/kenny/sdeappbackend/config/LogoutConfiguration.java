package com.kenny.sdeappbackend.config;


import com.kenny.sdeappbackend.auth.CustomLogoutHandler;
import com.kenny.sdeappbackend.auth.CustomLogoutSuccessHandler;
import com.kenny.sdeappbackend.repo.TokenRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
public class LogoutConfiguration {

    @Bean
    public CustomLogoutHandler logoutHandler(TokenRepo tokenRepo, JwtService jwtService) {
        return new CustomLogoutHandler(tokenRepo, jwtService);
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }
}
