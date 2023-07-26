package com.heima.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.user.dtos.LoginDto;
import com.heima.model.user.pojos.ApUser;
import com.heima.user.mapper.ApUserMapper;
import com.heima.user.service.ApUserService;
import com.heima.utils.common.AppJwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class ApUserServiceImpl extends ServiceImpl<ApUserMapper, ApUser> implements ApUserService {

    @Override
    public ResponseResult login(LoginDto loginDto) {
        String phone = loginDto.getPhone();
        String password = loginDto.getPassword();

        if (!StringUtils.isBlank(phone) && !StringUtils.isBlank(password)) {
            // 1、根据手机号查询用户
            ApUser apUser = getOne(Wrappers.<ApUser>lambdaQuery().eq(ApUser::getPhone, loginDto.getPhone()));
            if (apUser == null) {
                return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST, "当前用户不存在");
            }

            // 2、比对密码
            String salt = apUser.getSalt();
            // String pwd = DigestUtils.md5DigestAsHex((salt + password).getBytes()); // 注意这里是先密码后盐值相加
            String pwd = DigestUtils.md5DigestAsHex((password + salt).getBytes());

            if (!pwd.equals(apUser.getPassword())) {
                return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR, "登录密码错误");
            }

            // 3、生成 token
            Map<String, Object> map = new HashMap<>();
            Integer userId = apUser.getId();
            String token = AppJwtUtil.getToken(userId.longValue()); // longValue 可以讲 Integer 强转 Long
            map.put("token", token);

            // 用户信息也塞进去，注意密码和盐值不能返回
            apUser.setPassword("");
            apUser.setSalt("");
            map.put("user", apUser);

            return ResponseResult.okResult(map);
        } else {
            //2.游客  同样返回token  id = 0
            Map<String, Object> map = new HashMap<>();
            map.put("token", AppJwtUtil.getToken(0l));
            return ResponseResult.okResult(map);
        }
    }
}
