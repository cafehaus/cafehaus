package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("admin/setmeal")
@Api(tags = "套餐")
public class SetmealController {
    @Autowired
    SetmealService setmealService;

    /**
     * 分页查询套餐
     * @param setmealPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("分页查询套餐")
    public  Result<PageResult> query(SetmealPageQueryDTO setmealPageQueryDTO) {
        log.info("分页查询套餐:{}", setmealPageQueryDTO);
        PageResult result = setmealService.query(setmealPageQueryDTO);
        return Result.success(result);
    }

    /**
     * 新增套餐
     * @param setmealDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增套餐")
    public Result add(@RequestBody SetmealDTO setmealDTO) {
        log.info("新增套餐:{}", setmealDTO);
        setmealService.add(setmealDTO);
        return Result.success();
    }
}