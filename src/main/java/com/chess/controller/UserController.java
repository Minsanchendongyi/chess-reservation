package com.chess.controller;
import com.chess.common.Result;
import com.chess.entity.User;
import com.chess.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public Result<User> login(@RequestParam String phone, @RequestParam String password, HttpSession session) {
        User user = userService.login(phone, password);
        if (user != null) {
            session.setAttribute("userId", user.getId());
            session.setAttribute("userName", user.getName());
            return Result.success(user);
        }
        return Result.error("手机号或密码错误");
    }
    @PostMapping("/register")
    public Result<String> register(@RequestBody User user) {
        return userService.register(user) ? Result.success("注册成功") : Result.error("手机号已存在");
    }
    @PostMapping("/logout")
    public Result<String> logout(HttpSession session) {
        session.invalidate();
        return Result.success("退出成功");
    }
}