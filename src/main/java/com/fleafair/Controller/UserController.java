package com.fleafair.Controller;

import com.fleafair.Common.Result;
import com.fleafair.Entity.User;
import com.fleafair.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result<User> login(@RequestParam String username, @RequestParam String password) {
        User user = userService.findByUsername(username);
        if (user == null) {
            return Result.error("用户不存在");
        }
        if (!userService.checkPassword(user, password)) {
            return Result.error("密码错误");
        }
        return Result.success(user); // 登录成功返回用户信息
    }
}