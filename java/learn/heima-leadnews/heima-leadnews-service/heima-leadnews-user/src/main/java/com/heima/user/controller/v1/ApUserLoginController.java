package com.heima.user.controller.v1;


import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.user.dtos.LoginDto;
import com.heima.user.service.ApUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/login")
@Api(value = "登录", tags = "登录")
@Slf4j
public class ApUserLoginController {
    @Autowired
    private ApUserService apUserService;

    @PostMapping("/login_auth")
    @ApiOperation("手机号密码登录")
    public ResponseResult login(@RequestBody LoginDto loginDto) {
        log.info("手机号密码登录：{}", loginDto);
        return apUserService.login(loginDto);
    }
}
