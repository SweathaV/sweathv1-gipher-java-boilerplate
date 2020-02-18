package com.stackroute.giphermanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.stackroute.giphermanager.jwtfilter.JwtFilter;

@SpringBootApplication
public class GipherManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GipherManagerApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean jwtFilter() {
		FilterRegistrationBean filterBean = new FilterRegistrationBean<>();
		filterBean.setFilter(new JwtFilter());
		filterBean.addUrlPatterns("/api/*");
		return filterBean;
	}
}
