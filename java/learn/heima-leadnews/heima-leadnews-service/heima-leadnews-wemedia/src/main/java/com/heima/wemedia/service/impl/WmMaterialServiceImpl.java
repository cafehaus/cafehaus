package com.heima.wemedia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.file.service.FileStorageService;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.wemedia.dtos.WmMaterialDto;
import com.heima.model.wemedia.pojos.WmMaterial;
import com.heima.utils.thread.WmTokenThredUtil;
import com.heima.wemedia.mapper.WmMaterialMapper;
import com.heima.wemedia.service.WmMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class WmMaterialServiceImpl extends ServiceImpl<WmMaterialMapper, WmMaterial> implements WmMaterialService {
    @Autowired
    FileStorageService fileStorageService;

    @Autowired
    WmMaterialMapper wmMaterialMapper;

    @Override
    public ResponseResult uploadPicture(MultipartFile multipartFile) {
        // 参数校验
        if (multipartFile == null || multipartFile.getSize() == 0) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        // 上传文件到 minIO 中
        String fileName = UUID.randomUUID().toString().replace("-", "");
        String originName = multipartFile.getOriginalFilename();
        String suffix = originName.substring(originName.lastIndexOf("."));
        String fileUrl = null;
        try {
            fileUrl = fileStorageService.uploadImgFile("", fileName + suffix, multipartFile.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 写入数据库中
        WmMaterial wmMaterial = new WmMaterial();
        wmMaterial.setUserId(WmTokenThredUtil.getUser().getId());
        wmMaterial.setUrl(fileUrl);
        wmMaterial.setType((short) 0);
        wmMaterial.setIsCollection((short) 0);
        wmMaterial.setCreatedTime(new Date());

        save(wmMaterial);

        return ResponseResult.okResult(wmMaterial);
    }

    /**
     * 上传的图片列表
     * @param wmMaterialDto
     * @return
     */
    @Override
    public ResponseResult findList(WmMaterialDto wmMaterialDto) {
        // 校验参数
        wmMaterialDto.checkParam();

        // 分页查询
        IPage page = new Page(wmMaterialDto.getPage(), wmMaterialDto.getSize());
        LambdaQueryWrapper<WmMaterial> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        Short isCollection = wmMaterialDto.getIsCollection();
        // 是否收藏
        if (isCollection != null && isCollection == 1) {
            lambdaQueryWrapper.eq(WmMaterial::getIsCollection, isCollection);
        }
        // 用户 id
        Integer userId = WmTokenThredUtil.getUser().getId();
        if (userId != null) {
            lambdaQueryWrapper.eq(WmMaterial::getUserId, userId);
        }
        // 按创建时间排序
        lambdaQueryWrapper.orderByDesc(WmMaterial::getCreatedTime);

        // 返回分页数据
        page = page(page, lambdaQueryWrapper);
        ResponseResult result = new PageResponseResult(wmMaterialDto.getPage(),
                wmMaterialDto.getSize(), (int) page.getTotal());
        result.setData(page.getRecords());
        return result;
    }
}
