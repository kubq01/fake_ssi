package com.example.ssi.user;

import com.example.ssi.Favourite.FavouriteDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSimplified {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private LocalDate dateOfBirth;
    private boolean isUserBlocked;
    List<FavouriteDTO> favourites;
}
