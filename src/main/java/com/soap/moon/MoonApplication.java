package com.soap.moon;

import java.util.Optional;
import java.util.UUID;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableAspectJAutoProxy
public class MoonApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoonApplication.class, args);
	}

//	@Bean
//	public AuditorAware<String> auditorProvider(){
//		return () -> Optional.of(UUID.randomUUID().toString());
//	}

}
