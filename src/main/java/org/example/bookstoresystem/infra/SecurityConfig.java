package org.example.bookstoresystem.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(crsf -> crsf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize

                        .requestMatchers(HttpMethod.POST,"/api/v1/login").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/v1/register").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/v1/author").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/author").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/author/all-authors").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/author/by-id/{id}").permitAll() // Acesso público
                        .requestMatchers(HttpMethod.GET, "/api/v1/author/by-name").permitAll() // Acesso público
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/author/delete-author/{id   }").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/v1/book").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/book").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/book/all-books").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/book/by-id/{id}").permitAll() // Acesso público
                        .requestMatchers(HttpMethod.GET, "/api/v1/book/by-title").permitAll() // Acesso público
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/book/delete-book/{id}").hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}