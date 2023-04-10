package com.lili.takeaway.service.impl;/*
 *@ClassName:DishServiceImpl
 *@Description:
 *@Author:LL
 *@Date:2023/3/24
 */

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lili.takeaway.common.CustomException;
import com.lili.takeaway.dto.DishDto;
import com.lili.takeaway.entity.Dish;
import com.lili.takeaway.entity.DishFlavor;
import com.lili.takeaway.mapper.DishMapper;
import com.lili.takeaway.service.DishFlavorService;
import com.lili.takeaway.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DishServiceImpl extends ServiceImpl<DishMapper,Dish> implements DishService {
    @Autowired
    private DishFlavorService dishFlavorService;

    @Autowired
    private DishService dishService;

    @Transactional
    public void saveWithFlavor(DishDto dishDto){
        //保存菜品的基本信息到菜品表
        this.save(dishDto);
        Long dishId = dishDto.getId();//菜品id
        //赋值dishId
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors = flavors.stream().map((item)->{
            item.setDishId(dishId);
            return item;
        }).collect(Collectors.toList());
        //保存菜品口味到菜品口味表
        dishFlavorService.saveBatch(flavors);
    }

    /**
     * 根据id 查询菜品信息和对应口味信息
     * @param id
     * @return
     */
    @Override
    public DishDto getByIdWithFlavor(Long id) {
        //1.查询菜品基本信息 dish表
        Dish dish = this.getById(id);
        //创建对象
        DishDto dishDto = new DishDto();
        //拷贝
        BeanUtils.copyProperties(dish,dishDto);
        //2.查询口味信息 dish_flavor表
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId,dish.getId());
        List<DishFlavor> flavors = dishFlavorService.list(queryWrapper);
        dishDto.setFlavors(flavors);

        return dishDto;
    }

    @Transactional
    public void updateWithFlavor(DishDto dishDto) {
        //更新dish表
        this.updateById(dishDto);
        //更新dish_flavor表： 先清理口味数据(delete)+在添加当前提交过来的数据(insert)
        //1.先获取菜品id
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId,dishDto.getId());
        //2.清除口味
        dishFlavorService.remove(queryWrapper);
        //3.重新插入口味
        List<DishFlavor> flavors = dishDto.getFlavors();
        //获取dishId
        flavors = flavors.stream().map((item)->{
            item.setDishId(dishDto.getId());
            return item;
        }).collect(Collectors.toList());
        dishFlavorService.saveBatch(flavors);

    }

    /**
     * 根据id删除对应的菜品信息， 同时还要删除关联表dish_flavor中的数据。
     * 注意：如果菜品为起售则不能删除菜品，只有先停售后才能删除。
     *
     * @param ids
     */
    @Override
    public void deleteWithFlavor(List<Long> ids) {
        // 判断当前菜品的状态。
        // select count(*) from dish where id in (ids) and state=1;
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.in(Dish::getId, ids);
        dishLambdaQueryWrapper.eq(Dish::getStatus, 1);
        int count = dishService.count(dishLambdaQueryWrapper);
        if (count>0){
            // 如果不能删除，则抛出异常
            throw new CustomException("菜品为起售状态，无法删除");
        }
        // 如果能删除（status=0） ， 删除菜品信息
        this.removeByIds(ids);
        // 删除对应的口味信息 delete from dish_flavor where dish_id in (ids)
        LambdaQueryWrapper<DishFlavor> dishFlavorLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishFlavorLambdaQueryWrapper.in(DishFlavor::getDishId,ids);
        //执行删除口味。
        dishFlavorService.remove(dishFlavorLambdaQueryWrapper);
    }
}
