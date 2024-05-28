package com.in28minutes.springboot.myfirstwebapp.security;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.function.Function;

@Configuration
public class SpringSecurityConfiguration {

    //로그인 정보는 대개 LDAP or Database에 저장
    //In-memory DB를 사용
    @Bean
    public InMemoryUserDetailsManager createUserDetailsManager(){

        UserDetails userDetails1 = createNewUser("in28minutes", "qwer1234");
        UserDetails userDetails2 = createNewUser("qwer1234", "qwer1234");
        return new InMemoryUserDetailsManager(userDetails1, userDetails2);
    }
    private UserDetails createNewUser(String username, String password){
        Function<String, String> passwordEncoder
                = input -> passwordEncoder().encode(input);
        UserDetails userDetails = User.builder()
                .passwordEncoder(passwordEncoder)
                .username(username)
                .password(password)
                .roles("USER", "ADMIN") // 어플리케이션 안의 역할, 특정 작업을 하기 위한 역할
                .build();
        return userDetails;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 모든 요청이 승인되도록 수행
        http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated()
        );
        // 그렇지 않으면 로그인 form을 통해 인증해야함.
        http.formLogin(withDefaults());
        
        //csrf와 어플리케이션 사용 비활성화
        http.csrf().disable();
        http.headers().frameOptions().disable();
        return http.build();
    }
}
