package com.kierznowski.rentalapp.client.config;

import com.kierznowski.rentalapp.client.services.OfferService;
import com.kierznowski.rentalapp.client.services.RestOfferService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
public class ClientConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(cors -> {
            CorsConfigurationSource source = request -> {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(List.of("http://localhost:3000"));
                config.setAllowedMethods(List.of("*"));
                config.setAllowedHeaders(List.of("*"));
                config.setAllowCredentials(true);
                return config;
            };
            cors.configurationSource(source);
        });
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET, "bff/offers").permitAll()
                .anyRequest().authenticated());
        http.oauth2Login(login -> login.loginPage("/oauth2/authorization/rentalapp-client"));
       // http.oauth2Client(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }

    @Bean
    @RequestScope
    public OfferService offerService(OAuth2AuthorizedClientService clientService) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String accessToken = null;
        if(authentication.getClass().isAssignableFrom(OAuth2AuthenticationToken.class)) {
            OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;

            String clientRegistrationId = token.getAuthorizedClientRegistrationId();
            if(clientRegistrationId.equals("rentalapp-client")) {
                OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(clientRegistrationId, token.getName());
                accessToken = client.getAccessToken().getTokenValue();
            }
        }
        return new RestOfferService(accessToken);
    }


}


