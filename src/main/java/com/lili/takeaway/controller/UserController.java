package com.lili.takeaway.controller;/*
 *@ClassName:UserController
 *@Description:
 *@Author:LL
 *@Date:2023/3/30
 */

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lili.takeaway.common.R;
import com.lili.takeaway.entity.User;
import com.lili.takeaway.service.UserService;
import com.lili.takeaway.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RequestMapping("/user")
@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 获取手机验证码
     * @param user
     * @param
     * @return
     */
    @PostMapping("/sendMsg")
    public R<String>  sendMsg(@RequestBody User user, HttpServletRequest request){
        //获取手机号码
       String phone = user.getPhone();
       if(StringUtils.isNotEmpty(phone)){
           //生成随机四位验证码
          String code = ValidateCodeUtils.generateValidateCode(4).toString();
           log.info("code:{}",code);
           //调用阿里云提供的短信服务API完成发送短信
//           SMSUtils.sendMessage("外卖系统","",phone,code);
           //需要将生成大的验证码保存到Session
           request.getSession().setAttribute(phone,code);
           return R.success("手机验证码短信发送成功");
       }
        return R.error("手机短信验证码发送失败");
    }

    /**
     * 移动端用户登录
     * @return
     */
    @PostMapping("/login")
    public R<User> login(@RequestBody Map map, HttpServletRequest request){
        log.info(map.toString());
        // 获取手机号
        String phone = (String) map.get("phone");
        // 获取验证码
        String code = (String) map.get("code");
        // session中获取验证码
        Object codeSession =  request.getSession().getAttribute(phone);
        // 比对验证码
        if(codeSession!=null&&codeSession.equals(code)){
            // 成功，则登录
            // 判断当前用户是否为新用户，新用户自动完成注册
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();
            // 手机号查询新用户
            queryWrapper.eq(User::getPhone, phone);
            User user = userService.getOne(queryWrapper);
            if(user==null){
                user = new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);
            }
            request.getSession().setAttribute("user",user.getId());
            return R.success(user);
        }
        return R.error("登录失败,验证码有误");
    }

    /**
     * 退出登录
     * @return
     */
    @PostMapping("/loginout")
    public R<String> loginout(HttpSession session){
        //清除session中的id。
        session.removeAttribute("user");
        return R.success("退出登录成功");
    }
}
