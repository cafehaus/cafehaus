package com.heima.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.dtos.WmNewsDto;
import com.heima.model.wemedia.dtos.WmNewsPageReqDto;
import com.heima.model.wemedia.pojos.WmNews;

public interface WmNewsService extends IService<WmNews> {
    /**
     * 文章列表
     * @param dto
     * @return
     */
    ResponseResult findAll(WmNewsPageReqDto dto);


    /**
     * 新增编辑文章
     * @param wmNewsDto
     * @return
     */
    ResponseResult submitNews(WmNewsDto wmNewsDto);
}
