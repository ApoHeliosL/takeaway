package com.lili.takeaway.service.impl;/*
 *@ClassName:ShoppingCartServiceImpl
 *@Description:
 *@Author:LL
 *@Date:2023/4/3
 */

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lili.takeaway.entity.ShoppingCart;
import com.lili.takeaway.mapper.ShoppingCartMapper;
import com.lili.takeaway.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}

