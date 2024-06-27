package com.iot.springbootcreatereport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootCreateReportApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCreateReportApplication.class, args);
	}

}
