package com.sky.controller.admin;

import com.github.pagehelper.Page;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜品
 */

@Slf4j
@Api(tags = "菜品")
@RestController
@RequestMapping("/admin/dish")
public class DishController {
    @Autowired
    DishService dishService;
    /**
     * 新增菜品
     */
    @PostMapping
    @ApiOperation("新增菜品")
    public Result add(@RequestBody DishDTO dishDTO) {
        log.info("新增菜品：{}", dishDTO);
        dishService.insert(dishDTO);
        return Result.success();
    }

    /**
     * 查询菜品
     * @param dishPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("查询菜品")
    public Result<PageResult>query(DishPageQueryDTO dishPageQueryDTO) {
        log.info("查询菜品：{}", dishPageQueryDTO);
        PageResult result = dishService.query(dishPageQueryDTO);
        return Result.success(result);
    }

    /**
     * 根据id查询菜品
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询菜品")
    public Result<DishVO> queryDishById(@PathVariable Long id) {
        log.info("根据id查询菜品:{}", id);
        DishVO dish = dishService.queryById(id);
        return Result.success(dish);
    }

    /**
     * 删除菜品（可批量删除）
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation("删除菜品")
    public Result delete(@RequestParam List<Long> ids) {
        log.info("删除菜品：{}", ids);
        dishService.delete(ids);
        return Result.success();
    }

    /**
     * 修改菜品
     * @param dishDTO
     */
    @PutMapping
    @ApiOperation("修改菜品")
    public Result update(@RequestBody DishDTO dishDTO) {
        log.info("修改菜品:{}", dishDTO);
        dishService.update(dishDTO);
        return Result.success();
    }

    /**
     * 停售起售
     * @param id
     * @param status
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("停售起售")
    public Result changeStatus(Long id, @PathVariable Integer status) {
        log.info("停售起售id:{}, 状态:{}", id, status);
        dishService.changeStatus(id, status);
        return Result.success();
    }
}
