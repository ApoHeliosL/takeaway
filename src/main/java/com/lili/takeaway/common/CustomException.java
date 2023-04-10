package com.lili.takeaway.common;/*
 *@ClassName:CustomException
 *@Description:
 *@Author:LL
 *@Date:2023/3/24
 */

public class CustomException extends RuntimeException{
    public CustomException(String message){
        super(message);
    }
}
