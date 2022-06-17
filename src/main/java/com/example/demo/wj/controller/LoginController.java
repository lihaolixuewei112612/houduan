package com.example.demo.wj.controller;

/**
 * @Author : lihao
 * Created on : 6/16/22
 * @Description : TODO描述类作用
 */

import com.example.demo.wj.pojo.User;
import com.example.demo.wj.result.Result;
import com.example.demo.wj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;


import java.util.Objects;

@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping(value = "api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser) {
        // 对 html 标签进行转义，防止 XSS 攻击
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);
        String password = requestUser.getPassword();

        User user = userService.getByName(username);
        if (!Objects.equals(user.getUsername(), username) || !Objects.equals(user.getPassword(), password)) {
            String message = "账号密码错误";
            System.out.println("test");
            return new Result(400);
        } else {
            return new Result(200);
        }
    }
}

