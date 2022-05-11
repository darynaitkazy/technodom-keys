package com.example.technodomkeys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class TechnodomKeysApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechnodomKeysApplication.class, args);
	}

}
