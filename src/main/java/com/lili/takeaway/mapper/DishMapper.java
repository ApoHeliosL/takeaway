package com.lili.takeaway.mapper;/*
 *@InterfaceName:DishMapper
 *@Description:
 *@Author:LL
 *@Date:2023/3/24
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lili.takeaway.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
