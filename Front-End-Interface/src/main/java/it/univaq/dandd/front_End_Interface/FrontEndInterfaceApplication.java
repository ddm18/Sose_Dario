package it.univaq.dandd.front_End_Interface;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FrontEndInterfaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrontEndInterfaceApplication.class, args);
	}

}
