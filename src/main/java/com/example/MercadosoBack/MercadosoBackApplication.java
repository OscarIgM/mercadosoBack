package com.example.MercadosoBack;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
@EntityScan(basePackages = "com.example.MercadosoBack.models")

@SpringBootApplication
public class MercadosoBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(MercadosoBackApplication.class, args);
	}


}
