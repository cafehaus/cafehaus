package com.sky.service.impl;

import com.sky.context.BaseContext;
import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.Dish;
import com.sky.entity.ShoppingCart;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.mapper.ShoppingCartMapper;
import com.sky.service.ShoppingCartService;
import com.sky.vo.SetmealVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    ShoppingCartMapper shoppingCartMapper;
    @Autowired
    DishMapper dishMapper;
    @Autowired
    SetmealMapper setmealMapper;

    // 当前用户 id，不能定义在这里，会导致获取不到
    // private Long userId = BaseContext.getCurrentId();

    /**
     * 添加购物车
     * @param shoppingCartDTO
     */
    @Override
    public void add(ShoppingCartDTO shoppingCartDTO) {
        // 当前用户 id
        Long userId = BaseContext.getCurrentId();
        // 菜品id和套餐id只可能存在一个
        Long dishId = shoppingCartDTO.getDishId();
        Long setmealId = shoppingCartDTO.getSetmealId();

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUserId(userId);

        if (dishId != null) {
            shoppingCart.setDishId(dishId);
        } else {
            shoppingCart.setSetmealId(setmealId);
        }

        List<ShoppingCart> list = shoppingCartMapper.query(shoppingCart);
        ShoppingCart cur = null;
        if (list != null && list.size() > 0) {
            cur = list.get(0);
        }
        // 购物车表里已经存在就数量+1，不存在就插入一条数据
        if (cur != null) {
            Integer number = cur.getNumber() + 1;
            cur.setNumber(number);
            shoppingCartMapper.editNumber(cur);
        } else {
            // 插入时要先查询出菜品或套餐信息
            String image = "";
            String name = "";
            BigDecimal price = null;

            if (dishId != null) {
                Dish dish = dishMapper.queryById(dishId);
                name = dish.getName();
                image = dish.getImage();
                price = dish.getPrice();
            } else {
                SetmealVO setmeal = setmealMapper.queryById(setmealId);
                name = setmeal.getName();
                image = setmeal.getImage();
                price = setmeal.getPrice();
            }

            cur = ShoppingCart.builder()
                    .userId(userId)
                    .name(name)
                    .image(image)
                    .amount(price)
                    .number(1)
                    .createTime(LocalDateTime.now())
                    .build();
            if (dishId != null) {
                cur.setDishId(dishId);
            } else {
                cur.setSetmealId(setmealId);
            }

            shoppingCartMapper.add(cur);
        }
    }

    /**
     * 删除购物车
     * @param shoppingCartDTO
     */
    @Override
    public void subtract(ShoppingCartDTO shoppingCartDTO) {
        // 当前用户 id
        Long userId = BaseContext.getCurrentId();

        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCart);
        shoppingCart.setUserId(userId);

        // 先查询出当前的数量
        List<ShoppingCart> list = shoppingCartMapper.query(shoppingCart);
        ShoppingCart cur = null;
        if (list != null && list.size() > 0) {
            cur = list.get(0);
        }

        if (cur != null) {
            Integer number = cur.getNumber();
            // 只有 1 个时要清除掉当前商品
            if (number > 1) {
                cur.setNumber(number - 1);
                shoppingCartMapper.editNumber(cur);
            } else {
                shoppingCartMapper.delById(cur.getId());
            }
        }
    }

    /**
     * 清空购物车
     */
    @Override
    public void clean() {
        // 当前用户 id
        Long userId = BaseContext.getCurrentId();
        shoppingCartMapper.clean(userId);
    }

    /**
     * 查看购物车
     * @return
     */
    @Override
    public List<ShoppingCart> query() {
        // 当前用户 id
        Long userId = BaseContext.getCurrentId();
        ShoppingCart shoppingCart = ShoppingCart.builder()
                .userId(userId)
                .build();
        List<ShoppingCart> list = shoppingCartMapper.query(shoppingCart);
        return list;
    }
}
