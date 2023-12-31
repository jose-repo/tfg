package com.udima.tfg.controler;

import com.udima.tfg.entitiy.UserEntity;

import com.udima.tfg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserAuthenticationController {

  @Autowired
  private UserRepository userRepository;

  @PostMapping(
      value = "/authenticate",
      consumes = "application/json",
      produces = "application/json")
  public ResponseEntity<UserEntity> authentication(@RequestBody UserEntity userEntity) {
    return ResponseEntity.ok(
        userRepository
            .findByUsername(userEntity.getUsername(), userEntity.getPassword())
            .orElse(new UserEntity()));
    }
}
