package com.example.ssi.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {


   private final UserRepository userRepository;


   private final PasswordEncoder passwordEncoder;

    @GetMapping("/my_profile" )
    public ResponseEntity<UserSimplified> getUser(){
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        System.out.println(user.toString());
        System.out.println(UserSimplifiedMapper.INSTANCE.userToSimplifiedUser(user).toString());
        return ResponseEntity.ok(UserSimplifiedMapper.INSTANCE.userToSimplifiedUser(user));
    }

    @GetMapping("/getNotAdmins")
    public ResponseEntity<List<UserSimplified>> getNotAdmins(){
        return ResponseEntity.ok(userRepository.findAll()
                .stream()
                .filter(user -> user.getRole().equals(Role.USER))
                .map(user -> UserSimplifiedMapper.INSTANCE.userToSimplifiedUser(user))
                .toList());
    }

    @PostMapping
    public void updateUser(@RequestBody UserSimplified userSimplified){
        System.out.println(userSimplified.toString());
        User user = UserSimplifiedMapper.INSTANCE.userSimplifiedToUser(userSimplified);
        user.setUserBlocked(userSimplified.isUserBlocked());
        System.out.println(userRepository.save(user));
    }

    @PostMapping("/password")
    public void setNewPassword(@RequestBody UserSimplified userSimplified){
        System.out.println(userSimplified.toString());
        userSimplified.setPassword(passwordEncoder.encode(userSimplified.getPassword()));
        User user = UserSimplifiedMapper.INSTANCE.userSimplifiedToUser(userSimplified);
        user.setUserBlocked(userSimplified.isUserBlocked());
        System.out.println(userRepository.save(user));
    }
}
