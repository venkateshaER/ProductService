package com.hashedin.productService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
//@EnableAutoConfiguration(exclude=SecurityAutoConfiguration.class)
@EnableCircuitBreaker
@EnableConfigServer
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

}
