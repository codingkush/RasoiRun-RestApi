package com.kushg.rasoiRun.controller;

import com.kushg.rasoiRun.io.UserRequest;
import com.kushg.rasoiRun.io.UserResponse;
import com.kushg.rasoiRun.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@RequestBody UserRequest request){
       return userService.registerUser(request);
    }
}
