package com.example.ssi.Favourite;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FavouriteDTO {
    @Id
    @GeneratedValue
    Long id;
    private Long userId;
    private Long productId;
}
