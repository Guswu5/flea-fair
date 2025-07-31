package com.fleafair.Service;

import com.fleafair.Common.Result;
import com.fleafair.DTO.LoginDTO;
import com.fleafair.DTO.UserRegisterDTO;
import com.fleafair.DTO.UserUpdateDTO;
import com.fleafair.Entity.User;
import com.fleafair.VO.UserVO;

public interface UserService {
    /**
     * 用户登录
     * @param loginDTO
     * @return
     */
    Result<?> loginUser(LoginDTO loginDTO);

    /**
     *  用户注册
     * @param registerDTO
     */
    Result<?> register(UserRegisterDTO registerDTO);

    /**
     * 修改用户信息
     * @param updateDTO
     * @return
     */
    Result<?> update(UserUpdateDTO updateDTO);

    /**
     * 根据用户id查询用户
     * @param userId
     * @return
     */
    UserVO findById(Long userId);
}