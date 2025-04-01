package com.study.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
public class Ex12_JpqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ex12_JpqlApplication.class, args);
	}

}
