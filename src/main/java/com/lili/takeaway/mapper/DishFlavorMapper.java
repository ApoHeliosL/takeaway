package com.lili.takeaway.mapper;/*
 *@InterfaceName:DishFlavorMapper
 *@Description:
 *@Author:LL
 *@Date:2023/3/27
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lili.takeaway.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishFlavorMapper extends BaseMapper<DishFlavor> {
}
