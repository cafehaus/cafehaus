package com.sky.mapper;

import com.sky.entity.SetmealDish;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealDishMapper {
    /**
     * 批量新增套餐菜品
     * @param setmealDishs
     */
    void addBatch(List<SetmealDish> setmealDishs);

    /**
     * 根据菜品id删除
     * @param setmealId
     */
    @Delete("delete from setmeal_dish where setmeal_id=#{setmealId}")
    void deleteBySetmealId(Long setmealId);

    /**
     * 根据菜品id查询套餐id
     * @param dishIds
     * @return
     */
    List<Long> querySetmealIdByDishIds(List<Long> dishIds);

    /**
     * 根据套餐id查询
     * @param setmealId
     * @return
     */
    @Select("select * from setmeal_dish where setmeal_id=#{setmealId}")
    List<SetmealDish> queryBySetmealId(Long setmealId);
}
