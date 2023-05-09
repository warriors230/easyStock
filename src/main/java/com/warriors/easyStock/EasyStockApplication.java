package com.warriors.easyStock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class EasyStockApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyStockApplication.class, args);

		System.out.println(">>>>>>>>>>>>>>>>>>>>> "+new BCryptPasswordEncoder().encode("warriors230"));
	}

}
