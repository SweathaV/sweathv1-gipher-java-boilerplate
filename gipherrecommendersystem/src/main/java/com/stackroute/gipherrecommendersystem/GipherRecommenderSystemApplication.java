package com.stackroute.gipherrecommendersystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GipherRecommenderSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(GipherRecommenderSystemApplication.class, args);
	}

}


