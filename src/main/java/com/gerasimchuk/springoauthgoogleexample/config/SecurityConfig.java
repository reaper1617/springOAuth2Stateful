package com.gerasimchuk.springoauthgoogleexample.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String googleClientSecret;

    private static final String GOOGLE_REGISTRATION_ID = "google";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers("/oauth_login", "/failure", "/unsecured").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .loginPage("/oauth_login")
                .defaultSuccessUrl("/index")
                .failureUrl("/failure");

        return httpSecurity.build();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        ClientRegistration clientRegistration = CommonOAuth2Provider.GOOGLE.getBuilder(GOOGLE_REGISTRATION_ID)
                .clientId(googleClientId)
                .clientSecret(googleClientSecret)
                .build();
        return new InMemoryClientRegistrationRepository(clientRegistration);
    }

}
