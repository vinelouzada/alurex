package br.com.alura.alurex.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AlurexApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlurexApplication.class, args);
	}

}
