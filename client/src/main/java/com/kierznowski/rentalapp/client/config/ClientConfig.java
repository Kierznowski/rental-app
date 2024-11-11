package com.kierznowski.rentalapp.client.config;

import com.kierznowski.rentalapp.client.services.OfferService;
import com.kierznowski.rentalapp.client.services.RestOfferService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@Configuration
@EnableWebSecurity
public class ClientConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));
        http.csrf(csrf -> csrf.disable());
        http.oauth2Login(Customizer.withDefaults());
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET, "/bff/offers").permitAll()
                .requestMatchers(HttpMethod.GET, "/bff/offers/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/bff/offers/search").permitAll()
                .requestMatchers(HttpMethod.GET, "/image/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/register").permitAll()
                .anyRequest().authenticated());
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:8080", "http://localhost:9000"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    @Bean
    @RequestScope
    public OfferService offerService(OAuth2AuthorizedClientService clientService) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String accessToken = null;
        System.out.println("Before big IF");
        System.out.println(authentication);
        if(authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
            String clientRegistrationId = token.getAuthorizedClientRegistrationId();
            if(clientRegistrationId.equals("authServer")) {
                OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(clientRegistrationId, token.getName());
                accessToken = client.getAccessToken().getTokenValue();
                System.out.println("Acces token in bean: " + accessToken);
            }
            System.out.println("After if");
        }
        return new RestOfferService(accessToken);
    }
}


