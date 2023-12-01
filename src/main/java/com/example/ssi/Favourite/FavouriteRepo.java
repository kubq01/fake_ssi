package com.example.ssi.Favourite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouriteRepo extends JpaRepository<FavouriteDTO, Long> {
}
