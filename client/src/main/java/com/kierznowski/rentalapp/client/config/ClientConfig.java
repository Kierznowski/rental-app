package com.kierznowski.rentalapp.client.config;

import com.kierznowski.rentalapp.client.services.OfferService;
import com.kierznowski.rentalapp.client.services.RestOfferService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@Configuration
@EnableWebSecurity
public class ClientConfig {

    @Value("${frontend.url.base}")
    String frontendUrl;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, OAuth2AuthorizedClientService clientService) throws Exception {

        HeaderWriterLogoutHandler clearSiteData = new HeaderWriterLogoutHandler(new ClearSiteDataHeaderWriter(
                        ClearSiteDataHeaderWriter.Directive.COOKIES,
                        ClearSiteDataHeaderWriter.Directive.CACHE,
                        ClearSiteDataHeaderWriter.Directive.STORAGE));

        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));
        http.csrf(csrf -> csrf.disable());


        http.oauth2Login(login -> login.successHandler(((request, response, authentication) -> {
            String redirectUri = (String) request.getSession().getAttribute("redirect_uri");
            if(redirectUri != null) {
                response.sendRedirect(redirectUri);
            } else {
                response.sendRedirect(frontendUrl);
            }
        })));
        http.logout(logout -> logout
                .logoutUrl("/logout")
                .addLogoutHandler((request, response, authentication) -> {
                    String token = getToken(clientService);
                    revokeToken(token);
                    SecurityContextHolder.clearContext();
                    request.getSession().invalidate();
                    new CookieClearingLogoutHandler("JSESSIONID", "SESSION");
                })
                .logoutSuccessHandler(((request, response, authentication) -> {
                    response.sendRedirect("/login");
                }))
        );

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, "/logout").permitAll()
                .requestMatchers(HttpMethod.POST, "/bff/auth/login").permitAll()
                .requestMatchers(HttpMethod.GET, "/bff/auth/is-auth").permitAll()
                .requestMatchers(HttpMethod.GET, "/offers").permitAll()
                .requestMatchers(HttpMethod.GET, "/offers/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/offers/searched-offers").permitAll()
                .requestMatchers(HttpMethod.GET, "/image/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/bff/auth/register").permitAll()
                .anyRequest().authenticated());
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(frontendUrl, "http://localhost:8080", "http://localhost:9000"));
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
        String accessToken = getToken(clientService);
        return new RestOfferService(accessToken);
    }

    private String getToken(OAuth2AuthorizedClientService clientService) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String accessToken = null;
        if(authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
            String clientRegistrationId = token.getAuthorizedClientRegistrationId();
            if(clientRegistrationId.equals("authServer")) {
                OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(clientRegistrationId, token.getName());
                accessToken = client.getAccessToken().getTokenValue();
            }
        }
        return accessToken;
    }

    private void revokeToken(String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth("rentalapp-client", "secret");
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("token", token);
        System.out.println("Revokation of token: " + token);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
        ResponseEntity<HttpStatus> status = restTemplate.postForEntity("http://127.0.0.1:9000/oauth2/revoke", request, HttpStatus.class);
        System.out.println(status);
    }

}


