package com.backend.investigationservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan({"com.backend.investigationservice", "com.backend.commonservice"})
public class InvestigationServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(InvestigationServiceApplication.class, args);
	}
}
