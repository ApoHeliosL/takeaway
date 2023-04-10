package com.lili.takeaway.service.impl;/*
 *@ClassName:DishFlavorServiceImpl
 *@Description:
 *@Author:LL
 *@Date:2023/3/27
 */

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lili.takeaway.entity.DishFlavor;
import com.lili.takeaway.mapper.DishFlavorMapper;
import com.lili.takeaway.service.DishFlavorService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl  extends ServiceImpl<DishFlavorMapper, DishFlavor>implements DishFlavorService {
}
