package com.fleafair.Mapper;

import com.fleafair.Entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    /**
     * 用户查询
     * @param id
     * @return
     */
    @Select("SELECT * FROM user WHERE userId = #{userId}")
    User findById(Long id);

    /**
     * 用户注册
     * @param user
     */
    @Insert("INSERT INTO user (username, password, email, phone, status) VALUES (#{username}, #{password}, #{email}, #{phone}, #{status})")
    void insert(User user);

    /**
     * 用户更新
     * @param user
     */
    @Update("UPDATE user SET username = #{username}, password = #{password}, email = #{email}, phone = #{phone}, status = #{status} WHERE id = #{id}")
    void update(User user);

    /**
     * 通过手机号查询用户
     * @param phone
     * @return
     */
    @Select("SELECT * FROM user WHERE phone = #{phone}")
    User findByPhone(String phone);

    /**
     * 通过id或手机号查询用户
     * @param userId
     * @param phone
     * @return
     */
    User findBYIdOrPhone(Long userId, String phone);
}