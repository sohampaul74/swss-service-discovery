package org.swss.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableEurekaServer
public class ServiceDiscoveryApplication  {

	public static void main(String[] args) {
		ConfigurableApplicationContext cac = SpringApplication.run(ServiceDiscoveryApplication.class, args);
	}
}
