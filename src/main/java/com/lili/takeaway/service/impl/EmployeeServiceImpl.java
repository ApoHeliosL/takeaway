package com.lili.takeaway.service.impl;/*
 *@ClassName:EmployeeServiceImpl
 *@Description:
 *@Author:LL
 *@Date:2023/1/28
 */

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lili.takeaway.entity.Employee;
import com.lili.takeaway.mapper.EmployeeMapper;
import com.lili.takeaway.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
