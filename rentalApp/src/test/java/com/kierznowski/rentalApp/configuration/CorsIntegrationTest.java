package com.kierznowski.rentalApp.configuration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CorsIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Testing CORS config with allowed request")
    void testCorsConfigProperRequest() throws Exception {
        mockMvc.perform(get("/api/offers?recent")
                .header("Origin", "http://localhost:9090"))
                .andExpect(status().isOk())
                .andExpect(header().string("Access-Control-Allow-Origin", "http://localhost:9090"));
    }

    @Test
    @DisplayName("Testing CORS config with not allowed origin")
    void testCorsConfigNotAllowedOrigin() throws Exception {
        mockMvc.perform(get("/api/offers?recent")
                        .header("Origin", "http://localhost:4000"))
                .andExpect(status().isForbidden())
                .andExpect(header().doesNotExist("Access-Control-Allow-Origin"));

    }

    @Test
    @DisplayName("Testing CORS config with not allowed method")
    void testCorsConfigNotAllowedMethod() throws Exception {
        mockMvc.perform(patch("/api/offers?recent")
                .header("Origin", "http://localhost:9090")
                .header("Access-Control-Request-Method", "POST"))
                .andExpect(status().isForbidden());
    }

}
