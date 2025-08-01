package com.fleafair.Service.Impl;

import com.fleafair.Common.Result;
import com.fleafair.Config.JwtUtil;
import com.fleafair.DTO.LoginDTO;
import com.fleafair.DTO.UserRegisterDTO;
import com.fleafair.DTO.UserUpdateDTO;
import com.fleafair.Entity.User;
import com.fleafair.Mapper.UserMapper;
import com.fleafair.Service.UserService;
import com.fleafair.VO.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 用户登录
     * @param loginDTO
     * @return
     */
    @Override
    public Result<?> loginUser(LoginDTO loginDTO) {
        //通过用户id或者手机号查询用户
        User user = userMapper.findBYIdOrPhone(loginDTO.getUserId(), loginDTO.getPhone());

        if (user == null ) {
            return Result.error("账号不存在");
        }
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            return Result.error("密码错误");
        }

        // 登录成功，生成JWT
        String token = JwtUtil.createToken(user.getUserId());
        log.info("生成token：{}", token);

        // 可以只返回token，也可以把用户基本信息和token一起返回
        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("user", user);
        return Result.success(data);
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
        User oldUser = userMapper.findById(updateDTO.getId());
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

    private static final String DEFAULT_AVATAR = "https://gus-image.oss-cn-guangzhou.aliyuncs.com/images/236f9976-5d7c-45b2-971b-e2320ebf200a.jpg";
    /**
     * 通过用户id查询用户
     * @param userId
     * @return
     */
    @Override
    public UserVO findById(Long userId) {
        User user = userMapper.findById(userId);


        //获取用户头像
        if(user.getAvatar() == null){
            user.setAvatar(DEFAULT_AVATAR);
        }
        
        return UserVO.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .avatar(user.getAvatar())
                .build();
    }
}