package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.LoginDTO;
import com.example.demo.DTO.SignUpDTO;
import com.example.demo.entities.User;

@Service
public class AuthService {
    


    @Autowired
    private UserService userService;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;


    public ResponseEntity<?> signUp(SignUpDTO signUpDTO){

         try {
        // Convert DTO to User entity
        User user = new User();
        user.setName(signUpDTO.getName());
        user.setEmail(signUpDTO.getEmail());
        user.setPassword(signUpDTO.getPassword());

        // Call service method to register user (encodes password internally)
        User registeredUser = userService.registerUser(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body("User registered successfully with username: " + registeredUser.getName());
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body("Error: " + e.getMessage());
    }
    }


  public String login(LoginDTO loginDTO) {
   Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);

    return jwtService.generateToken(authentication.getName()); 
}

}
