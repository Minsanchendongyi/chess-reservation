package com.chess.service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chess.entity.User;
import com.chess.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public User login(String phone, String password) {
        return userMapper.login(phone, password);
    }
    public boolean register(User user) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getPhone, user.getPhone());
        if (userMapper.selectCount(wrapper) > 0) return false;
        user.setLevel("普通会员");
        return userMapper.insert(user) > 0;
    }
}