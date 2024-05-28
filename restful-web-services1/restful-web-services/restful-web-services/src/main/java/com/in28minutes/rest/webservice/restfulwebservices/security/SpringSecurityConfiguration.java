package com.in28minutes.rest.webservice.restfulwebservices.security;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 1) 모든 요청이 인증되어야 함
        http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated()
        );
        // 2) 인증되어 있지 않으면 팝업으로 인증 수행
        http.httpBasic(withDefaults());
        // 3) 인증이 되지 않아도 POST, PUT 가능
        http.csrf().disable();
        return http.build();
    }
}
