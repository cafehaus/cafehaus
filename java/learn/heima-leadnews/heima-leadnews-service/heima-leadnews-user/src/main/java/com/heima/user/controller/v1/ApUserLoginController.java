package com.heima.user.controller.v1;


import com.heima.model.common.dtos.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/login")
@Slf4j
public class ApUserLoginController {

    @PostMapping("/login_auth")
    public ResponseResult login() {
        log.info("登录：");
        return null;
    }
}
