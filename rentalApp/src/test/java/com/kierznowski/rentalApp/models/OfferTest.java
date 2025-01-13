package com.kierznowski.rentalApp.models;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OfferTest {

    @Nested
    @DisplayName("Unit tests for Offer entity")
    class OfferUnitTests {

        private Validator validator;

        @BeforeEach
        void setUp() {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            validator = factory.getValidator();
        }

        @Test
        @DisplayName("Testing validation for proper offer data")
        void testOfferValidationSuccess() {
            Offer offer = new Offer();
            offer.setOfferName("Mock offer name");
            offer.setCity("Warsaw");
            offer.setBuildingYear(2024);
            offer.setFullPrice(BigDecimal.valueOf(1000000));
            offer.setRoomsNumber(3);
            offer.setArea(72);

            Set<ConstraintViolation<Offer>> violations = validator.validate(offer);

            assertEquals(0, violations.size());
        }

        @Test
        @DisplayName("Testing validation for wrong offer data")
        void testOfferValidationFailed() {
            Offer offer = new Offer(); // Missing required offerName and city
            offer.setBuildingYear(1700); // should be at least from 1800
            offer.setFullPrice(BigDecimal.valueOf(-100)); // should be positive
            offer.setRoomsNumber(0); // should be greater than 0

            Set<ConstraintViolation<Offer>> violations = validator.validate(offer);
            assertEquals(5, violations.size());
        }
    }


    @Nested
    @DataJpaTest
    @DisplayName("JPA Tests for Offer entity")
    class OfferJpaTests {

        @Autowired
        private TestEntityManager entityManager;

        @Test
        @DisplayName("Persist and Retrieve Offer")
        void testPersistAndRetrieveOffer() {
            User user = new User(
                    "test@test.com", "Adam", "Smith", "123456789",
                    "Warsaw", "mockstreet", "00-000");
            Offer offer = new Offer();
            offer.setOfferName("Mock offer name");
            offer.setCity("Warsaw");
            offer.setBuildingYear(2020);
            offer.setFullPrice(BigDecimal.valueOf(800000));
            offer.setRoomsNumber(4);
            offer.setArea(80);
            offer.setUser(user);

            entityManager.persist(user);
            entityManager.persist(offer);
            entityManager.flush();

            Offer retrievedOffer = entityManager.find(Offer.class, offer.getId());

            assertNotNull(retrievedOffer);
            assertEquals("Mock offer name", retrievedOffer.getOfferName());
            assertEquals("test@test.com", retrievedOffer.getUser().getEmail());

            assertNotNull(retrievedOffer.getCreatedAt(), "Verify persisting createdAt field in Offer entity");
        }

    }

}
