package com.allan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroUserApplication.class, args);
	}

}
