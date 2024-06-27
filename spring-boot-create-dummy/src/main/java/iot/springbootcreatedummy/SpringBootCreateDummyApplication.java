package iot.springbootcreatedummy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootCreateDummyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCreateDummyApplication.class, args);
	}

}
