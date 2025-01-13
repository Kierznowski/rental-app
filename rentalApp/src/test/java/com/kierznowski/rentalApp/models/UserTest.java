package com.kierznowski.rentalApp.models;


import com.kierznowski.rentalApp.repositories.UserRepository;
import net.bytebuddy.build.ToStringPlugin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Nested
    @DisplayName("Unit Tests for User entity")
    class UserUnitTests {

        @Test
        @DisplayName("Testing parametrized constructor")
        void testParametrizedConstructor() {
            User user = new User(
                    "test@test.com", "John", "Adams", "123456789",
                    "Warsaw", "Mock Street", "00-000");

            user.setUserId(1L);
            assertAll("Verify User parametrized constructor",
                    () -> assertEquals("test@test.com", user.getEmail()),
                    () -> assertEquals("John", user.getFirstName()),
                    () -> assertEquals("Adams", user.getSecondName()),
                    () -> assertEquals("123456789", user.getPhone()),
                    () -> assertEquals("Warsaw", user.getUserCity()),
                    () -> assertEquals("Mock Street", user.getUserStreet()),
                    () -> assertEquals("00-000", user.getUserZip()),
                    () -> assertNotNull(user.getOfferList()),
                    () -> assertTrue(user.getOfferList().isEmpty())
            );
        }

        @Test
        @DisplayName("Testing adding and removing offers to the User")
        void testOfferToUserRelationship() {
            User user = new User(
                    "test@test.com", "John", "Adams", "123456789",
                    "Warsaw", "Mock Street", "00-000");

            Offer offer1 = new Offer();
            Offer offer2 = new Offer();
            offer1.setId(1L);
            offer2.setId(2L);
            offer1.setUser(user);
            offer2.setUser(user);

            user.getOfferList().add(offer1);
            user.getOfferList().add(offer2);

            assertAll("Verify adding offers to the User",
                    () -> assertEquals(2, user.getOfferList().size()),
                    () -> assertTrue(user.getOfferList().contains(offer1)),
                    () -> assertTrue(user.getOfferList().contains(offer2))
            );

            user.getOfferList().remove(offer1);

            assertAll("Verify removing offer from the User",
                    () -> assertEquals(1, user.getOfferList().size()),
                    () -> assertFalse(user.getOfferList().contains(offer1)),
                    () -> assertTrue(user.getOfferList().contains(offer2))
            );
        }

        @Test
        @DisplayName("Testing setters and getters of the User")
        void testSettersAndGetters() {
            User user = new User("test@test.com", "John", "Adams", "123456789",
                    "Warsaw", "Mock Street", "00-000");
            user.setFirstName("Jimmy");
            user.setSecondName("Smith");
            user.setPhone("987654321");
            user.setUserCity("New York");
            user.setUserStreet("5th Avenue");
            user.setUserZip("11-111");

            assertAll("Verify setters and getters of the User",
                    () -> assertEquals("Jimmy", user.getFirstName()),
                    () -> assertEquals("Smith", user.getSecondName()),
                    () -> assertEquals("987654321", user.getPhone()),
                    () -> assertEquals("New York", user.getUserCity()),
                    () -> assertEquals("5th Avenue", user.getUserStreet()),
                    () -> assertEquals("11-111", user.getUserZip())
            );
        }
    }

    @Nested
    @DisplayName("JPA tests for User entity")
    @DataJpaTest
    class UserJpaTests {

        @Autowired
        private TestEntityManager entityManager;

        @Test
        @DisplayName("Testing User persistence")
        void testUserPersistence() {
            User user = new User("test@test.com", "John", "Adams", "123456789",
                    "Warsaw", "Mock Street", "00-000");

            User persistedUser = entityManager.persistAndFlush(user);

            assertNotNull(persistedUser.getUserId(), "User ID should not be null");
            entityManager.clear();

            User retrievedUser = entityManager.find(User.class, user.getUserId());

            assertNotNull(retrievedUser, "User should be retrieved from the database");
            assertAll("Verify retrieving User from DB",
                    () -> assertEquals("test@test.com", retrievedUser.getEmail()),
                    () -> assertEquals("John", retrievedUser.getFirstName()),
                    () -> assertEquals("Adams", retrievedUser.getSecondName()),
                    () -> assertEquals("123456789", retrievedUser.getPhone()),
                    () -> assertEquals("Warsaw", retrievedUser.getUserCity()),
                    () -> assertEquals("Mock Street", retrievedUser.getUserStreet()),
                    () -> assertEquals("00-000", retrievedUser.getUserZip())
            );
        }

        @Test
        @DisplayName("Testing cascading behaviour for User and Offer")
        void testCascadingBehaviour() {
            User user = new User("test@test.com", "John", "Adams", "123456789",
                    "Warsaw", "Mock Street", "00-000");

            Offer offer1 = new Offer();
            offer1.setOfferName("Test offer title");
            offer1.setCity("Warsaw");
            offer1.setBuildingYear(2020);
            offer1.setRoomsNumber(2);
            offer1.setFullPrice(BigDecimal.valueOf(1000000));

            offer1.setUser(user);
            user.getOfferList().add(offer1);

            User persistedUser = entityManager.persistAndFlush(user);
            entityManager.clear();

            User retrievedUser = entityManager.find(User.class, persistedUser.getUserId());

            assertNotNull(retrievedUser, "User should be retrieved from DB");
            assertEquals(1, retrievedUser.getOfferList().size());
            assertEquals("Test offer title", retrievedUser.getOfferList().iterator().next().getOfferName());
        }


    }

}
