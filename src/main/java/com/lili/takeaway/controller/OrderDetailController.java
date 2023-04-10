package com.lili.takeaway.controller;/*
 *@ClassName:OrderDetailController
 *@Description:
 *@Author:LL
 *@Date:2023/4/3
 */

import com.lili.takeaway.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderDetail")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;
}


