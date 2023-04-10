package com.lili.takeaway.service;/*
 *@InterfaceName:DishService
 *@Description:
 *@Author:LL
 *@Date:2023/3/24
 */

import com.baomidou.mybatisplus.extension.service.IService;
import com.lili.takeaway.dto.DishDto;
import com.lili.takeaway.entity.Dish;

import java.util.List;

public interface DishService extends IService<Dish> {
    //新增菜品 同时需要操作两张表
    public void saveWithFlavor(DishDto dishDto);

    //根据id 查询菜品信息和对应口味信息
    public DishDto getByIdWithFlavor(Long id);

    //跟新菜品信息 同时跟新口味信息
    public void updateWithFlavor(DishDto dishDto);

    //删除菜品
    public void deleteWithFlavor(List<Long> ids);
}
