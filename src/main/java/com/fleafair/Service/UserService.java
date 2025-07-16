package com.fleafair.Service;

import com.fleafair.Common.Result;
import com.fleafair.DTO.UserRegisterDTO;
import com.fleafair.DTO.UserUpdateDTO;
import com.fleafair.Entity.User;

public interface UserService {
    /**
     * 根据用户名查询用户
     * @param id
     * @return
     */
    User findByUserId(Long id);
    boolean checkPassword(User user, String rawPassword);


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
}