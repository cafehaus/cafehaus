package cn.cafe123.helloworld.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello world, cafehaus!";
    }

    // 带参数
    @GetMapping("/username")
    public String userName(String nickname) {
        return "Hello," + nickname;
    }

    // post
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username, String password) {
        return "你的名字：" + username + ", 你的密码：" + password;
    }
}











