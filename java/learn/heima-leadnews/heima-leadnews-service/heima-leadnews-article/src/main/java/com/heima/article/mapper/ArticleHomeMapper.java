package com.heima.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.article.pojos.ApArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleHomeMapper extends BaseMapper<ApArticle> {
    List<ApArticle> loadArticleList(@Param("articleDto") ArticleHomeDto articleHomeDto, @Param("type") Short type);
}