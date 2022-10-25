package tn.esprit.shopgular;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.security.servlet.*;
import org.springframework.scheduling.annotation.*;
@EnableScheduling
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ShopgularApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopgularApplication.class, args);
	}
}
