package com.lili.takeaway.service;/*
 *@InterfaceName:CateGoryService
 *@Description:
 *@Author:LL
 *@Date:2023/2/28
 */

import com.baomidou.mybatisplus.extension.service.IService;
import com.lili.takeaway.entity.Category;

public interface CateGoryService extends IService<Category> {
    public void remove(Long id);
}
