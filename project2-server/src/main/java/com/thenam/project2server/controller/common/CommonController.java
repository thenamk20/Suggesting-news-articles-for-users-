package com.thenam.project2server.controller.common;

import com.thenam.project2server.entity.UserEntity;
import com.thenam.project2server.services.common.LogInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class CommonController {

    @Autowired
    LogInService logInService;

    @PostMapping("/login")
    public String logIn(
            @RequestParam("userName") String userName,
            @RequestParam("password") String password
    ){
        int responseCode = logInService.handleLogIn(userName,password);
        return Integer.toString(responseCode);
    }

}
