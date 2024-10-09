package com.kierznowski.rentalApp.authServer;

import com.kierznowski.rentalApp.authServer.repositories.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.kierznowski.rentalApp.authServer.model.User;

@ComponentScan("com.kierznowski.rentalApp.authServer")
@SpringBootApplication
public class AuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServerApplication.class, args);
	}

	@Bean
	public ApplicationRunner dataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			userRepository.save(new User("test@test.com", passwordEncoder.encode("test"), "ROLE_USER"));
			userRepository.save(new User("admin", passwordEncoder.encode("admin"), "ROLE_ADMIN"));
		};
	}
}
