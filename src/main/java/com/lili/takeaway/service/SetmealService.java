package com.lili.takeaway.service;/*
 *@InterfaceName:SetMealService
 *@Description:
 *@Author:LL
 *@Date:2023/3/24
 */

import com.baomidou.mybatisplus.extension.service.IService;
import com.lili.takeaway.dto.SetmealDto;
import com.lili.takeaway.entity.Setmeal;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    /**
     * 新增套餐 同时保存套餐和菜品之间的关联关系
     * @param setmealDto
     */
    public  void saveWithDish(SetmealDto setmealDto);

    /**
     * 删除套餐，同时删除套餐和菜品的关联数据
     * @param ids
     */
    public void removeWithDish(List<Long> ids);

    /**
     * 修改套餐 回溯信息
     * @param id
     * @return
     */
    public SetmealDto getByIdWithDish(Long id);

    /**
     * 修改套餐
     * @param setmealDto
     */
    public void updateWithDish(SetmealDto setmealDto);
}
