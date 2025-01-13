package com.kierznowski.rentalApp.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfigurationSource;

import static org.junit.jupiter.api.Assertions.*;

public class SecurityConfigTest {

    @Nested
    @DisplayName("Unit tests for SecurityConfig class")
    class SecurityConfigUnitTests {


        SecurityConfig securityConfig;

        @Mock
        CorsConfigurationSource corsConfigurationSource;

        @BeforeEach
        void setUp() {
            securityConfig = new SecurityConfig();
        }

        @Test
        @DisplayName("Test password encoder")
        void testPasswordEncoderShouldReturnBCrypt() {
            PasswordEncoder passwordEncoder = securityConfig.passwordEncoder();

            assertNotNull(passwordEncoder);
            assertInstanceOf(PasswordEncoder.class, passwordEncoder);

            String rawPassword = "password123";
            String encodedPassword = passwordEncoder.encode(rawPassword);
            assertTrue(passwordEncoder.matches(rawPassword, encodedPassword));
        }
    }
}
