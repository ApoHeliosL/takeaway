package com.lili.takeaway.mapper;/*
 *@ClassName:EmployeeMapper
 *@Description:
 *@Author:LL
 *@Date:2023/1/28
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lili.takeaway.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
