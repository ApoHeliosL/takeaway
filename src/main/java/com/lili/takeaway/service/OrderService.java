package com.lili.takeaway.service;/*
 *@InterfaceName:OrderService
 *@Description:
 *@Author:LL
 *@Date:2023/4/3
 */

import com.baomidou.mybatisplus.extension.service.IService;
import com.lili.takeaway.entity.Orders;

public interface OrderService extends IService<Orders> {
    public void submit(Orders orders);
}

