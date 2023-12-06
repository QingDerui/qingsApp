package com.example.springboottest.data.mapper.db1;

import com.example.springboottest.data.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

public interface UserTkMapper extends Mapper<User>, MySqlMapper<User> {

    @Select("select * from user")
    List<User> getAllUsers();

    @Select("select username from user where username = #{name}")
    String checkUser(@Param("name")String name);

    @Select("select username from user where token = #{token}")
    String checkUserByToken(@Param("token")String token);

    User selectUser(@Param("name")String name, @Param("password")String password);
}
