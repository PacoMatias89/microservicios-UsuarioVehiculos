package com.pacomolina;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CocheServicioApplication {

	public static void main(String[] args) {
		SpringApplication.run(CocheServicioApplication.class, args);
	}

}
