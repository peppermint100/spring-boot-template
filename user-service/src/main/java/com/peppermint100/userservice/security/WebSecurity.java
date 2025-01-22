package com.peppermint100.userservice.security;

import com.peppermint100.userservice.service.ApplicationUserService;
import com.peppermint100.userservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurity {

    private final JwtUtil jwtUtil;
    private final ApplicationUserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    private static final String[] WHITE_LIST_URLS = {
            "/users/register",
            "/users/login"
    };

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        AuthenticationProvider authenticationProvider = authenticationProvider();

        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(jwtUtil, userService);

        http.csrf(csrf -> csrf.disable());

        http
                .authorizeHttpRequests(authz -> authz
                .requestMatchers(WHITE_LIST_URLS).permitAll()
                .anyRequest().authenticated())
                .authenticationProvider(authenticationProvider)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        ;

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }
}
