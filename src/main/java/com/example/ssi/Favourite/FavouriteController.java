package com.example.ssi.Favourite;

import com.example.ssi.category.CategoryDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/favourite")
public class FavouriteController extends HttpServlet {

    @Autowired
    FavouriteRepo repo;

    @GetMapping
    public FavouriteDTO getById(@RequestParam Long id){
        return repo.findById(id).orElseThrow();
    }
}