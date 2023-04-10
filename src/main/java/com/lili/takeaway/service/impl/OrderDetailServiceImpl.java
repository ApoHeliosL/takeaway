package com.lili.takeaway.service.impl;/*
 *@ClassName:OrderDetailServiceImpl
 *@Description:
 *@Author:LL
 *@Date:2023/4/3
 */

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lili.takeaway.entity.OrderDetail;
import com.lili.takeaway.mapper.OrderDetailMapper;
import com.lili.takeaway.service.OrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
