package com.kierznowski.rentalApp.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(passwordEncoder);
        userService.restTemplate = mock(RestTemplate.class);
    }

    @Test
    @DisplayName("Test userService initialization")
    void testUserServiceInitialization() {
        assertNotNull(userService);
        assertNotNull(userService.restTemplate);
        assertNotNull(userService.passwordEncoder);
    }

    @Test
    @DisplayName("Test password encoding in UserService")
    void testPasswordEncoding() {
        String rawPassword = "password123";
        String encodedPassword = "encodedPassword123";
        when(passwordEncoder.encode(rawPassword)).thenReturn(encodedPassword);

        String result = userService.passwordEncoder.encode(rawPassword);

        assertEquals(result, encodedPassword);
        verify(passwordEncoder, times(1)).encode(rawPassword);
    }

    @Test
    @DisplayName("Test RestTemplate call in UserService")
    void testRestTemplateCall() {
        String url = "http://api.example.com/endpoint";
        String mockResponse = "Some Mock API response";
        when(userService.restTemplate.getForObject(url, String.class)).thenReturn(mockResponse);

        String result = userService.restTemplate.getForObject(url, String.class);

        assertEquals(mockResponse, result);
        verify(userService.restTemplate, times(1)).getForObject(url, String.class);
    }
}
