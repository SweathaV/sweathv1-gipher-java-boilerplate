package com.stackroute.giphermanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class GipherManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GipherManagerApplication.class, args);
	}

/*	@Bean
	public FilterRegistrationBean jwtFilter() {
		FilterRegistrationBean filterBean = new FilterRegistrationBean<>();
		filterBean.setFilter(new JwtFilter());
		filterBean.addUrlPatterns("/api/*");
		return filterBean;
	}
	*/
}
