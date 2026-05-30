package com.backend.suspectservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan({"com.backend.suspectservice","com.backend.commonservice"})
public class SuspectServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(SuspectServiceApplication.class, args);
	}

}
