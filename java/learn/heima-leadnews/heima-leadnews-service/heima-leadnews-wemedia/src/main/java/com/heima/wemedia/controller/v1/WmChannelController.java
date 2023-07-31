package com.heima.wemedia.controller.v1;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.wemedia.service.WmChannelService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/channel")
@Api(tags = "渠道")
@Slf4j
public class WmChannelController {
    @Autowired
    WmChannelService wmChannelService;

    @GetMapping("channels")
    public ResponseResult findAll() {
        log.info("查询所有渠道");
        ResponseResult result = wmChannelService.findAll();
        return result;
    }
}
