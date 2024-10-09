package com.kierznowski.rentalApp.configuration;

import com.kierznowski.rentalApp.models.User;
import com.kierznowski.rentalApp.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/api/offers").hasRole("USER")
                        .requestMatchers("/add-offer", "/profile", "/account", "/edit-offer").hasRole("USER")
                        .requestMatchers("/", "/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("http://localhost:3000/login")
                        .loginProcessingUrl("http://localhost:3000/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .permitAll())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .csrf(csrf -> csrf.disable())
                .build();
    }

    // Redirect back to proper page after authentication
    /*@Bean
    public RequestCache refererRequestCache() {
        return new HttpSessionRequestCache() {
            @Override
            public void saveRequest(HttpServletRequest request, HttpServletResponse response) {
                String referrer = request.getHeader("referrer");
                if(referrer == null) {
                    referrer = request.getRequestURL().toString();
                }
                request.getSession().setAttribute("SPRING_SECURITY_SAVED_REQUEST", new SimpleSavedRequest(referrer));
            }
        };
    }*/
/*
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
    }*/

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return email -> {
            User user = userRepository.findByEmail(email);
            if(user != null){
                return user;
            }
            throw new UsernameNotFoundException("User " + email + " not found.");
        };
    }
}
