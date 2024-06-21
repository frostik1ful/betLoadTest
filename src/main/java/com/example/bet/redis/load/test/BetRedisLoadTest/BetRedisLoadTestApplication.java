package com.example.bet.redis.load.test.BetRedisLoadTest;

import com.example.bet.redis.load.test.BetRedisLoadTest.service.DataService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BetRedisLoadTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(BetRedisLoadTestApplication.class, args);
	}
	@Bean
	CommandLineRunner run(DataService dataService) {
		return args -> {
			dataService.addDefaultData();
		};
	}
}
