package com.heima.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.article.mapper.ArticleHomeMapper;
import com.heima.article.service.ArticleHomeService;
import com.heima.common.constants.ArticleConstants;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.common.dtos.ResponseResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ArticleHomeServiceImpl extends ServiceImpl<ArticleHomeMapper, ApArticle> implements ArticleHomeService {
    @Autowired
    ArticleHomeMapper articleHomeMapper;

    /**
     * 文章列表
     * @param articleHomeDto
     * @return
     */
    public ResponseResult load(Short type, ArticleHomeDto articleHomeDto) {
        // 参数校验
        // 1、size 分页参数，默认 10，最多 50
        Integer size = articleHomeDto.getSize();
        if (size == null || size == 0) {
            size = 10;
        }
        // size = Math.min(size, 50);
        if (size > 50) {
            size = 50;
        }
        articleHomeDto.setSize(50);

        // 2、类型参数检验
        if (!type.equals(ArticleConstants.LOADTYPE_LOAD_MORE) || !type.equals(ArticleConstants.LOADTYPE_LOAD_NEW)) {
            type = ArticleConstants.LOADTYPE_LOAD_MORE;
        }

        // 3、文章频道校验
        if (StringUtils.isBlank(articleHomeDto.getTag())) {
            articleHomeDto.setTag(ArticleConstants.DEFAULT_TAG);
        }

        // 4、时间擦参数，默认当前时间
        if(articleHomeDto.getMaxBehotTime() == null) articleHomeDto.setMaxBehotTime(new Date());
        if(articleHomeDto.getMinBehotTime() == null) articleHomeDto.setMinBehotTime(new Date());

        List<ApArticle> list = articleHomeMapper.loadArticleList(articleHomeDto, type);
        return ResponseResult.okResult(list);
    }
}
