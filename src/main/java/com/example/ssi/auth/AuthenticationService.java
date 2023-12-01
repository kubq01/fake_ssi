package com.example.ssi.auth;

import com.example.ssi.user.User;
import com.example.ssi.user.UserRepository;
import com.example.ssi.user.UsernameTakenException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@AllArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) throws UsernameTakenException {
    if(repository.findByEmail(request.getEmail()).isPresent())
      throw new UsernameTakenException("Email is already being used by another account");
    var user = User.builder()
        .firstName(request.getFirstname())
        .lastName(request.getLastname())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(request.getRole())
        .build();
    var savedUser = repository.save(user);
    var jwtToken = jwtService.generateToken(new HashMap<>(), user);
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
        .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    User user = repository.findByEmail(request.getEmail())
        .orElseThrow();
    String jwtToken = jwtService.generateToken(new HashMap<>(), user);
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
        .build();
  }
}
