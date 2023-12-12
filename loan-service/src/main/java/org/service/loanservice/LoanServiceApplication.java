package org.service.loanservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
        info = @Info(
                title = "Loan Service",
                version = "1.0",
                description = "Loan Service API",
                termsOfService = "http://www.example.com/terms",
                contact = @Contact(
                        name = "User Name",
                        email = "XXXXXXXXXXXXXXXXX"
                )
        ),

        servers = {
                @Server(
                        url = "XXXXXXXXXXXXXXXXXXXXX"
                ),
        }
)
public class LoanServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoanServiceApplication.class, args);
    }

}
