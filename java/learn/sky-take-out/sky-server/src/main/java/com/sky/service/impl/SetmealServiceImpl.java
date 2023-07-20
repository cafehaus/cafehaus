package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.SetmealDishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    SetmealMapper setmealMapper;
    @Autowired
    SetmealDishMapper setmealDishMapper;

    /**
     * 分页查询套餐
     * @param setmealPageQueryDTO
     */
    @Override
    public PageResult query(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageHelper.startPage(setmealPageQueryDTO.getPage(), setmealPageQueryDTO.getPageSize());
        Page<SetmealVO> page = setmealMapper.query(setmealPageQueryDTO);

        Long total = page.getTotal();
        List<SetmealVO> records = page.getResult();

        return new PageResult(total, records);
    }

    /**
     * 新增套餐
     * @param setmealDTO
     */
    @Override
    @Transactional
    public void add(SetmealDTO setmealDTO) {
        // 新增套餐
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        setmealMapper.add(setmeal);

        // 新增成功后要返回id，下面插入套餐菜品关联表要用
        Long setmealId = setmeal.getId();

        // 新增套餐关联的菜品
        List<SetmealDish> setmealDishs = setmealDTO.getSetmealDishes();
        if (setmealDishs != null && setmealDishs.size() > 0) {
            setmealDishs.forEach(m->{
                m.setSetmealId(setmealId);
            });
//            for (SetmealDish s: setmealDishs) {
//                s.setSetmealId(setmealId);
//                System.out.printf("新增套餐关联的菜品:" + s);
//            }
            setmealDishMapper.addBatch(setmealDishs);
        }
    }

    /**
     * 起售停售套餐
     * @param id
     * @param status
     */
    @Override
    public void changeStatus(Long id, Integer status) {
        Setmeal setmeal = Setmeal.builder()
                .id(id)
                .status(status)
                .build();
        setmealMapper.edit(setmeal);
    }

    /**
     * 修改套餐
     * @param setmealDTO
     */
    @Override
    public void eidt(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        // 修改套餐数据
        setmealMapper.edit(setmeal);

        // 修改套餐菜品关联表数据
        Long setmealId = setmealDTO.getId();
        // 先全部删除
        setmealDishMapper.deleteBySetmealId(setmealId);
        // 重新插入关联的菜品
        List<SetmealDish> setmealDishs = setmealDTO.getSetmealDishes();
        if (setmealDishs != null && setmealDishs.size() > 0) {
            for (SetmealDish s: setmealDishs) {
                s.setSetmealId(setmealId);
            }
            setmealDishMapper.addBatch(setmealDishs);
        }
    }

    /**
     * 通过id查询套餐
     * @param id
     * @return
     */
    @Override
    public SetmealVO queryById(Long id) {
        SetmealVO setmealVO = setmealMapper.queryById(id);

        // 查询套餐关联的菜品
        List<SetmealDish> setmealDishes = setmealDishMapper.queryBySetmealId(id);
        setmealVO.setSetmealDishes(setmealDishes);
        return setmealVO;
    }

    /**
     * 删除套餐
     * @param ids
     */
    @Override
    @Transactional
    public void delete(List<Long> ids) {
        // 起售中的套餐不能删除
        ids.forEach(id -> {
            SetmealVO setmeal = setmealMapper.queryById(id);
            if(StatusConstant.ENABLE == setmeal.getStatus()){
                throw new DeletionNotAllowedException(MessageConstant.SETMEAL_ON_SALE);
            }
        });

        // 删除套餐
        setmealMapper.delete(ids);

        // 删除套餐菜品关联表数据
        for (Long id : ids) {
            setmealDishMapper.deleteBySetmealId(id);
        }
    }
}
