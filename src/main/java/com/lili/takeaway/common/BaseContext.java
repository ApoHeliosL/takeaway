package com.lili.takeaway.common;/*
 *@ClassName:BaseContext
 *@Description:
 *@Author:LL
 *@Date:2023/2/28
 */

/**
 * 基于ThreadLocal封装工具类，用户保存和获取当前登录用户id
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }
    public static Long getCurrentId(){
      return  threadLocal.get();
    }
}
