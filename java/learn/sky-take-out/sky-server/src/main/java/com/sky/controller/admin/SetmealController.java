package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * 通过id查询套餐
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("通过id查询套餐")
    public Result<SetmealVO> queryById(@PathVariable Long id) {
        log.info("通过id查询套餐:{}", id);
        SetmealVO setmealVO = setmealService.queryById(id);
        return Result.success(setmealVO);
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

    /**
     * 起售停售套餐
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("起售停售套餐")
    public Result changeStatus(Long id, @PathVariable Integer status) {
        log.info("起售停售套餐: {}, 状态:{}", id, status);
        setmealService.changeStatus(id, status);
        return Result.success();
    }

    /**
     * 修改套餐
     * @param setmealDTO
     * @return
     */
    @PutMapping
    @ApiOperation("修改套餐")
    public Result edit(@RequestBody SetmealDTO setmealDTO) {
        log.info("修改套餐: {}", setmealDTO);
        setmealService.eidt(setmealDTO);
        return Result.success();
    }

    /**
     * 删除套餐
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation("删除套餐")
    public Result delete(@RequestParam List<Long> ids) {
        log.info("删除套餐: {}", ids);
        setmealService.delete(ids);
        return Result.success();
    }
}
