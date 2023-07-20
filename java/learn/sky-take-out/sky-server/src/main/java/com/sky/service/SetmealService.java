package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;

public interface SetmealService {
    /**
     * 分页查询套餐
     * @param setmealPageQueryDTO
     */
    PageResult query(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * 新增套餐
     * @param setmealDTO
     */
    void add(SetmealDTO setmealDTO);

    /**
     * 起售停售套餐
     * @param id
     * @param status
     */
    void changeStatus(Long id, Integer status);
}
