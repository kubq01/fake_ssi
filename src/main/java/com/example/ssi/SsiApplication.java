package com.example.ssi;

import com.example.ssi.auth.AuthenticationService;
import com.example.ssi.auth.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.example.ssi.user.Role.ADMIN;
import static com.example.ssi.user.Role.USER;

@SpringBootApplication
public class SsiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsiApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            AuthenticationService service
    ) {
        return args -> {
            var admin = RegisterRequest.builder()
                    .firstname("Admin")
                    .lastname("Admin")
                    .email("admin@mail.com")
                    .password("password")
                    .role(ADMIN)
                    .build();
            String adminToken = service.register(admin).getAccessToken();
            System.out.println("Admin token: " + adminToken);

            var manager = RegisterRequest.builder()
                    .firstname("User")
                    .lastname("User")
                    .email("user@mail.com")
                    .password("password")
                    .role(USER)
                    .build();
            String userToken = service.register(manager).getAccessToken();
            System.out.println("User token: " + userToken);
        };
    }

}
