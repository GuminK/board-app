package com.example.backend.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class SecurityConfig{
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        // Spring에서 React를 배포할 때 필요
                        // 가끔 Build 초기화가 안되는데 브라우저에서 Ctrl+Shift+R로 캐시 지우고 Build파일 다시 넣기
                        .requestMatchers("/", "/index.html", "/favicon.ico", "/manifest.json", "/robots.txt", "/static/**", "/logo192.png", "/logo512.png").permitAll()
                        // 로그인이 필요한 api
                        .requestMatchers("/api/auth/logout").authenticated()
                        // 로그인이 필요없는 api
                        .requestMatchers("/api/auth/**", "/api/board/list", "/api/board/detail/**").permitAll()
                        // 로그인이 필요한 api
                        .requestMatchers("/api/**").authenticated()
                        // 나머지
                        .anyRequest().permitAll()
                )
                // 스프링 기본 로그인 폼 비활성화
                .formLogin(form -> form.disable())
                .logout(logout -> logout.disable())
                .httpBasic(basic -> basic.disable())
                .build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
