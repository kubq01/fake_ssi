package com.example.ssi.Product;


import com.example.ssi.Favourite.FavouriteDTO;
import com.example.ssi.user.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/product")
public class ProductController extends HttpServlet {

    @Autowired
    ProductRepo repo;

    @GetMapping
    public ProductDTO getById(@RequestParam Long id){
        return repo.findById(id).orElseThrow();
    }

    @GetMapping("/all")
    public List<ProductDTO> getAllForUser(){
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        List<ProductDTO> productDTOS = repo.findAll();
        List<ProductDTO> finalProducts = new ArrayList<>();

        for(ProductDTO productDTO: productDTOS){
            boolean add = true;
            for(FavouriteDTO fav: user.getFavourites()){
                if(fav.getProductId() == productDTO.getId())
                    add = false;
            }
            if(add)
                finalProducts.add(productDTO);
        }

        return finalProducts;
    }

    @GetMapping("allall")
    public List<ProductDTO> getAll(){
        return repo.findAll();
    }

}

