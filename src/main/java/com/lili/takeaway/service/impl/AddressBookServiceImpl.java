package com.lili.takeaway.service.impl;/*
 *@ClassName:AddressBookServiceImpl
 *@Description:
 *@Author:LL
 *@Date:2023/4/3
 */

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lili.takeaway.entity.AddressBook;
import com.lili.takeaway.mapper.AddressBookMapper;
import com.lili.takeaway.service.AddressBookService;
import org.springframework.stereotype.Service;


@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {

}

