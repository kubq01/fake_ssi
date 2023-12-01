package com.example.ssi.category;

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
@RequestMapping("/category")
public class CategoryController extends HttpServlet {

    @Autowired
    CategoryRepo repo;

   @GetMapping
    public CategoryDTO getById(@RequestParam Long id){
       return repo.findById(id).orElseThrow();
   }
}
