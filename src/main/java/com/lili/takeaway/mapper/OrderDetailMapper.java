package com.lili.takeaway.mapper;/*
 *@InterfaceName:OrderDetailMapper
 *@Description:
 *@Author:LL
 *@Date:2023/4/3
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lili.takeaway.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {
}
