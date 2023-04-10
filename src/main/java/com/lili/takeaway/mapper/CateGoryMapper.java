package com.lili.takeaway.mapper;/*
 *@InterfaceName:CateGoryMapper
 *@Description:
 *@Author:LL
 *@Date:2023/2/28
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lili.takeaway.entity.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CateGoryMapper extends BaseMapper<Category> {
}
