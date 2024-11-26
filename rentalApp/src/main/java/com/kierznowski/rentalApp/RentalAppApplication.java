package com.kierznowski.rentalApp;

import com.kierznowski.rentalApp.models.Offer;
import com.kierznowski.rentalApp.models.User;
import com.kierznowski.rentalApp.repositories.OfferRepository;
import com.kierznowski.rentalApp.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;

@SpringBootApplication
public class RentalAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentalAppApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(UserRepository userRepository, OfferRepository offerRepository,
										PasswordEncoder passwordEncoder) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {


				Offer testOffer = new Offer();
				testOffer.setId(1L);
				testOffer.setOfferName("Mieszkanie AuraSky - super komunikacja, wysoki standard");
				testOffer.setCity("Warszawa");
				testOffer.setDistrict("Praga-poludnie");
				testOffer.setStreet("Ostrobramska");
				testOffer.setZip("04-187");
				testOffer.setBuildingYear(2017);
				testOffer.setFullPrice(new BigDecimal(3000));
				testOffer.setBasePrice(new BigDecimal(2000));
				testOffer.setAdditionalPrice(new BigDecimal(1000));
				testOffer.setArea(45);
				testOffer.setRoomsNumber(2);
				testOffer.setEstateLevel(8);
				testOffer.setGarage(false);
				testOffer.setAnnexKitchen(true);
				testOffer.setElevator(true);
				testOffer.setAnimals(false);
				offerRepository.save(testOffer);

				Offer testOffer2 = new Offer();
				testOffer2.setId(2L);
				testOffer2.setOfferName("Wola - blisko centrum, zwierzęta mile widziane");
				testOffer2.setCity("Warszawa");
				testOffer2.setDistrict("Wola");
				testOffer2.setStreet("Wolska");
				testOffer2.setZip("03-000");
				testOffer2.setBuildingYear(2014);
				testOffer2.setFullPrice(new BigDecimal(4500));
				testOffer2.setBasePrice(new BigDecimal(3500));
				testOffer2.setAdditionalPrice(new BigDecimal(1000));
				testOffer2.setArea(50);
				testOffer2.setRoomsNumber(3);
				testOffer2.setEstateLevel(5);
				testOffer2.setGarage(false);
				testOffer2.setAnnexKitchen(true);
				testOffer2.setElevator(true);
				testOffer2.setAnimals(true);
				offerRepository.save(testOffer2);

				Offer testOffer3 = new Offer();
				testOffer3.setId(3L);
				testOffer3.setOfferName("Piękne mieszkanie na bemowie w nowym budownictwie");
				testOffer3.setCity("Warszawa");
				testOffer3.setDistrict("Bemowo");
				testOffer3.setStreet("Bemowska");
				testOffer3.setZip("01-230");
				testOffer3.setBuildingYear(2023);
				testOffer3.setFullPrice(new BigDecimal(3800));
				testOffer3.setBasePrice(new BigDecimal(3500));
				testOffer3.setAdditionalPrice(new BigDecimal(300));
				testOffer3.setArea(43);
				testOffer3.setRoomsNumber(2);
				testOffer3.setEstateLevel(2);
				testOffer3.setGarage(true);
				testOffer3.setAnnexKitchen(true);
				testOffer3.setElevator(true);
				testOffer3.setAnimals(false);
				offerRepository.save(testOffer3);
			}

		};

	}
}
