package com.thoughtmechanix.licenses;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.thoughtmechanix.licenses.utils.UserContextInterceptor;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableEurekaClient
public class Application {

	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		RestTemplate template = new RestTemplate();
		List interceptors = template.getInterceptors();
		
		if(interceptors == null){
			template.setInterceptors(Collections.singletonList(new UserContextInterceptor()));
		}else{
			interceptors.add(new UserContextInterceptor());
			template.setInterceptors(interceptors);
		}
		
		return template;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
