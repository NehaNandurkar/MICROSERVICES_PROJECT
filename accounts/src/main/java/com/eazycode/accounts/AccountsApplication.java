package com.eazycode.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef="auditAwareImpl") //Passing the name of my bean 
//Switch on the auto-tracking feature for created/updated fields in entities.
//@OpenAPIDefinition Provides metadata for Swagger UI (title, description, version, contact, license) common to all API 
@OpenAPIDefinition(
		info=@Info(
				title="Accounts microservice REST API documentation",
				description="EazyCode Accounts microservices REST API Documentation",
				version="1.0.0",
				contact=@Contact(
						name="Alex Jorden",
						email="eazycode@email.com",
						url="https://www.eazycode.com"
						),
				license=@License(
						name="Apache 2.0",
						url="https://www.eazycode.com"
						)),
		externalDocs=@ExternalDocumentation(
				description="EazyCode Accounts microservice REST API Documentation",
				url="http://www.eazycode.com/accounts/swagger-ui/index.html"
				)
		)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
