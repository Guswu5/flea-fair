package com.fleafair.Controller;

import com.fleafair.Common.Result;
import com.fleafair.DTO.UserRegisterDTO;
import com.fleafair.DTO.UserUpdateDTO;
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

    /**
     * 用户登录
     * @param id
     * @param password
     * @return
     */
    @PostMapping("/login")
    public Result<User> login(@RequestParam Long id, @RequestParam String password) {
        User user = userService.findByUserId(id);
        if (user == null) {
            return Result.error("用户不存在");
        }
        if (!userService.checkPassword(user, password)) {
            return Result.error("密码错误");
        }
        log.info("用户登录成功：{}", user);
        return Result.success(user); // 登录成功返回用户信息
    }

    /**
     * 用户注册
     * @param registerDTO
     * @return
     */
    @PostMapping("/register")
    public Result<?> register(@RequestBody UserRegisterDTO registerDTO) {
        log.info("用户注册,{}", registerDTO);

        return userService.register(registerDTO);
    }

    /**
     * 修改用户信息
     * @param updateDTO
     * @return
     */
    @PostMapping("/update")
    public Result<?> update(@RequestBody UserUpdateDTO updateDTO) {
        log.info("修改用户信息,{}", updateDTO);
        return userService.update(updateDTO);
    }
}