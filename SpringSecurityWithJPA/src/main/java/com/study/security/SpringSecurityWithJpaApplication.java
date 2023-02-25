package com.study.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.study.security.repository.UserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class SpringSecurityWithJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityWithJpaApplication.class, args);
	}

}
