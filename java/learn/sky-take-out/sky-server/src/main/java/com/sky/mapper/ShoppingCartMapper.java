package com.sky.mapper;

import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {
    /**
     * 查询购物车
     * @param shoppingCart
     */
    List<ShoppingCart> query(ShoppingCart shoppingCart);

    /**
     * 修改数量
     * @param shoppingCart
     */
    @Update("update shopping_cart set number=#{number} where id=#{id}")
    void editNumber(ShoppingCart shoppingCart);

    /**
     * 新增
     * @param shoppingCart
     */
    @Insert("insert into shopping_cart" +
            "(name, image, user_id, dish_id, setmeal_id, dish_flavor, number, amount, create_time)" +
            "values" +
            "(#{name}, #{image}, #{userId}, #{dishId}, #{setmealId}, #{dishFlavor}, #{number}, #{amount}, #{createTime})")
    void add(ShoppingCart shoppingCart);

    /**
     * 删除购物车
     * @param id
     */
    @Delete("delete from shopping_cart where id=#{id}")
    void delById(Long id);

    /**
     * 清空购物车
     * @param userId
     */
    @Delete("delete from shopping_cart where user_id=#{userId}")
    void clean(Long userId);

    /**
     * 多条件删除，测试用 xml 动态配置会报错：org.apache.ibatis.binding.BindingException: Invalid bound statement (not found）
     * @param shoppingCart
     */
    // @Delete("delete from shopping_cart where user_id=#{userId} and dish_id=#{dishId}")
    void del(ShoppingCart shoppingCart);
}
