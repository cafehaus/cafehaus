package com.heima.wemedia.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.common.constants.WemediaConstants;
import com.heima.common.exception.CustomException;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.wemedia.dtos.WmNewsDto;
import com.heima.model.wemedia.dtos.WmNewsPageReqDto;
import com.heima.model.wemedia.pojos.WmMaterial;
import com.heima.model.wemedia.pojos.WmNews;
import com.heima.model.wemedia.pojos.WmNewsMaterial;
import com.heima.model.wemedia.pojos.WmUser;
import com.heima.utils.thread.WmTokenThredUtil;
import com.heima.wemedia.mapper.WmMaterialMapper;
import com.heima.wemedia.mapper.WmNewsMapper;
import com.heima.wemedia.mapper.WmNewsMaterialMapper;
import com.heima.wemedia.service.WmNewsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WmNewsServiceImpl extends ServiceImpl<WmNewsMapper, WmNews> implements WmNewsService {
    @Autowired
    WmNewsMaterialMapper wmNewsMaterialMapper;
    @Autowired
    WmMaterialMapper wmMaterialMapper;

    /**
     * 分页查询文章列表
     * @param dto
     * @return
     */
    @Override
    public ResponseResult findAll(WmNewsPageReqDto dto) {
        // 参数校验
        if (dto == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }
        dto.checkParam();

        // 获取当前登录人的信息
        WmUser wmUser = WmTokenThredUtil.getUser();
        if (wmUser == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }

        // 分页条件查询
        IPage page = new Page(dto.getPage(), dto.getSize());
        LambdaQueryWrapper<WmNews> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        // 状态精确查询
        if (dto.getStatus() != null) {
            lambdaQueryWrapper.eq(WmNews::getStatus, dto.getStatus());
        }

        // 频道精确查询
        if (dto.getChannelId() != null) {
            lambdaQueryWrapper.eq(WmNews::getChannelId, dto.getChannelId());
        }

        // 时间范围查询
        if (dto.getBeginPubDate() != null && dto.getEndPubDate() != null) {
            lambdaQueryWrapper.between(WmNews::getPublishTime, dto.getBeginPubDate(), dto.getEndPubDate());
        }

        // 关键字模糊查询
        if (dto.getKeyword() != null) {
            lambdaQueryWrapper.like(WmNews::getTitle, dto.getKeyword());
        }

        // 查询当前登录用户的文章
        lambdaQueryWrapper.eq(WmNews::getUserId, wmUser.getId());

        // 发布时间倒序查询
        lambdaQueryWrapper.orderByDesc(WmNews::getPublishTime);

        // 结果返回
        page = page(page, lambdaQueryWrapper);
        ResponseResult result = new PageResponseResult(dto.getPage(), dto.getSize(), (int)page.getTotal());
        result.setData(page.getRecords());
        return result;
    }

    /**
     * 新增编辑文章
     * @param dto
     * @return
     */
    @Override
    public ResponseResult submitNews(WmNewsDto dto) {
        // 校验参数
        if (dto == null || dto.getContent() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        // 复制参数
        WmNews wmNews = new WmNews();
        BeanUtils.copyProperties(dto, wmNews);

        // 封面图 list 转成逗号分割的 string
        List<String> images = dto.getImages();
        if (images != null && images.size() > 0) {
            String imagesText = StringUtils.join(images, ",");
            wmNews.setImages(imagesText);
        }

        // 如果当前封面类型为自动 -1，设置为 null
        if (dto.getType() != null && dto.getType().equals(WemediaConstants.WM_NEWS_TYPE_AUTO)) {
            wmNews.setType(null);
        }

        // 保存文章
        saveOrUpdateWmNews(wmNews);

        // 为草稿时直接返回成功
        Short graftCode = WmNews.Status.NORMAL.getCode(); // WmNews 实体里定义了状态枚举
        if (dto.getStatus().equals(graftCode)) {
            return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
        }

        // 非草稿时
        // 提取内容中的图片信息
        List<String> materials =  ectractUrlInfo(dto.getContent());
        saveRelativeInfo(materials, wmNews.getId(), WemediaConstants.WM_CONTENT_REFERENCE);

        // 保存文章封面图片与素材的关系，如果当前布局是自动，需要匹配封面图片
        saveRelativeInfoForCover(dto, wmNews, materials);

        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }


    /**
     * 保存文章
     * @param wmNews
     */
    private void saveOrUpdateWmNews(WmNews wmNews) {
        // 补全参数
        wmNews.setUserId(WmTokenThredUtil.getUser().getId());
        wmNews.setCreatedTime(new Date());
        wmNews.setSubmitedTime(new Date());
        wmNews.setEnable((short)1); // 默认上架

        if (wmNews.getId() == null) {
            // 新增时直接保存到数据库
            save(wmNews);
        } else {
            // 修改
            // 删除文章图片与素材的关系
            wmNewsMaterialMapper.delete(Wrappers.<WmNewsMaterial>lambdaQuery().eq(WmNewsMaterial::getNewsId, wmNews.getId()));
            updateById(wmNews);
        }
    }


    /**
     * 提取内容中的图片信息
     * @param content 内容是用字符串化了的 List 存的 [{type: "text", value: "我是一段文字"},{type: "image", value: "http:xxxx.com/3844242.jpg"}, {type: "text", value: "我是文章末尾"}]
     * @return
     */
    private List<String> ectractUrlInfo(String content) {
        List<String> materials = new ArrayList<>();
        List<Map> maps = JSON.parseArray(content, Map.class);

        for (Map map:maps) {
            if (map.get("type").equals("images")) {
                String imgUrl = (String) map.get("value");
                materials.add(imgUrl);
            }
        }
        return materials;
    }


    /**
     * 保存文章里插入的图片与素材的关系到 wm_news_material 关系表中
     * @param materials
     * @param newsId
     * @param type
     */
    private void saveRelativeInfo(List<String> materials, Integer newsId, Short type) {
        if (materials != null && !materials.isEmpty()) {
            // 通过图片 url 去素材表 wm_material 中查对应的 id
            List<WmMaterial> dbMaterials = wmMaterialMapper.selectList(Wrappers.<WmMaterial>lambdaQuery().in(WmMaterial::getUrl, materials));

            // 表中未查到数据或查到的总条数不一致时直接抛出异常：1、能够提示调用者素材失效了，2、进行数据的回滚
            if (dbMaterials == null || dbMaterials.size() == 0 ||
                    materials.size() != dbMaterials.size()) {
                throw new CustomException(AppHttpCodeEnum.MATERIAL_REFERENCE_FAIL);
            }


            List<Integer> idList = dbMaterials.stream().map(WmMaterial::getId).collect(Collectors.toList());

            //批量保存
            wmNewsMaterialMapper.saveRelations(idList,newsId,type);
        }
    }

    /**
     * 第一个功能：如果当前封面类型为自动，则设置封面类型的数据
     * 匹配规则：
     * 1，如果内容图片大于等于1，小于3  单图  type 1
     * 2，如果内容图片大于等于3  多图  type 3
     * 3，如果内容没有图片，无图  type 0
     *
     * 第二个功能：保存封面图片与素材的关系
     * @param dto
     * @param wmNews
     * @param materials
     */
    private void saveRelativeInfoForCover(WmNewsDto dto, WmNews wmNews, List<String> materials) {
        List<String> images = dto.getImages();
        if (dto.getStatus().equals(WemediaConstants.WM_NEWS_TYPE_AUTO)) {
            Integer size = materials.size();
            if (size > 3) { // 多图
                wmNews.setType(WemediaConstants.WM_NEWS_MANY_IMAGE);
                images = materials.stream().limit(3).collect(Collectors.toList());
            } else if (size >= 1 && size < 3) { // 单图
                wmNews.setType(WemediaConstants.WM_NEWS_SINGLE_IMAGE);
                images = materials.stream().limit(1).collect(Collectors.toList());
            } else { // 无图
                wmNews.setType(WemediaConstants.WM_NEWS_NONE_IMAGE);
            }

            //修改文章
            if(images != null && images.size() > 0){
                wmNews.setImages(StringUtils.join(images,","));
            }
            updateById(wmNews);
        }

        // 将封面图和文章的对应关系存到 wm_news_material 关系表中
        if(images != null && images.size() > 0){
            saveRelativeInfo(images, wmNews.getId(), WemediaConstants.WM_COVER_REFERENCE);
        }
    }

}
