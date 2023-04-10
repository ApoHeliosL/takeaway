package com.lili.takeaway.service.impl;/*
 *@ClassName:UserServiceImpl
 *@Description:
 *@Author:LL
 *@Date:2023/3/30
 */

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lili.takeaway.entity.User;
import com.lili.takeaway.mapper.UserMapper;
import com.lili.takeaway.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
