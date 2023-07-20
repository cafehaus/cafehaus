package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.SetmealVO;

import java.util.List;

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

    /**
     * 修改套餐
     * @param setmealDTO
     */
    void eidt(SetmealDTO setmealDTO);

    /**
     * 通过id查询套餐
     * @param id
     * @return
     */
    SetmealVO queryById(Long id);

    /**
     * 删除套餐
     * @param ids
     */
    void delete(List<Long> ids);
}
