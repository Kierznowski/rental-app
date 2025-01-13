package com.kierznowski.rentalApp.controllers;

import com.kierznowski.rentalApp.models.User;
import com.kierznowski.rentalApp.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testing registration controller")
public class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Test
    @DisplayName("Testing user registration")
    void testSaveUserData() throws Exception {
        when(userRepository.save(any(User.class))).thenAnswer( invocation -> {
            User user = invocation.getArgument(0);
            user.setUserId(1L);
            return user;
        });

        mockMvc.perform(post("/register")
                .contentType("application/json")
                .content("""
                        {
                            "email": "test@test.com",
                            "password": "password",
                            "firstName": "John",
                            "secondName": "Adams",
                            "phone": "123456789",
                            "userCity": "Warsaw",
                            "userStreet": "mockstreet",
                            "userZip": "00-000"
                        }
                        """))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(1)).save(captor.capture());

        User savedUser = captor.getValue();
        assertAll("Verify saved user properties",
                () -> assertEquals("test@test.com", savedUser.getEmail()),
                () -> assertEquals("John", savedUser.getFirstName()),
                () -> assertEquals("Adams", savedUser.getSecondName()),
                () -> assertEquals("123456789", savedUser.getPhone()),
                () -> assertEquals("Warsaw", savedUser.getUserCity()),
                () -> assertEquals("mockstreet", savedUser.getUserStreet()),
                () -> assertEquals("00-000", savedUser.getUserZip())
        );
    }
}
