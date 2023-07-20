package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService {
    /**
     * 新增菜品
     * @param dishDTO
     */
    void insert(DishDTO dishDTO);

    /**
     * 查询菜品
     * @param dishPageQueryDTO
     * @return
     */
    PageResult query(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 删除菜品
     * @param ids
     */
    void delete(List<Long> ids);

    /**
     * 根据id查询菜品
     * @param id
     * @return
     */
    DishVO queryById(Long id);

    /**
     * 修改菜品
     * @param dishDTO
     */
    void update(DishDTO dishDTO);

    /**
     * 停售起售
     * @param id
     * @param status
     */
    void changeStatus(Long id, Integer status);

    /**
     * 根据分类id查询菜品
     * @param dish
     * @return
     */
    List<DishVO> queryByCategoryOrName(Dish dish);
}