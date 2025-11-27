package com.sysManagemet.servientrega;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServientregaApplication {

	public static void main(String[] args) {

		// Necesario para que Swing funcione en Spring Boot
		System.setProperty("java.awt.headless", "false");

		// Arranca Spring
		SpringApplication.run(ServientregaApplication.class, args);
	}
}






