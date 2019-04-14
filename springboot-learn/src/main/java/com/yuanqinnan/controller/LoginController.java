package com.yuanqinnan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Description: java-learn
 * <p>
 * Created by yuanqn on 2019/4/14 16:47
 */
@Controller
public class LoginController {

    @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession httpSession) {
        if (!StringUtils.isEmpty(username) &&
                "123456".equals(password)) {
            //设置session
            httpSession.setAttribute("loginUser", username);
            //重定向到主页
            return "redirect:/main.html";
        } else {
            map.put("msg", "用户名密码错误");
            return "login";
        }
    }
}
