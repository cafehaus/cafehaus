package com.heima.article.controller.v1;

import com.heima.article.service.ArticleHomeService;
import com.heima.common.constants.ArticleConstants;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.common.dtos.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/article")
@Slf4j
public class ArticleHomeController {
    @Autowired
    ArticleHomeService articleHomeService;

    /**
     * 文章列表
     * @param articleHomeDto
     * @return
     */
    @PostMapping("/load")
    public ResponseResult load(ArticleHomeDto articleHomeDto) {
        log.info("文章列表:{}", articleHomeDto);
        return articleHomeService.load(ArticleConstants.LOADTYPE_LOAD_NEW, articleHomeDto);
    }

    /**
     * 文章列表加载更多
     * @param articleHomeDto
     * @return
     */
    @PostMapping("/loadmore")
    public ResponseResult loadMore(ArticleHomeDto articleHomeDto) {
        log.info("文章列表加载更多:{}", articleHomeDto);
        return articleHomeService.load(ArticleConstants.LOADTYPE_LOAD_MORE, articleHomeDto);
    }

    /**
     * 文章列表加载最新
     * @param articleHomeDto
     * @return
     */
    @PostMapping("/loadnew")
    public ResponseResult loadNew(ArticleHomeDto articleHomeDto) {
        log.info("文章列表加载最新:{}", articleHomeDto);
        return articleHomeService.load(ArticleConstants.LOADTYPE_LOAD_NEW, articleHomeDto);
    }
}
