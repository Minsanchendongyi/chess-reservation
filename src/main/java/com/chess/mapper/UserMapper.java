package com.chess.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chess.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM user WHERE phone = #{phone} AND password = #{password}")
    User login(@Param("phone") String phone, @Param("password") String password);
}