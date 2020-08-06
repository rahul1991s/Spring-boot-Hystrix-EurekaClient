package com.rahuls.springconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author Rahul S
 *
 */
@SpringBootApplication
@EnableConfigServer
public class SpringCloudConfiguration {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConfiguration.class, args);
		
	}
}
