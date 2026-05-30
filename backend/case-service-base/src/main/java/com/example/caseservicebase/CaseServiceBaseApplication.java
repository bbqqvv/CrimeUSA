package com.example.caseservicebase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CaseServiceBaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaseServiceBaseApplication.class, args);
	}

}
