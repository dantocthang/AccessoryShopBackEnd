package com.nhom9.springjwt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nhom9.springjwt.models.User;
import com.nhom9.springjwt.repository.AddressRepository;
import com.nhom9.springjwt.repository.UserRepository;

@CrossOrigin(origins = "http://127.0.0.1:5173", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    AddressRepository addressRepo;
    @Autowired
    UserRepository userRepo;

    @GetMapping("/{user_id}")
    public ResponseEntity<User> getAllProducts(@RequestParam long user_id) {
        try {
            User user = userRepo.findById(user_id).orElseThrow(
                    () -> new InvalidConfigurationPropertyValueException("User id", user_id, "Not found"));

            return new ResponseEntity<>(user, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @PostMapping("/{user_id}")
    // public ResponseEntity<User> updateProfile(@RequestParam long user_id){

    // }
}
