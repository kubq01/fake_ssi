package com.example.ssi.Favourite;

import com.example.ssi.category.CategoryDTO;
import com.example.ssi.user.User;
import com.example.ssi.user.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/favourite")
@RequiredArgsConstructor
public class FavouriteController extends HttpServlet {


    private final FavouriteRepo repo;
    private final UserRepository userRepository;

    @GetMapping
    public FavouriteDTO getById(@RequestParam Long id){
        System.out.println(repo.findById(id).orElseThrow());
        return repo.findById(id).orElseThrow();
    }

    @DeleteMapping
    public void deleteFromUser(@RequestParam Long id){
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        FavouriteDTO fav = FavouriteDTO.builder().userId((long) user.getId()).productId(id).build();
        user.deleteFav(fav);
        System.out.println("Gowno w bazie znalezione: " + repo.findById(id).orElseThrow());
        System.out.println("Kurwa DTO wygenerowane: " + fav.toString());
        userRepository.save(user);
    }

    @PostMapping
    public void addFav(@RequestParam Long productId){
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        FavouriteDTO fav = FavouriteDTO.builder().userId((long) user.getId()).productId(productId).build();
        repo.save(fav);
        user.addFav(fav);
        userRepository.save(user);
    }
}