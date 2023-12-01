package com.example.ssi.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class  ProductDTO {
    @Id
    @GeneratedValue
    private Long id;
    private Long categoryId;
    private String name;
    private double price;
    private double rating;
}
