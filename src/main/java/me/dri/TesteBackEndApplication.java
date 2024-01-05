package me.dri;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TesteBackEndApplication {
	public static void main(String[] args) {
		System.out.println(System.getenv("PASSWORD_MONGO_DB_ATLAS"));
		SpringApplication.run(TesteBackEndApplication.class, args);
	}

}
