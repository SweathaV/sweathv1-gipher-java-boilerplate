package com.stackroute.netflixzuulapigatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
//import brave.sampler.Sampler; // Zookeeper jar

import com.stackroute.netflixzuulapigatewayserver.filter.JwtFilter;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class NetflixZuulApiGatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixZuulApiGatewayServerApplication.class, args);
	}
	
	/*
	 * @Bean public Sampler defaultSampler() {
	 * 
	 * return Sampler.ALWAYS_SAMPLE; }
	 */
	
	
	  @Bean 
	 public CorsFilter corsFilter() { final UrlBasedCorsConfigurationSource
	  source = new UrlBasedCorsConfigurationSource(); final CorsConfiguration
	  config = new CorsConfiguration(); config.setAllowCredentials(true);
	  config.addAllowedOrigin("*"); config.addAllowedHeader("*");
	  config.addAllowedMethod("OPTIONS"); config.addAllowedMethod("HEAD");
	  config.addAllowedMethod("GET"); config.addAllowedMethod("PUT");
	  config.addAllowedMethod("POST"); config.addAllowedMethod("DELETE");
	  config.addAllowedMethod("PATCH");
	  source.registerCorsConfiguration("/**",config); return new
	  CorsFilter(source); }
	  
	  @SuppressWarnings({ "rawtypes", "unchecked" })	  
	  @Bean 
	  public FilterRegistrationBean jwtFilter() { FilterRegistrationBean
	  registrationBean = new FilterRegistrationBean();
	  registrationBean.setFilter(new JwtFilter());
	  registrationBean.addUrlPatterns("/api/v1/auth/*");
	  registrationBean.addUrlPatterns("/api/v1/auth/gipher/*");
	  registrationBean.addUrlPatterns("/api/v1/gipherrecommendersystem/recommend/*");
	  return registrationBean; 
	  }
	 

}
