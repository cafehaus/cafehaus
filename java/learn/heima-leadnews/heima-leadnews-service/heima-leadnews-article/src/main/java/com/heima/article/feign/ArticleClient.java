package com.heima.article.feign;

import com.heima.apis.article.IArticleClient;
import com.heima.article.service.ArticleHomeService;
import com.heima.model.article.dtos.ArticleDto;
import com.heima.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArticleClient implements IArticleClient {

    @Autowired
    private ArticleHomeService articleHomeService;

    @Override
    @PostMapping("/api/v1/article/save")
    public ResponseResult saveArticle(@RequestBody ArticleDto dto) {
        return articleHomeService.saveArticle(dto);
    }

}