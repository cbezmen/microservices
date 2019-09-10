package com.cbezmen.microservice.address;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "com.cbezmen")
@EnableDiscoveryClient
public class AddressApplication {

    public static void main(String[] args) {
        SpringApplication.run(AddressApplication.class, args);
    }

}
