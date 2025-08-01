package com.fleafair.Controller;

import com.fleafair.Common.Result;
import com.fleafair.Config.JwtUtil;
import com.fleafair.DTO.LoginDTO;
import com.fleafair.DTO.UserRegisterDTO;
import com.fleafair.DTO.UserUpdateDTO;
import com.fleafair.Entity.User;
import com.fleafair.Service.UserService;
import com.fleafair.VO.UserVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
@Tag(name = "用户接口")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param loginDTO
     * @return
     */
    @PostMapping("/login")
    @Operation(summary = "用户登录")
    public Result<?> login(@RequestBody LoginDTO loginDTO) {
        log.info("用户登录：{}", loginDTO);
        return userService.loginUser(loginDTO); // 登录成功返回用户信息
    }

    /**
     * 用户注册
     * @param registerDTO
     * @return
     */
    @PostMapping("/register")
    @Operation(summary = "用户注册")
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
    @Operation(summary = "修改用户信息")
    public Result<?> update(@RequestBody UserUpdateDTO updateDTO) {
        log.info("修改用户信息,{}", updateDTO);
        return userService.update(updateDTO);
    }

    /**
     * 获取用户信息
     * @param userid
     * @return
     */
    @GetMapping("/me")
    @Operation(summary = "获取当前用户信息")
    public Result<?> me(@AuthenticationPrincipal Long userid) {

        UserVO user = userService.findById(userid);

        log.info("用户信息：{}", user);
        return Result.success(user);
    }
}