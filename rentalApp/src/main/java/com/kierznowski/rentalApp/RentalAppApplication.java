package com.kierznowski.rentalApp;

import com.kierznowski.rentalApp.models.Offer;
import com.kierznowski.rentalApp.repositories.OfferRepository;
import com.kierznowski.rentalApp.repositories.UserRepository;
import com.kierznowski.rentalApp.services.FileLocationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Function;

@SpringBootApplication
public class RentalAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentalAppApplication.class, args);
	}

	/*@Bean
	public CommandLineRunner dataLoader(UserRepository userRepository, OfferRepository offerRepository,
										PasswordEncoder passwordEncoder, FileLocationService fileLocationService) {

		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {

				String image1Path = "src/main/resources/static/mock-images/image1.jpg";
				String image2Path = "src/main/resources/static/mock-images/image2.jpg";
				String image3Path = "src/main/resources/static/mock-images/image3.jpg";
				String image4Path = "src/main/resources/static/mock-images/image4.jpg";
				String image5Path = "src/main/resources/static/mock-images/image5.jpg";
				String image6Path = "src/main/resources/static/mock-images/image6.jpg";
				String image7Path = "src/main/resources/static/mock-images/image7.jpg";
				String image8Path = "src/main/resources/static/mock-images/image8.jpg";
				String image9Path = "src/main/resources/static/mock-images/image9.jpg";
				String image10Path = "src/main/resources/static/mock-images/image10.jpg";
				String image11Path = "src/main/resources/static/mock-images/image11.jpg";
				String image12Path = "src/main/resources/static/mock-images/image12.jpg";
				Function<String, Long> uploadImage = (filePath) -> {
					try {
						byte[] content = Files.readAllBytes(Paths.get(filePath));
						String imageName = Paths.get(filePath).getFileName().toString();
						return fileLocationService.save(content, imageName);
					} catch(Exception e) {
						e.printStackTrace();
						return null;
					}
				};

				Long image1Id = uploadImage.apply(image1Path);
				Long image2Id = uploadImage.apply(image2Path);
				Long image3Id = uploadImage.apply(image3Path);
				Long image4Id = uploadImage.apply(image4Path);
				Long image5Id = uploadImage.apply(image5Path);
				Long image6Id = uploadImage.apply(image6Path);
				Long image7Id = uploadImage.apply(image7Path);
				Long image8Id = uploadImage.apply(image8Path);
				Long image9Id = uploadImage.apply(image9Path);
				Long image10Id = uploadImage.apply(image10Path);
				Long image11Id = uploadImage.apply(image11Path);
				Long image12Id = uploadImage.apply(image12Path);

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

				testOffer.getImageIds().add(image1Id);
				testOffer.getImageIds().add(image4Id);
				testOffer.getImageIds().add(image7Id);
				testOffer.getImageIds().add(image10Id);
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

				testOffer2.getImageIds().add(image2Id);
				testOffer2.getImageIds().add(image5Id);
				testOffer2.getImageIds().add(image8Id);
				testOffer2.getImageIds().add(image11Id);
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

				testOffer3.getImageIds().add(image3Id);
				testOffer3.getImageIds().add(image6Id);
				testOffer3.getImageIds().add(image9Id);
				testOffer3.getImageIds().add(image12Id);
				offerRepository.save(testOffer3);
			}

		};

	}*/
}
