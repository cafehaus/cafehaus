package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealDishMapper {

    /**
     * 根据菜品id查询套餐id
     * @param dishIds
     * @return
     */
    List<Long> querySetmealIdByDishIds(List<Long> dishIds);

    /**
     * 批量新增套餐菜品
     * @param setmealDishs
     */
    void addBatch(List<SetmealDish> setmealDishs);
}
