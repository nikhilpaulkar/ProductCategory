package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
@Component
@ComponentScan("com")
@SpringBootApplication
public class CategoryProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(CategoryProductApplication.class, args);
	}

}
