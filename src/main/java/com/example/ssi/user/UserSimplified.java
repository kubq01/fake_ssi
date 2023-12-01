package com.example.ssi.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSimplified {
    private String firstName;
    private String lastName;
    private String email;
}
