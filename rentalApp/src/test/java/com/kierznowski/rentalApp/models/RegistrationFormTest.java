package com.kierznowski.rentalApp.models;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class RegistrationFormTest {

    private RegistrationForm registrationForm;

    @BeforeEach
    void setUp() {
        registrationForm = new RegistrationForm();
        registrationForm.setEmail("test@test.com");
        registrationForm.setPassword("password");
        registrationForm.setFirstName("Adam");
        registrationForm.setSecondName("Smith");
        registrationForm.setPhone("123456789");
        registrationForm.setUserCity("Warsaw");
        registrationForm.setUserStreet("Mock Street");
        registrationForm.setUserZip("00-000");
    }

    @Test
    @DisplayName("Testing RegistrationForm getters and setters")
    void testGettersAndSetters() {
        assertEquals("test@test.com", registrationForm.getEmail());
        assertEquals("password", registrationForm.getPassword());
        assertEquals("Adam", registrationForm.getFirstName());
        assertEquals("Smith", registrationForm.getSecondName());
        assertEquals("123456789", registrationForm.getPhone());
        assertEquals("Warsaw", registrationForm.getUserCity());
        assertEquals("Mock Street", registrationForm.getUserStreet());
        assertEquals("00-000", registrationForm.getUserZip());
    }

    @Test
    @DisplayName("Test toUser() method")
    void testToUserConversionMethod() {
        User user = registrationForm.toUser();

        assertNotNull(user);
        assertEquals("test@test.com", user.getEmail());
        assertEquals("Adam", user.getFirstName());
        assertEquals("Smith", user.getSecondName());
        assertEquals("123456789", user.getPhone());
        assertEquals("Warsaw", user.getUserCity());
        assertEquals("Mock Street", user.getUserStreet());
        assertEquals("00-000", user.getUserZip());
    }

    @Nested
    class RegistrationFormValidationTest {

        private Validator validator;

        @BeforeEach
        void setUp() {
            validator = Validation.buildDefaultValidatorFactory().getValidator();
        }

        @Test
        @DisplayName("Testing RegistrationForm success validation")
        void testValidRegistrationForm() {
            RegistrationForm form = new RegistrationForm();
            form.setEmail("test@test.com");
            form.setPassword("password");
            form.setFirstName("John");
            form.setSecondName("Smith");
            form.setPhone("123456789");
            form.setUserCity("Warsaw");
            form.setUserStreet("Mock Street");
            form.setUserZip("00-000");

            Set<ConstraintViolation<RegistrationForm>> violations = validator.validate(form);
            assertEquals(0, violations.size());
        }

        @Test
        @DisplayName("Testing validation with invalid email format")
        void testInvalidEmail() {
            RegistrationForm form = new RegistrationForm();
            form.setEmail("test.com");
            form.setPassword("password123");
            form.setFirstName("John");
            form.setSecondName("Adams");
            form.setPhone("123456789");
            form.setUserCity("Warsaw");
            form.setUserStreet("Mock Street");
            form.setUserZip("00-000");

            Set<ConstraintViolation<RegistrationForm>> violations = validator.validate(form);
            assertEquals(1, violations.size());
            assertEquals("invalid email format", violations.iterator().next().getMessage());
        }

        @Test
        @DisplayName("Testing validation with too short password")
        void testShortPassword() {
            RegistrationForm form = new RegistrationForm();
            form.setEmail("test@test.com");
            form.setPassword("123");
            form.setFirstName("John");
            form.setSecondName("Adams");
            form.setPhone("123456789");
            form.setUserCity("Warsaw");
            form.setUserStreet("Mock Street");
            form.setUserZip("10-001");

            Set<ConstraintViolation<RegistrationForm>> violations = validator.validate(form);
            assertEquals(1, violations.size());
            assertEquals("Password must be at least 8 characters", violations.iterator().next().getMessage());
        }

        @Test
        @DisplayName("Testing missing required fields")
        void testMissingFields() {
            RegistrationForm form = new RegistrationForm();
            form.setEmail("test@test.com");

            Set<ConstraintViolation<RegistrationForm>> violations = validator.validate(form);
            assertFalse(violations.isEmpty());
            assertTrue(violations.stream().anyMatch(v ->
                    Set.of("must not be null", "nie może mieć wartości null").contains(v.getMessage())));

        }
    }
}
