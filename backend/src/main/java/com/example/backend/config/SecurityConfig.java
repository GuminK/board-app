package com.example.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig{
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                // CSRF 일단 비활성화
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/logout", "/register", "/me", "/board/list").permitAll()
                        .requestMatchers("/board/detail/**").permitAll()
                        .requestMatchers("/board/create", "/board/update").authenticated()
                        .anyRequest().authenticated()
                )
                // 스프링 기본 로그인 폼 비활성화
                .formLogin(form -> form.disable())
                .logout(logout -> logout.disable())
//                .httpBasic(Customizer.withDefaults())
                .httpBasic(basic -> basic.disable())
                .build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
