package com.rapipay.otp_handler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@EnableAutoConfiguration
@AutoConfigurationPackage
@SpringBootApplication
public class OtpHandlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OtpHandlerApplication.class, args);
	}

}
