package com.sky.controller.user;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController("userShopController") // 因为和 admin 下面的 ShopController 重名，这里要自己设置
@RequestMapping("/user/shop")
@Api(tags = "店铺")
@Slf4j
public class ShopController {
    @Autowired
    RedisTemplate redisTemplate;

    // 储存的营业状态的 key 键名
    private static final String KEY = "SHOP_STATUS";


    /**
     * 获取店铺营业状态
     * @return
     */
    @GetMapping("/status")
    @ApiOperation("获取店铺营业状态")
    public Result<Integer> getShopStatus() {
        log.info("获取店铺营业状态");
        Integer status = (Integer) redisTemplate.opsForValue().get(KEY);
        return Result.success(status);
    }
}
