package com.jun.board_project.global.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final CustomAuthenticationSuccessHandler successHandler;

    public SecurityConfig(CustomAuthenticationSuccessHandler successHandler) {
        this.successHandler = successHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()  // 모든 요청 인증 필요
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .successHandler(successHandler)  // 커스텀 Success Handler 등록
                        .permitAll()
                )
                .logout(logout -> logout.permitAll());  // 로그아웃 허용
        return http.build();
    }
}
