package com.fleafair.Controller;

import com.fleafair.Service.UserService;
import com.fleafair.DTO.LoginDTO;
import com.fleafair.Common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result<?> login(@RequestBody LoginDTO loginDTO) {
        return userService.loginUser(loginDTO);
    }
}