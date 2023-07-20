package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.enumeration.OperationType;
import com.sky.vo.SetmealVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealMapper {
    /**
     * 分页查询套餐
     * @param setmealPageQueryDTO
     * @return
     */
    Page<SetmealVO> query(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * 新增套餐
     * @param setmeal
     */
    @AutoFill(OperationType.INSERT)
    void add(Setmeal setmeal);

    /**
     * 更新套餐
     * @param setmeal
     */
    @AutoFill(OperationType.UPDATE)
    void edit(Setmeal setmeal);

    /**
     * 通过id查询套餐
     * @param id
     * @return
     */
    @Select("select * from setmeal where id=#{id}")
    SetmealVO queryById(Long id);

    /**
     * 删除套餐
     * @param ids
     */
    void delete(List<Long> ids);
}
