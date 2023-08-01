package com.heima.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.article.dtos.ArticleDto;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.common.dtos.ResponseResult;

public interface ArticleHomeService extends IService<ApArticle> {

    /**
     * 文章列表
     * @param articleHomeDto
     * @return
     */
    ResponseResult load(Short type, ArticleHomeDto articleHomeDto);

    /**
     * 保存文章
     * @param dto
     * @return
     */
    ResponseResult saveArticle(ArticleDto dto);
}
