package com.heima.model.article.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleHomeDto {
    /**
     * 最大时间
     */
    Date maxBehotTime;

    /**
     * 最小时间
     */
    Date minBehotTime;

    /**
     * 分页 size
     */
    Integer size;

    /**
     * 频道 id
     */
    String tag;
}
