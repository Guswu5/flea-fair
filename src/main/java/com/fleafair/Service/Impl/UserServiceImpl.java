package com.fleafair.Service.Impl;

import com.fleafair.Common.Result;
import com.fleafair.DTO.UserRegisterDTO;
import com.fleafair.DTO.UserUpdateDTO;
import com.fleafair.Entity.User;
import com.fleafair.Mapper.UserMapper;
import com.fleafair.Service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 根据用户名查询用户
     * @param id
     * @return
     */
    @Override
    public User findByUserId(Long id) {
        return userMapper.findByUserId(id);
    }

    @Override
    public boolean checkPassword(User user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }

    /**
     *  用户注册
     * @param registerDTO
     * @return
     */
    @Override
    public Result<?> register(UserRegisterDTO registerDTO) {
        User user = new User();
        BeanUtils.copyProperties(registerDTO, user);

        //密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setStatus(1);
        //创建用户
        userMapper.insert(user);

        return Result.success();
    }


    /**
     * 更新用户信息
     * @param updateDTO
     * @return
     */
    @Override
    public Result<?> update(UserUpdateDTO updateDTO) {
        // 1. 查询数据库中的用户
        User oldUser = userMapper.findByUserId(updateDTO.getId());
        if (oldUser == null) {
            return Result.error("用户不存在");
        }
        // 2. 校验原密码
        if (!passwordEncoder.matches(updateDTO.getCurrentPassword(), oldUser.getPassword())) {
            return Result.error("原密码错误");
        }
        // 3. 更新字段
        if (updateDTO.getNewPassword() != null) {
            oldUser.setPassword(passwordEncoder.encode(updateDTO.getNewPassword()));
        }
        if (updateDTO.getEmail() != null) {
            oldUser.setEmail(updateDTO.getEmail());
        }
        if (updateDTO.getPhone() != null) {
            oldUser.setPhone(updateDTO.getPhone());
        }
        // 4. 更新数据库
        userMapper.update(oldUser);
        return Result.success("修改成功");
    }
}