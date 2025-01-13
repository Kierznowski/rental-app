package com.kierznowski.rentalApp.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

public class ImageTest {

    @Nested
    @DisplayName("Unit Tests for Image entity")
    class ImageUnitTests {

        @Test
        @DisplayName("Testing default constructor and setters")
        void testDefaultConstructorAndSetters() {
            Image image = new Image();
            image.setId(1L);
            image.setName("example.png");
            image.setLocation("/images");
            image.setContent(new byte[]{0, 1, 2, 3});

            assertAll("Verify default constructor",
                    () -> assertEquals(1L, image.getId()),
                    () -> assertEquals("example.png", image.getName()),
                    () -> assertEquals("/images", image.getLocation()),
                    () -> assertArrayEquals(new byte[]{0, 1, 2, 3}, image.getContent())
            );
        }

        @Test
        @DisplayName("Testing parametrized constructor")
        void testParametrizedConstructor() {
            Image image = new Image("example.png", "/images");

            assertAll("Verify parametrized constructor",
                    () -> assertNull(image.getId(), "ID should be null by default"),
                    () -> assertEquals("example.png", image.getName()),
                    () -> assertEquals("/images", image.getLocation())
            );
        }

        @Test
        @DisplayName("Testing equality")
        void testEqualsAndHashCode() {
            Image image1 = new Image(1L);
            Image image2 = new Image(1L);
            Image image3 = new Image(2L);

            assertAll("Verify equals and hashCode methods",
                    () -> assertEquals(image1, image2, "Images with the same ID should be equal"),
                    () -> assertNotEquals(image1, image3, "Images with different ID should not be equals"),
                    () -> assertEquals(image1.hashCode(), image2.hashCode(), "Hash codes should be the same for equals images")
            );
        }
    }

    @Nested
    @DisplayName("JPA Tests for Image entity")
    @DataJpaTest
    class ImageJpaTests {

        @Autowired
        private TestEntityManager entityManager;

        @Test
        @DisplayName("Persist and Retrieve Image")
        void testPersistAndRetrieveImage() {
            Image image = new Image("example.png", "/images");
            image.setContent(new byte[]{1, 2, 3});
            entityManager.persistAndFlush(image);

            Image retrievedImage = entityManager.find(Image.class, image.getId());
            assertAll("Verify field in persisted and retrieved Image",
                    () -> assertEquals(image.getId(), retrievedImage.getId()),
                    () -> assertEquals("example.png", retrievedImage.getName()),
                    () -> assertEquals("/images", retrievedImage.getLocation()),
                    () -> assertArrayEquals(new byte[] {1, 2, 3}, retrievedImage.getContent())
            );
        }

    }
}
