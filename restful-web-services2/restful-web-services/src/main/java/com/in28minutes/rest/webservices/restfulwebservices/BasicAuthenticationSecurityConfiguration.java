package com.in28minutes.rest.webservices.restfulwebservices;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

//@Configuration
public class BasicAuthenticationSecurityConfiguration {
    //Filter Chain
    // SecurityFilterChain은 HttpServletRequest에 매칭될 수 있는 필터 체인을 정의함.
    // 요청이 들어오면, Spring Security는 이 필터 체인을 사용함.
    // HttpSecurity는 특정한 HTTP 요청에 대해 웹 기반 보안을 사용할 수 있게함
    // 커스터마이징: CSRF를 막음. 세션이 존재하면 허용해야함. 
    // 그러나 이것은 Stateless rest api 임
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        RequestMatcher optionsMatcher = new AntPathRequestMatcher("/**", HttpMethod.OPTIONS.toString());
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(optionsMatcher).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/**")).authenticated());

        http.csrf(csrf -> csrf.disable());

        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
