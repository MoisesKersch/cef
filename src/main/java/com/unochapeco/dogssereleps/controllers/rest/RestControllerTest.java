package com.unochapeco.dogssereleps.controllers.rest;

import com.unochapeco.dogssereleps.models.User;
import com.unochapeco.dogssereleps.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(value = "api")
public class RestControllerTest {

    @Autowired
    private UserService userService;

    @PostMapping
    public User save(@RequestBody  User user) {
        return userService.save(user);
    }

}

