package com.kierznowski.rentalApp.configuration;

import com.kierznowski.rentalApp.models.User;
import com.kierznowski.rentalApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Value("${keySetURI}")
    private String keySetUri;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(c -> c.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .oauth2ResourceServer(c -> c.jwt(j -> j.jwkSetUri(keySetUri)))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/offers").permitAll()
                        .requestMatchers(HttpMethod.GET, "/file-system/image/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/searchOffers").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/searchOffers").permitAll()
                        .anyRequest().permitAll())
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000", "http://localhost:9090", "http://localhost:9000"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public GrantedAuthoritiesMapper grantedAuthoritiesMapper() {
        return authorities -> {
            Set<GrantedAuthority> mappedAuthorities = new HashSet<>();

            authorities.forEach(authority -> {
                GrantedAuthority mappedAuthority;

                if(authority instanceof OidcUserAuthority) {
                    OidcUserAuthority userAuthority = (OidcUserAuthority) authority;
                    mappedAuthority = new OidcUserAuthority("ROLE_USER", userAuthority.getIdToken(), userAuthority.getUserInfo());
                } else if (authority instanceof OAuth2UserAuthority) {
                    OAuth2UserAuthority userAuthority = (OAuth2UserAuthority) authority;
                    mappedAuthority = new OAuth2UserAuthority("ROLE_USER", userAuthority.getAttributes());
                } else {
                    mappedAuthority = authority;
                }
                mappedAuthorities.add(mappedAuthority);
            });
            return mappedAuthorities;
        };
    }
}
