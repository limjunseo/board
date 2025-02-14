package com.jun.board_project.global.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/post").hasRole("USER")  // 특정 경로 권한 설정
                        .anyRequest().permitAll()  // 나머지는 모두 허용
                )
                .formLogin(login -> login
                        .loginPage("/login")               // 로그인 페이지 경로
                        .loginProcessingUrl("/login")      // 로그인 처리 URL
                        .usernameParameter("memberId")     // 사용자명 파라미터 이름
                        .passwordParameter("pw")           // 비밀번호 파라미터 이름
                        .defaultSuccessUrl("/")            // 로그인 성공 후 리다이렉트 경로
                        .failureUrl("/login?error=true")   // 로그인 실패 시 경로
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")              // 로그아웃 URL
                        .logoutSuccessUrl("/")             // 로그아웃 성공 후 리다이렉트 경로
                        .permitAll()
                )
                .rememberMe(rememberMe -> rememberMe
                        .key("uniqueAndSecret")            // Remember Me 기능에 사용될 키
                        .tokenValiditySeconds(60 * 60 * 7) // Remember Me 토큰 유효 시간 (7시간)
                )
                .csrf(csrf -> csrf.disable());  // 로컬 환경에서 CSRF 보호 비활성화

        return http.build();
    }
}
