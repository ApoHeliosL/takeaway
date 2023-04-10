package com.lili.takeaway.mapper;/*
 *@InterfaceName:UserMapper
 *@Description:
 *@Author:LL
 *@Date:2023/3/30
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lili.takeaway.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
