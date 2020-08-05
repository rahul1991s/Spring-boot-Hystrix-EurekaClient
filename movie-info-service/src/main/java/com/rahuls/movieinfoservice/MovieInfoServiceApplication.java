package com.rahuls.movieinfoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


/**
 * @author Rahul S
 *
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class MovieInfoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieInfoServiceApplication.class, args);
	}

	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		//this will wait for 3 seconds and if call for this rest template is not served it will end the resource.
		/*HttpComponentsClientHttpRequestFactory c= new HttpComponentsClientHttpRequestFactory();
		 c.setConnectTimeout(3000);
		 return new RestTemplate(c);*/
		 return new RestTemplate();
	}
}

