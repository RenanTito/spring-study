package br.com.project.springmicroserviceproject;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringMicroserviceProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMicroserviceProjectApplication.class, args);
	}

	@Bean 
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			System.out.print("Lets inspect the beans provided by Spring Boot"); 
			String[] beanNames = ctx.getBeanDefinitionNames(); 
			Arrays.sort(beanNames); 
			for(String beanName : beanNames) {
				System.out.print("BEAN-NAME: ".concat(beanName).concat("\n"));
			} 
		}; 
	}

}
