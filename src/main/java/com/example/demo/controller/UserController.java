package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/hello")
    public String[] hello(){
        return new String[]{"Hello", "World!"};
    }

    @GetMapping("/user")
    public String get_user(){
        Optional<User> result = userRepository.findById("SWLEE");
        if(result.isPresent()){
            return new String(String.valueOf(result.get()));
        }
        return new String("no result");
    }
}
