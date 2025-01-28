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
public class WebSecurityConfig{


    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests((authorize) -> authorize
                                .requestMatchers("/post").hasRole("USER")
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .anyRequest().permitAll()
                        //.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        //.anyRequest().authenticated()
                )
                .formLogin( (formLogin) -> formLogin
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .usernameParameter("memberId")
                        .passwordParameter("pw")
                        .defaultSuccessUrl("/")
                        .failureUrl("/login")
                        .permitAll()
                )
                .logout( (logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()
                )
                .rememberMe( (rememberMe) -> rememberMe
                        .key("uniqueAndSecret")
                        .tokenValiditySeconds(60*60*7)
                )
                .csrf( (csrf) -> csrf.disable() ); //로컬 환경에서 확인을 위해 disable;


        return http.build();
    }

}