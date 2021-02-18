package com.login;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.login.entities.AppRole;
import com.login.services.AccountServices;

@SpringBootApplication
public class LoginServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginServicesApplication.class, args);
	}
	@Bean
	CommandLineRunner start(AccountServices accountServices) {
		return args -> {
			accountServices.save(new AppRole(null,"USER"));
			accountServices.save(new AppRole(null,"ADMIN"));
			Stream.of("user1", "user2","user3","admin").forEach(un -> {
				accountServices.saveUser(un, "1234","1234");
			});
			accountServices.addRoleToUser("admin","ADMIN");
			
			
			 
	};

}
	@Bean
     BCryptPasswordEncoder getBCE() {
    	 return new BCryptPasswordEncoder();
     }
	
	
}
