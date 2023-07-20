package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.StatusConstant;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.entity.Setmeal;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealDishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.service.SetmealService;
import com.sky.vo.DishVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DishServiceImpl implements DishService {
    @Autowired
    DishMapper dishMapper;
    @Autowired
    DishFlavorMapper dishFlavorMapper;
    @Autowired
    SetmealDishMapper setmealDishMapper;
    @Autowired
    SetmealMapper setmealMapper;
    @Autowired
    SetmealService setmealService;

    /**
     * 新增菜品
     * @param dishDTO
     */
    @Override
    public void insert(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        dishMapper.insert(dish);

        // 新增菜品的 insert 设置了 useGeneratedKeys，插入成功后会返回主键
        Long dishId = dish.getId();
        // 遍历设置风味的 dishId
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (flavors != null && flavors.size() > 0) {
            flavors.forEach(m->{
                m.setDishId(dishId);
            });
            dishFlavorMapper.insertBatch(flavors);
        }
    }

    /**
     * 查询菜品
     * @param dishPageQueryDTO
     * @return
     */
    @Override
    public PageResult query(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());
        Page<DishVO> page = dishMapper.query(dishPageQueryDTO);

        Long total = page.getTotal();
        List<DishVO> records = page.getResult();
        return new PageResult(total, records);
    }

    /**
     * 删除菜品
     * @param ids
     */
    @Override
    @Transactional
    public void delete(List<Long> ids) {
        // 起售中的菜品不能删除
        for (Long id: ids) {
            Dish dish = dishMapper.queryById(id);
            if (dish.getStatus() == StatusConstant.ENABLE) {
                throw new DeletionNotAllowedException("起售中的菜品不能删除");
            }
        }

        // 关联套餐的菜品不能删除
        List<Long> setmealIds = setmealDishMapper.querySetmealIdByDishIds(ids);
        if (setmealIds != null && setmealIds.size() > 0) {
            throw new DeletionNotAllowedException("关联套餐的菜品不能删除");
        }

        // 删除菜品
        for (Long id: ids) {
            dishMapper.delete(id);

            // 删除菜品关联的口味表数据
            dishFlavorMapper.deleteByDishId(id);
        }
    }

    /**
     * 根据id查询菜品
     * @param id
     * @return
     */
    @Override
    public DishVO queryById(Long id) {
        // 查出菜品数据
        Dish dish = dishMapper.queryById(id);
        // 查出菜品关联的口味列表
        List<DishFlavor> dishFlavors = dishFlavorMapper.queryByDishId(id);

        // 合成前端需要的数据
        DishVO dishVO = new DishVO();
        BeanUtils.copyProperties(dish, dishVO);
        dishVO.setFlavors(dishFlavors);

        return dishVO;
    }

    /**
     * 修改菜品
     * @param dishDTO
     */
    @Transactional
    public void update(DishDTO dishDTO) {
        // 更新菜品数据
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        dishMapper.update(dish);

        Long dishId = dish.getId();
        // 先删除原有的口味
        dishFlavorMapper.deleteByDishId(dishId);

        // 编辑时有口味重新插入
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (flavors != null && flavors.size() > 0) {
            flavors.forEach(m->{
                m.setDishId(dishId);
            });
            dishFlavorMapper.insertBatch(flavors);
        }
    }

    /**
     * 停售起售
     * @param id
     * @param status
     */
    @Override
    public void changeStatus(Long id, Integer status) {
        Dish dish = Dish.builder()
                .status(status)
                .id(id)
                .build();
        dishMapper.update(dish);

        // TODO 如果是停售菜品，要同时停售包含该菜品的套餐
        if (status == StatusConstant.DISABLE) {
            List<Long> ids = new ArrayList<Long>();
            ids.add(id);

            List<Long> setmealIds = setmealDishMapper.querySetmealIdByDishIds(ids);
            if (setmealIds != null && setmealIds.size() > 0) {
                for (Long setmealId: setmealIds) {
                    // 方式一：直接调用 mapper 层修改数据
//                    Setmeal setmeal = Setmeal.builder()
//                            .id(setmealId)
//                            .status(StatusConstant.DISABLE)
//                            .build();
//                    setmealMapper.edit(setmeal);
                    // 方式二：调用 service 层的 SetmealService 去修改数据
                    // 不知道这样是否合理，感觉职责更分明，更像微服务中的服务之间调用
                    setmealService.changeStatus(setmealId, StatusConstant.DISABLE);
                }
            }
        }
    }

    /**
     * 根据分类id查询菜品
     * @param dish
     * @return
     */
    @Override
    public List<DishVO> queryByCategoryOrName(Dish dish) {
        // Dish dish = Dish.builder()
        // .categoryId(categoryId)
        // .status(StatusConstant.ENABLE)
        // .build();

        // 需要同时支持分类或菜名查询，所以这里直接用 Dish 接收参数
        // 注意要起售中的菜品
        dish.setStatus(StatusConstant.ENABLE);
        List<DishVO> dishs = dishMapper.queryByCategoryOrName(dish);
        return dishs;
    }
}
