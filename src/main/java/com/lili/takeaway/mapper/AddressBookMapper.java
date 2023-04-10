package com.lili.takeaway.mapper;/*
 *@InterfaceName:AddressBookMapper
 *@Description:
 *@Author:LL
 *@Date:2023/4/3
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lili.takeaway.entity.AddressBook;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBook> {

}