package com.example.ssi;

import com.example.ssi.Favourite.FavouriteDTO;
import com.example.ssi.Favourite.FavouriteRepo;
import com.example.ssi.Product.ProductDTO;
import com.example.ssi.Product.ProductRepo;
import com.example.ssi.auth.AuthenticationService;
import com.example.ssi.auth.RegisterRequest;
import com.example.ssi.user.User;
import com.example.ssi.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.example.ssi.user.Role.ADMIN;
import static com.example.ssi.user.Role.USER;

@SpringBootApplication
public class SsiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsiApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            AuthenticationService service,
            ProductRepo productRepo,
            FavouriteRepo favouriteRepo,
            PasswordEncoder passwordEncoder,
            UserRepository userRepo
    ) {
        return args -> {
            ProductDTO prod1 = ProductDTO.builder()
                    .categoryId(1L)
                    .name("prod1")
                    .price(100)
                    .rating(5.0)
                    .build();
            ProductDTO prod2 = ProductDTO.builder()
                    .categoryId(1L)
                    .name("prod2")
                    .price(100)
                    .rating(3.0)
                    .build();
            ProductDTO prod3 = ProductDTO.builder()
                    .categoryId(1L)
                    .name("prod3")
                    .price(100)
                    .rating(4.0)
                    .build();
            productRepo.save(prod1);
            productRepo.save(prod2);
            productRepo.save(prod3);
            FavouriteDTO fav1 = FavouriteDTO.builder()
                    .userId(2L)
                    .productId(1L)
                    .build();
            FavouriteDTO fav2 = FavouriteDTO.builder()
                    .userId(2L)
                    .productId(2L)
                    .build();
            FavouriteDTO fav3 = FavouriteDTO.builder()
                    .userId(2L)
                    .productId(3L)
                    .build();
            favouriteRepo.save(fav1);
            favouriteRepo.save(fav2);
            favouriteRepo.save(fav3);
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
            User user = User
                    .builder()
                    .firstName("User1")
                    .lastName("User1")
                    .email("user1@mail.com")
                    .password(passwordEncoder.encode("password"))
                    .role(USER)
                    .isUserBlocked(false)
                    .favourites(List.of(fav1,fav2))
                    .build();
            userRepo.save(user);
            String userToken = service.register(manager).getAccessToken();
            System.out.println("User token: " + userToken);
        };
    }

}
