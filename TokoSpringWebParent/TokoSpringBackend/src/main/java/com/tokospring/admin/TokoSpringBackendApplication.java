package com.tokospring.admin;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan({"com.tokospring.common.entity", "com.tokospring.admin.user"})
@SpringBootApplication
public class TokoSpringBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TokoSpringBackendApplication.class, args);
	}

}
